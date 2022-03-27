package com.company.drawable;

import jdk.jfr.StackTrace;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.Line2D;

@AllArgsConstructor
public class Line implements Drawable {

    @Getter
    @Setter
    private Line2D.Double lineToDraw;

    @Override
    public void draw(Graphics2D gd) {
        gd.draw(lineToDraw);
    }
}
