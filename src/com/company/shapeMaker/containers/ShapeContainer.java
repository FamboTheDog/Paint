package com.company.shapeMaker.containers;

import com.company.view.Paint;
import com.company.view.PaintType;
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
    PaintType paintType;

    public ShapeContainer(Color color, Shape shape, PaintType paintType){
        this.color = color;
        this.shape = shape;
        this.paintType = paintType;
    }

    public ShapeContainer(Color color, ArrayList<ShapeContainer> shapes, PaintType paintType){
        this.color = color;
        this.shapes = shapes;
        this.paintType = paintType;
    }

    public ShapeContainer(Color color, BufferedImage img, PaintType paintType){
        this.color = color;
        this.img = img;
        this.paintType = paintType;
    }
}
