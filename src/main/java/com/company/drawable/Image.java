package com.company.drawable;

import lombok.AllArgsConstructor;

import java.awt.*;
import java.awt.image.BufferedImage;

@AllArgsConstructor
public class Image implements Drawable {

    private BufferedImage image;

    @Override
    public void draw(Graphics2D gd) {
        gd.drawImage(image, 0, 0, null);
    }
}
