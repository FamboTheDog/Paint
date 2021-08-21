package com.company.view;

import com.company.Main;
import com.company.keybinds.ControlY;
import com.company.shapeMaker.containers.ShapeContainer;
import com.company.shapeMaker.ShapeMaker;
import com.company.shapeMaker.ShapeModes;
import lombok.Getter;
import lombok.Setter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Paint extends JPanel implements MouseListener, MouseMotionListener {

    @Getter private final ArrayList<ShapeContainer> toPaint = new ArrayList<>();

    private final ArrayList<ShapeContainer> currentShapeToFill = new ArrayList<>();
    private ShapeContainer currentShapeToDraw;

    private final ShapeMaker shapeMaker;

    @Setter private ControlY ctrlY;

    public Paint(ShapeMaker currentShape){
        this.shapeMaker = currentShape;

        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D gd = (Graphics2D) g;

        toPaint.forEach((painting)->{
            if (painting.getShapes() != null){
                painting.getShapes().forEach((paint->{
                    gd.setColor(paint.getColor());
                    switch (paint.getPaintType()){
                        case FILL -> gd.fill(paint.getShape());
                        case DRAW -> gd.draw(paint.getShape());

                    }
                }));
            }else{
                gd.setColor(painting.getColor());
                switch (painting.getPaintType()){
                    case FILL -> gd.fill(painting.getShape());
                    case DRAW -> gd.draw(painting.getShape());
                    case IMAGE -> gd.drawImage(painting.getImg(), 0, 0, null);
                }
            }
        });

        currentShapeToFill.forEach((shape)->{
            gd.setColor(shape.getColor());
            gd.fill(shape.getShape());
        });

        if(currentShapeToDraw != null){
            gd.setColor(currentShapeToDraw.getColor());
            gd.draw(currentShapeToDraw.getShape());
        }

        gd.dispose();
    }

    public void createImage(){
        FileDialog fileExplorer = new FileDialog(Main.getFrame(), "Save image", FileDialog.SAVE);
        fileExplorer.setDirectory(System.getProperty("user.dir"));
        fileExplorer.setFile("*.jpg");
        fileExplorer.setVisible(true);
        String filename = fileExplorer.getFile();
        String directory = fileExplorer.getDirectory();

        if (filename != null){
            BufferedImage bImg = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D cg = bImg.createGraphics();
            this.paintAll(cg);

            try {
                if (ImageIO.write(bImg, "jpg", new File(directory + filename))) {
                    System.out.println("image saved");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(shapeMaker.getMode() == ShapeModes.BUCKET) {
            BufferedImage bImg = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D cg = bImg.createGraphics();
            this.paintAll(cg);

            BufferedImage bucket = shapeMaker.bucket(bImg, e.getX(), e.getY(), this.getWidth(), this.getHeight());

            toPaint.add(new ShapeContainer(shapeMaker.getColor(), bucket, PaintType.IMAGE));
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        shapeMaker.setStartX(e.getX());
        shapeMaker.setStartY(e.getY());

        ctrlY.reset();

        addToCurrentPaint(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(currentShapeToDraw != null) {
            ShapeContainer currentShapeToDrawCopy = new ShapeContainer(shapeMaker.getColor(), currentShapeToDraw.getShape(), PaintType.DRAW);
            toPaint.add(currentShapeToDrawCopy);
        }

        if(currentShapeToFill.size() > 0) {
            ShapeContainer copy = new ShapeContainer(shapeMaker.getColor(), new ArrayList<>(currentShapeToFill), PaintType.FILL);
            toPaint.add(copy);
            currentShapeToFill.clear();
        }
        currentShapeToDraw = null;
        repaint();
    }

    public void addToCurrentPaint(MouseEvent e){
        shapeMaker.setX(e.getX());
        shapeMaker.setY(e.getY());

        ShapeContainer newShape = shapeMaker.makeBrush();
        if(newShape != null) currentShapeToFill.add(newShape);

        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {
        addToCurrentPaint(e);
        currentShapeToDraw = shapeMaker.temporaryShape();
    }

    @Override
    public void mouseMoved(MouseEvent e) {}

    public void addToLoadedImages(BufferedImage img){
        toPaint.add(new ShapeContainer(shapeMaker.getColor(), img, PaintType.IMAGE));
    }
}
