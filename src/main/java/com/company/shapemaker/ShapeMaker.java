package com.company.shapemaker;

import com.company.view.container.paint.PaintType;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;

@Setter
public class ShapeMaker {

    private int startX;
    private int startY;

    private int x;
    private int y;

    @Getter private float strokeWidth = 5;

    @Getter private Stroke stroke;

    @Getter private ShapeModes mode;

    @Getter private Color color = Color.black;
    @Getter private Color bgColor = Color.white;


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
            case CIRCLE:
                // I was lazy and just copied the code, instead of making a function
                tempX = startX;
                tempY = startY;
                width = x - startX;
                height = y - startY;
                if(startX > x){
                    tempX = x;
                    width = startX - tempX;
                }
                if(startY > y){
                    tempY = y;
                    height = startY - tempY;
                }
                newShape = new Ellipse2D.Double(tempX, tempY, width , height);
                break;
            default:
                return null;
        }
        return new ShapeContainer(color, newShape, PaintType.DRAW, this.stroke);
    }

    public ShapeContainer makeBrush(){
        if(mode != ShapeModes.BRUSH) return null;
        this.stroke = new BasicStroke(strokeWidth,
                BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        ShapeContainer newShape = new ShapeContainer(color, new Line2D.Float(startX, startY, x, y), PaintType.PENCIL,
                this.stroke);
        startX = x;
        startY = y;
        return newShape;
    }

    public ShapeContainer eraser(){
        if(mode != ShapeModes.ERASER) return null;
        this.stroke = new BasicStroke(strokeWidth, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER);
        ShapeContainer newShape = new ShapeContainer(bgColor, new Line2D.Float(startX, startY, x, y), PaintType.PENCIL,
                this.stroke);
        startX = x;
        startY = y;
        return newShape;
    }
}
