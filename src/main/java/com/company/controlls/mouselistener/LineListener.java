package com.company.controlls.mouselistener;

import com.company.controlls.keybind.control.ControlY;
import com.company.drawable.Line;
import com.company.view.container.paint.Paint;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

@RequiredArgsConstructor
public class LineListener extends MouseAdapter {

    private final Paint paint;

    private double startX;
    private double startY;

    private final ControlY ctrlY;

    private Line line;

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        ctrlY.reset();
        startX = e.getX() / paint.getScale();
        startY = e.getY() / paint.getScale();

        line = new Line(new Line2D.Double(startX, startY, startX, startY));
        paint.getDrawables().add(line);
        paint.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        updateLine(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        updateLine(e);
    }

    private void updateLine(MouseEvent e) {
        line.setLineToDraw(new Line2D.Double(startX, startY, e.getX() / paint.getScale(), e.getY() / paint.getScale()));
        paint.repaint();
    }
}
