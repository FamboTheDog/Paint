package com.company.view.container.paint;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class ZoomManager implements MouseWheelListener {

    private Paint paint;

    // will probably be editing after play-testing, these variables are made to make finding these values easier
    final double maxBound = 4;
    final double minBound = 0.5;

    ZoomManager(Paint paint) {
        this.paint = paint;
    }

    @Getter @Setter double scale = 1;

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        // TODO changing the scale should also change the size of paintContainer
        this.scale -= e.getPreciseWheelRotation();
        if (scale < minBound) scale = minBound;
        if (scale > maxBound) scale = maxBound;

        System.out.println(scale);
        System.out.println((int) Math.ceil((paint.getWidth()  / scale)));
        System.out.println((int) Math.ceil((paint.getHeight() / scale)));
        System.out.println("_____");

        // paint.setSize(new Dimension((int) Math.ceil((paint.getWidth() / scale)), (int) Math.ceil((paint.getHeight() / scale))));
        paint.setScale(this.scale);
        paint.repaint();
    }
}
