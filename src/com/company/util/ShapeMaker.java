package com.company.util;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class ShapeMaker {

    private int startX;
    private int startY;

    private int x;
    private int y;

    private int endX;
    private int endY;

    private int width;
    private int height;

    private ShapeMode mode;

    private Color color = Color.black; // this variable in going to be strongly typed until later

    public ShapeMaker(){
        this.mode = ShapeMode.BRUSH; // default shape
    }

    public ShapeMode getMode() {
        return mode;
    }

    public ShapeContainer makeShape(){
        Shape shapeToReturn;
        switch (mode){
            case LINE:
                shapeToReturn = new Line2D.Double(startX, startY, endX, endY);
                break;
            case RECTANGLE:
                int width = startX - endX;
                width = -width;
                int height = startY - endY;
                height = -height;
                shapeToReturn = new Rectangle(startX, startY, width, height);
                break;
            default:
                return null;
        }

        return new ShapeContainer(color, shapeToReturn);
    }

    public ShapeContainer makeBrush(){
        if(mode == ShapeMode.BRUSH){
            return new ShapeContainer(color, new Ellipse2D.Double(x, y, width, height));
        } else{
            return null;
        }
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setMode(ShapeMode mode) {
        this.mode = mode;
    }
}
