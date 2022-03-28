package com.company.controlls.mouselistener.shapelisneter;

import com.company.controlls.keybind.control.ControlY;
import com.company.drawable.DrawableShape;
import com.company.view.container.paint.Paint;

import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class RectangleListener extends BaseShapeListener {

    public RectangleListener(Paint paint, ControlY ctrlY) {
        super(paint, ctrlY);
    }

    @Override
    DrawableShape instantiateShape() {
        return new DrawableShape(new Rectangle2D.Double(startX, startY, 1, 1), paint.getStroke(), paint.getColor());
    }

    @Override
    void updateShape(MouseEvent e) {
        invertShape(e);
        shape.setShapeToDraw(new Rectangle2D.Double(newX, newY, width, height));
    }
}
