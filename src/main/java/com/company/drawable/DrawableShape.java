package com.company.drawable;

import lombok.AllArgsConstructor;
import lombok.Setter;

import java.awt.*;

@AllArgsConstructor
public abstract class DrawableShape implements Drawable {

    @Setter
    protected Shape shapeToDraw;

}
