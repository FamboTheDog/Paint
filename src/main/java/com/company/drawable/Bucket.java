package com.company.drawable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;

public class Bucket implements Drawable {

    private BufferedImage img;

    public Bucket(BufferedImage img, int x, int y, int boundsX, int boundsY, Color color) {
        int startColor = img.getRGB(x, y);
        if(startColor == color.getRGB()) {
            this.img = null;
            return;
        }

        Queue<Point> fill = new LinkedList<>();

        fill.add(new Point(x, y));

        while(!fill.isEmpty()){
            Point n = fill.peek();
            fill.remove(n);
            if(n.x >= 0 && n.x < boundsX && n.y >= 0 && n.y < boundsY && img.getRGB(n.x, n.y) == startColor){
                img.setRGB(n.x, n.y, color.getRGB());

                fill.add(new Point(n.x + 1, n.y));
                fill.add(new Point(n.x - 1, n.y));
                fill.add(new Point(n.x, n.y + 1));
                fill.add(new Point(n.x, n.y - 1));
            }
        }
        this.img = img;
    }

    @Override
    public void draw(Graphics2D gd) {
        if (img != null) gd.drawImage(img, 0, 0, null);
    }
}
