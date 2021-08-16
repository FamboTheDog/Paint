package com.company.util;

import lombok.Setter;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

@Setter
public class ShapeMaker {

    private int startX;
    private int startY;
    private int x;
    private int y;
    private int endX;
    private int endY;
    private int width;
    private int height;
    private ShapeModes mode;

    private Color color = Color.black; // this variable in going to be strongly typed until later

    public ShapeMaker(){
        this.mode = ShapeModes.LINE; // default shape
    }

    public ShapeContainer temporaryShape(){
        switch (mode){
            case LINE:
                return new ShapeContainer(color, new Line2D.Double(startX, startY, x, y));
            case RECTANGLE:
                int tempX = startX;
                int tempY = startY;
                int width = x - startX;
                int height = y - startY;
                if(startX > x){
                    tempX = x;
                    width = startX - tempX;
                }
                if(startY > y){
                    tempY = y;
                    height = startY - tempY;
                }
                return new ShapeContainer(color, new Rectangle(tempX, tempY, width , height));
            default:
                return null;
        }
    }

    public ShapeContainer makeBrush(){
        if(mode == ShapeModes.BRUSH){
            return new ShapeContainer(color, new Ellipse2D.Double(x - ((float) width / 2), y - ((float) height / 2), width, height));
        } else{
            return null;
        }
    }
}
