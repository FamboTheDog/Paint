package com.company.drawable;

import lombok.Getter;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class Brush implements Drawable {

    @Getter
    private ArrayList<Line2D.Double> brushes = new ArrayList<>();

    @Override
    public void draw(Graphics2D gd) {
        brushes.forEach(gd::draw);
    }
}
