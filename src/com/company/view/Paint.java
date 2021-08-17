package com.company.view;

import com.company.Main;
import com.company.shapeMaker.BucketContainer;
import com.company.shapeMaker.ShapeContainer;
import com.company.shapeMaker.ShapeMaker;
import com.company.shapeMaker.ShapeModes;

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

    private final ArrayList<Object> allShapes;

    private final ArrayList<ShapeContainer> shapesToDraw;
    private final ArrayList<ArrayList<ShapeContainer>> shapesToFill;

    private final ArrayList<ShapeContainer> currentShapeToFill;
    private ShapeContainer currentShapeToDraw;

    private final ShapeMaker shapeMaker;

    private final ArrayList<BufferedImage> loadedImages;

    private final ArrayList<BucketContainer> bucket;

    public Paint(ShapeMaker currentShape){
        shapesToDraw = new ArrayList<>();
        shapesToFill = new ArrayList<>();
        currentShapeToFill = new ArrayList<>();
        allShapes = new ArrayList<>();

        loadedImages = new ArrayList<>();

        bucket = new ArrayList<>();

        this.shapeMaker = currentShape;

        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D gd = (Graphics2D) g;

        loadedImages.forEach((image)-> gd.drawImage(image, 0, 0, null));

        shapesToDraw.forEach((shape)->{
            gd.setColor(shape.getColor());
            gd.draw(shape.getShape());
        });
        shapesToFill.forEach((container) -> container.forEach((shape)->{
            gd.setColor(shape.getColor());
            gd.fill(shape.getShape());
        }));
        currentShapeToFill.forEach((shape)->{
            gd.setColor(shape.getColor());
            gd.fill(shape.getShape());
        });

        if(currentShapeToDraw != null){
            gd.setColor(currentShapeToDraw.getColor());
            gd.draw(currentShapeToDraw.getShape());
        }

        bucket.forEach((b)->{
            gd.setColor(b.getColor());
            b.getCoordinates().forEach((bucketFill)-> gd.fillRect(bucketFill.x, bucketFill.y, 1, 1));
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
            BucketContainer bucket = shapeMaker.bucket(bImg, e.getX(), e.getY(), this.getWidth(), this.getHeight());
            if (bucket != null) this.bucket.add(bucket);
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        shapeMaker.setStartX(e.getX());
        shapeMaker.setStartY(e.getY());

        addToCurrentPaint(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(currentShapeToDraw != null) {
            ShapeContainer currentShapeToDrawCopy = new ShapeContainer(currentShapeToDraw.getColor(), currentShapeToDraw.getShape());
            shapesToDraw.add(currentShapeToDrawCopy);
            allShapes.add(currentShapeToDrawCopy);
        }

        if(currentShapeToFill.size() > 0) {
            ArrayList<ShapeContainer> currentPaintCopy = new ArrayList<>(currentShapeToFill);
            shapesToFill.add(currentPaintCopy);
            allShapes.add(currentPaintCopy);
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

    public ArrayList<Object> getAllShapes() {
        return allShapes;
    }

    public ArrayList<ShapeContainer> getShapesToDraw() {
        return shapesToDraw;
    }

    public ArrayList<ArrayList<ShapeContainer>> getShapesToFill() {
        return shapesToFill;
    }

    public void addToLoadedImages(BufferedImage img){
        this.loadedImages.add(img);
    }
}
