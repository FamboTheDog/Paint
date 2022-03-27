package com.company.controlls.mouselistener;

import com.company.controlls.keybind.control.ControlY;
import com.company.drawable.Brush;
import com.company.view.container.paint.Paint;

import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

public class BrushListener extends BaseMouseListener {

    private Brush brush;

    public BrushListener(Paint paint, ControlY controlY) {
        super(paint, controlY);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        brush = new Brush();
        brush.getBrushes().add(new Line2D.Double(startX, startY, startX, startY));
        paint.getDrawables().add(brush);
        paint.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        double x = e.getX() / paint.getScale();
        double y = e.getY() / paint.getScale();

        brush.getBrushes().add(new Line2D.Double(startX, startY, x, y));

        startX = x;
        startY = y;
        paint.repaint();
    }
}
