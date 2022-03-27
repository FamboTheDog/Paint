package com.company.controlls.mouselistener.shapelisneter;

import com.company.controlls.keybind.control.ControlY;
import com.company.drawable.DrawableShape;
import com.company.view.container.paint.Paint;
import lombok.RequiredArgsConstructor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

@RequiredArgsConstructor
public abstract class BaseShapeListener extends MouseAdapter {

    protected final Paint paint;

    protected double startX;
    protected double startY;

    double newX;
    double newY;
    double width;
    double height;

    private final ControlY ctrlY;

    protected DrawableShape shape;

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        ctrlY.reset();
        startX = e.getX() / paint.getScale();
        startY = e.getY() / paint.getScale();

        shape = instantiateShape();
        paint.getDrawables().add(shape);
        paint.repaint();
    }

    abstract DrawableShape instantiateShape();

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        updateShape(e);
        paint.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        updateShape(e);
        paint.repaint();
    }

    abstract void updateShape(MouseEvent e);

    protected void invertShape(MouseEvent e) {
        double x = e.getX() / paint.getScale();
        double y = e.getY() / paint.getScale();
        newX = startX;
        newY = startY;
        width = x - startX;
        height = y - startY;
        if(startX > x){
            newX = x;
            width = startX - newX;
        }
        if(startY > y){
            newY = y;
            height = startY - newY;
        }
    }

}
