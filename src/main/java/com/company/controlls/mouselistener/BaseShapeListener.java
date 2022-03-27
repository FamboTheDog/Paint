package com.company.controlls.mouselistener;

import com.company.controlls.keybind.control.ControlY;
import com.company.drawable.DrawableShape;
import com.company.view.container.paint.Paint;
import lombok.RequiredArgsConstructor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@RequiredArgsConstructor
public abstract class BaseShapeListener extends MouseAdapter {

    protected final Paint paint;

    protected double startX;
    protected double startY;

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
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        updateShape(e);
    }

    abstract void updateShape(MouseEvent e);

}
