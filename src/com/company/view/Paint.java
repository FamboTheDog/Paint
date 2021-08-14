package com.company.view;

import com.company.util.ShapeContainer;
import com.company.util.ShapeMaker;

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
    private final ArrayList<ShapeContainer> currentPaint;


    private final ShapeMaker shapeMaker;

    public Paint(ShapeMaker currentShape){
        shapesToDraw = new ArrayList<>();
        shapesToFill = new ArrayList<>();
        currentPaint = new ArrayList<>();
        allShapes = new ArrayList<>();

        this.shapeMaker = currentShape;

        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D gd = (Graphics2D) g;

        shapesToDraw.forEach((shape)->{
            gd.setColor(shape.getColor());
            gd.draw(shape.getShape());
        });
        shapesToFill.forEach((container) -> container.forEach((shape)->{
            gd.setColor(shape.getColor());
            gd.fill(shape.getShape());
        }));
        currentPaint.forEach((shape)->{
            gd.setColor(shape.getColor());
            gd.fill(shape.getShape());
        });

        gd.dispose();
    }

    public void createImage(){
        BufferedImage bImg = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D cg = bImg.createGraphics();
        this.paintAll(cg);
        try {
            if (ImageIO.write(bImg, "png", new File("./output_image.png"))) {
                System.out.println("image saved");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        shapeMaker.setStartX(e.getX());
        shapeMaker.setStartY(e.getY());

        addToCurrentPaint(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        shapeMaker.setEndX(e.getX());
        shapeMaker.setEndY(e.getY());

        ShapeContainer newShape = shapeMaker.makeShape();
        if(newShape != null) {
            shapesToDraw.add(newShape);
            allShapes.add(newShape);
        }

        if(currentPaint.size() > 0) {
            ArrayList<ShapeContainer> currentPaintCopy = new ArrayList<>(currentPaint);
            shapesToFill.add(currentPaintCopy);
            allShapes.add(currentPaintCopy);
            currentPaint.clear();
        }

        repaint();
    }

    public void addToCurrentPaint(MouseEvent e){
        shapeMaker.setX(e.getX());
        shapeMaker.setY(e.getY());

        ShapeContainer newShape = shapeMaker.makeBrush();
        if(newShape != null) currentPaint.add(newShape);

        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {
        addToCurrentPaint(e);
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
}
