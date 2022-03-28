package com.company.controlls.mouselistener.shapelisneter;

import com.company.controlls.keybind.control.ControlY;
import com.company.drawable.DrawableShape;
import com.company.view.paint.Paint;

import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

public class LineListener extends BaseShapeListener {

    public LineListener(Paint paint, ControlY ctrlY) {
        super(paint, ctrlY);
    }

    @Override
    DrawableShape instantiateShape() {
        return new DrawableShape(new Line2D.Double(startX, startY, startX, startY), paint.getStroke(), paint.getColor());
    }

    @Override
    void updateShape(MouseEvent e) {
        shape.setShapeToDraw(new Line2D.Double(startX, startY, e.getX() / paint.getScale(), e.getY() / paint.getScale()));
    }
}
