package com.company.drawable;

import lombok.AllArgsConstructor;
import lombok.Setter;

import java.awt.*;

@AllArgsConstructor
public class DrawableShape implements Drawable {

    @Setter
    protected Shape shapeToDraw;

    public void draw(Graphics2D gd) {
        gd.draw(shapeToDraw);
    }
}
