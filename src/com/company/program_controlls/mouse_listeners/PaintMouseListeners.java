package com.company.program_controlls.mouse_listeners;

import com.company.program_controlls.keybinds.control.ControlY;
import com.company.shapeMaker.ShapeContainer;
import com.company.shapeMaker.ShapeMaker;
import com.company.shapeMaker.ShapeModes;
import com.company.view.container.paint.Paint;
import com.company.view.container.paint.PaintContainer;
import com.company.view.container.paint.PaintType;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/*
* This class should seriously be split into 2 classes!
* */
public class PaintMouseListeners extends MouseAdapter implements MouseMotionListener {

    @Getter
    private final ArrayList<ShapeContainer> toPaint = new ArrayList<>();

    private ShapeContainer currentShapeToFill;
    private ShapeContainer eraser;
    private ShapeContainer currentShapeToDraw;

    private final ShapeMaker shapeMaker;

    @Setter private ControlY ctrlY;

    @Getter @Setter private PaintContainer paintC;

    @Getter @Setter double scale = 1;

    Paint paint;

    public PaintMouseListeners(ShapeMaker shapeMaker, Paint paint) {
        this.shapeMaker = shapeMaker;
        this.paint = paint;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(shapeMaker.getMode() == ShapeModes.BUCKET) {
            BufferedImage bImg = new BufferedImage(paint.getWidth(), paint.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D cg = bImg.createGraphics();
            paint.paintAll(cg);

            BufferedImage bucket = shapeMaker.bucket(bImg, e.getX(), e.getY(), paint.getWidth(), paint.getHeight());

            toPaint.add(new ShapeContainer(shapeMaker.getColor(), bucket, PaintType.IMAGE));
            paint.repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        shapeMaker.setStartX((int) (e.getX() / scale));
        shapeMaker.setStartY((int) (e.getY() / scale));

        ctrlY.reset();


        if(shapeMaker.getMode() == ShapeModes.ERASER){
            eraser = new ShapeContainer(shapeMaker.getBgColor(), new ArrayList<>(), PaintType.PENCIL, shapeMaker.getStroke());
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
            toPaint.add(new ShapeContainer(eraser.getColor(),eraser.getShapes(), PaintType.PENCIL, shapeMaker.getStroke()));
        }
        currentShapeToDraw = null;
        paint.repaint();
    }

    public void addToCurrentPaint(MouseEvent e){
        shapeMaker.setX((int) (e.getX() / scale));
        shapeMaker.setY((int) (e.getY() / scale));

        ShapeContainer newShape = shapeMaker.makeBrush();
        if(newShape != null) currentShapeToFill.getShapes().add(newShape);

        paint.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        addToCurrentPaint(e);

        if(shapeMaker.getMode() == ShapeModes.LINE || shapeMaker.getMode() == ShapeModes.RECTANGLE
                || shapeMaker.getMode() == ShapeModes.CIRCLE){
            toPaint.remove(currentShapeToDraw);
            currentShapeToDraw = shapeMaker.temporaryShape();
            toPaint.add(currentShapeToDraw);
        }

        ShapeContainer eraser = shapeMaker.eraser();
        if(eraser != null) this.eraser.getShapes().add(eraser);
        paint.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {}

}
