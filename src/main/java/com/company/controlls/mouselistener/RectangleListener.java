package com.company.controlls.mouselistener;

import com.company.controlls.keybind.control.ControlY;
import com.company.drawable.DrawableShape;
import com.company.drawable.Rectangle;
import com.company.view.container.paint.Paint;

import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class RectangleListener extends BaseShapeListener {

    public RectangleListener(Paint paint, ControlY ctrlY) {
        super(paint, ctrlY);
    }

    @Override
    DrawableShape instantiateShape() {
        return new Rectangle(new Rectangle2D.Double(startX, startY, 1, 1));
    }

    @Override
    void updateShape(MouseEvent e) {
        double x = e.getX() / paint.getScale();
        double y = e.getY() / paint.getScale();
        double tempX = startX;
        double tempY = startY;
        double width = x - startX;
        double height = y - startY;
        if(startX > x){
            tempX = x;
            width = startX - tempX;
        }
        if(startY > y){
            tempY = y;
            height = startY - tempY;
        }
        shape.setShapeToDraw(new Rectangle2D.Double(tempX, tempY, width, height));
        paint.repaint();
    }
}
