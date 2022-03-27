package com.company.controlls.mouselistener.shapelisneter;

import com.company.controlls.keybind.control.ControlY;
import com.company.drawable.DrawableShape;
import com.company.view.container.paint.Paint;

import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

public class EllipseListener extends BaseShapeListener {

    public EllipseListener(Paint paint, ControlY ctrlY) {
        super(paint, ctrlY);
    }

    @Override
    DrawableShape instantiateShape() {
        return new DrawableShape(new Ellipse2D.Double(startX, startY, 1, 1));
    }

    @Override
    void updateShape(MouseEvent e) {
        invertShape(e);
        shape.setShapeToDraw(new Ellipse2D.Double(newX, newY, width, height));
    }
}
