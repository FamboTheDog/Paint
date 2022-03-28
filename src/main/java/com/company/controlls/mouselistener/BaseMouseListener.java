package com.company.controlls.mouselistener;

import com.company.controlls.keybind.control.ControlY;
import com.company.view.paint.Paint;
import lombok.RequiredArgsConstructor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@RequiredArgsConstructor
public abstract class BaseMouseListener extends MouseAdapter {

    protected final Paint paint;
    private final ControlY ctrlY;

    protected double startX;
    protected double startY;

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        ctrlY.reset();
        startX = e.getX() / paint.getScale();
        startY = e.getY() / paint.getScale();
    }

}
