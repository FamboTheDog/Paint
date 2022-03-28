package com.company.controlls.mouselistener;

import com.company.drawable.Bucket;
import com.company.view.container.paint.Paint;
import lombok.AllArgsConstructor;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

@AllArgsConstructor
public class BucketListener extends MouseAdapter {

    private Paint paint;

    @Override
    public void mouseClicked(MouseEvent e) {
        BufferedImage bImg = new BufferedImage(paint.getWidth(), paint.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D cg = bImg.createGraphics();
        paint.paintAll(cg);

        paint.getDrawables().add(new Bucket(bImg, e.getX(), e.getY(), paint.getWidth(), paint.getHeight(), paint.getColor()));

        paint.repaint();
    }
}
