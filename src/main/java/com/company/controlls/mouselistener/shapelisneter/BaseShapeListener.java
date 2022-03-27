package com.company.controlls.mouselistener.shapelisneter;

import com.company.controlls.keybind.control.ControlY;
import com.company.controlls.mouselistener.BaseMouseListener;
import com.company.drawable.DrawableShape;
import com.company.view.container.paint.Paint;

import java.awt.event.MouseEvent;

public abstract class BaseShapeListener extends BaseMouseListener {

    double newX;
    double newY;
    double width;
    double height;

    protected DrawableShape shape;

    public BaseShapeListener(Paint paint, ControlY ctrlY) {
        super(paint, ctrlY);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);

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
