package com.company.view.container.paint;

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

    private ShapeContainer currentShapeToFill;
    private ShapeContainer eraser;
    private ShapeContainer currentShapeToDraw;

    private final ShapeMaker shapeMaker;

    @Setter private ControlY ctrlY;

    public Paint(ShapeMaker currentShape){
        this.shapeMaker = currentShape;

        Dimension defaultSize = new Dimension(640,480);

        this.setSize(defaultSize);

        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D gd = (Graphics2D) g;

        gd.setColor(Color.white);
        gd.fillRect(0, 0, getWidth(), getHeight());

        Stroke defaultStroke = gd.getStroke();

        toPaint.forEach((painting)->{
            if (painting.getShapes() != null){
                painting.getShapes().forEach((paint->{
                    gd.setColor(paint.getColor());
                    switch (paint.getPaintType()){
                        // not sure if the case DRAW can ever happen
                        case PENCIL -> {
                            gd.setStroke(paint.getStroke());
                            gd.draw(paint.getShape());
                            gd.setStroke(defaultStroke);
                        }
                        case DRAW -> gd.draw(paint.getShape());
                    }
                }));
            }else{
                gd.setColor(painting.getColor());
                switch (painting.getPaintType()){
                    case DRAW -> gd.draw(painting.getShape());
                    case IMAGE -> gd.drawImage(painting.getImg(), 0, 0, null);
                }
            }
        });


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


        if(shapeMaker.getMode() == ShapeModes.ERASER){
            eraser = new ShapeContainer(shapeMaker.getBgColor(), new ArrayList<>(), PaintType.PENCIL);
            toPaint.add(eraser);
        }else if(shapeMaker.getMode() == ShapeModes.BRUSH){
            currentShapeToFill = new ShapeContainer(shapeMaker.getColor(), new ArrayList<>(), PaintType.PENCIL, shapeMaker.getStroke());
            toPaint.add(currentShapeToFill);
        }

        addToCurrentPaint(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(shapeMaker.getMode() == ShapeModes.LINE || shapeMaker.getMode() == ShapeModes.RECTANGLE) {
            toPaint.remove(currentShapeToDraw);
            if(currentShapeToDraw != null)
                toPaint.add(new ShapeContainer(currentShapeToDraw.getColor(), currentShapeToDraw.getShape(), currentShapeToDraw.getPaintType()));
        }else if(shapeMaker.getMode() == ShapeModes.BRUSH) {
            toPaint.remove(currentShapeToFill);
            toPaint.add(new ShapeContainer(currentShapeToFill.getColor(), currentShapeToFill.getShapes(), PaintType.PENCIL, currentShapeToFill.getStroke()));
        }else if(shapeMaker.getMode() == ShapeModes.ERASER){
            toPaint.remove(eraser);
            toPaint.add(new ShapeContainer(eraser.getColor(),eraser.getShapes(), PaintType.PENCIL));
        }
        currentShapeToDraw = null;
        repaint();
    }

    public void addToCurrentPaint(MouseEvent e){
        shapeMaker.setX(e.getX());
        shapeMaker.setY(e.getY());

        ShapeContainer newShape = shapeMaker.makeBrush();
        if(newShape != null) currentShapeToFill.getShapes().add(newShape);

        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {
        addToCurrentPaint(e);

        if(shapeMaker.getMode() == ShapeModes.LINE || shapeMaker.getMode() == ShapeModes.RECTANGLE){
            toPaint.remove(currentShapeToDraw);
            currentShapeToDraw = shapeMaker.temporaryShape();
            toPaint.add(currentShapeToDraw);
        }

        ShapeContainer eraser = shapeMaker.eraser();
        if(eraser != null) this.eraser.getShapes().add(eraser);
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {}

    public void addToLoadedImages(BufferedImage img){
        toPaint.add(new ShapeContainer(shapeMaker.getColor(), img, PaintType.IMAGE));
    }
}
