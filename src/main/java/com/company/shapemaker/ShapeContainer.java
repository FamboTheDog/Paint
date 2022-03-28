package com.company.shapemaker;

import com.company.view.container.paint.PaintType;
import lombok.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

@Getter
@Setter
public class ShapeContainer {
    Color color;
    Shape shape;
    ArrayList<ShapeContainer> shapes;
    BufferedImage img;
    Stroke stroke;
    PaintType paintType;
}
