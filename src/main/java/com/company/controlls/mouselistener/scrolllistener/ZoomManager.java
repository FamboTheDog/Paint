package com.company.controlls.mouselistener.scrolllistener;

import com.company.view.container.paint.Paint;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class ZoomManager implements MouseWheelListener {

    private com.company.view.container.paint.Paint paint;

    // will probably be editing after play-testing
    final double maxBound = 10;
    final double minBound = 1;

    public ZoomManager(Paint paint) {
        this.paint = paint;
    }

    @Getter
    @Setter
    private double scale = 1;

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        this.scale -= e.getPreciseWheelRotation();
        if (scale < minBound) scale = minBound;
        else if (scale > maxBound) scale = maxBound;
        else {
            Dimension scaledDimension = new Dimension((int) Math.ceil((paint.getCurrentSize().getWidth() * scale)), (int) Math.ceil((paint.getCurrentSize().getHeight() * scale)));
            paint.setSize(scaledDimension);
            paint.getPaintC().getScrollPaneContainer().setPreferredSize(scaledDimension);
            paint.setScale(this.scale);
            paint.revalidate();
            paint.repaint();
            paint.getPaintC().getScrollPaneContainer().revalidate();
            paint.getPaintC().getScrollPaneContainer().repaint();
        }
    }
}
