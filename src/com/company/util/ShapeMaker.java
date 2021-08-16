package com.company.util;

import lombok.Getter;
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

    private int width;
    private int height;

    private ShapeModes mode;

    @Getter private Color color = Color.black;

    public ShapeMaker(){
        this.mode = ShapeModes.LINE;
    }

    public ShapeContainer temporaryShape(){
        Shape newShape;
        switch (mode){
            case LINE:
                newShape = new Line2D.Double(startX, startY, x, y);
                break;
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
                newShape = new Rectangle(tempX, tempY, width , height);
                break;
            default:
                return null;
        }
        return new ShapeContainer(color, newShape);
    }

    public ShapeContainer makeBrush(){
        if(mode == ShapeModes.BRUSH){
            return new ShapeContainer(color, new Ellipse2D.Double(x - ((float) width / 2), y - ((float) height / 2), width, height));
        } else{
            return null;
        }
    }
}
