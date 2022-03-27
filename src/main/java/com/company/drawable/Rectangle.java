package com.company.drawable;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Rectangle extends DrawableShape {

    public Rectangle(Shape shapeToDraw) {
        super(shapeToDraw);
    }

    @Override
    public void draw(Graphics2D gd) {
        gd.draw(shapeToDraw);
    }
}
