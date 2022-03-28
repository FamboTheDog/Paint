package com.company.drawable;

import lombok.AllArgsConstructor;
import lombok.Setter;

import java.awt.*;

@AllArgsConstructor
public class DrawableShape implements Drawable {

    @Setter
    protected Shape shapeToDraw;

    protected Stroke stroke;
    protected Color color;

    public void draw(Graphics2D gd) {
        gd.setStroke(stroke);
        gd.setColor(color);
        gd.draw(shapeToDraw);
    }
}
