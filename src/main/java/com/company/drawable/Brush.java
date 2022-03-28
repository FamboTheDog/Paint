package com.company.drawable;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

@AllArgsConstructor
public class Brush implements Drawable {

    @Getter
    private final ArrayList<Line2D.Double> brushes = new ArrayList<>();

    protected Stroke stroke;
    protected Color color;

    @Override
    public void draw(Graphics2D gd) {
        gd.setStroke(stroke);
        gd.setColor(color);
        brushes.forEach(gd::draw);
    }
}
