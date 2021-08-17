package com.company.shapeMaker;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

@Setter
public class ShapeMaker {

    private int startX;
    private int startY;

    private int x;
    private int y;

    private int width;
    private int height;

    @Getter private ShapeModes mode;

    @Getter private Color color = Color.black;

    private ArrayList<Integer> bucketX;
    private ArrayList<Integer> bucketY;

    public ShapeMaker(){
        this.mode = ShapeModes.LINE;
        bucketX = new ArrayList<>();
        bucketY = new ArrayList<>();
    }

    public BucketContainer bucket(BufferedImage img, int x, int y, int boundsX, int boundsY) {
        System.out.println(img.getRGB(x, y));
        System.out.println(color.getRGB());
        System.out.println("----");
        System.out.println(x);
        System.out.println(y);
        System.out.println("------");
        int startColor = img.getRGB(x, y);
        if(startColor == color.getRGB()) return null;
        ArrayList<Point> toFill = new ArrayList<>();
        Queue<Point> fill = new LinkedList<>();
        fill.add(new Point(x, y));
        toFill.add(new Point(x, y));
        while(!fill.isEmpty()){
            Point n = fill.peek();
            fill.remove(n);
            if(img.getRGB(n.x, n.y) == startColor && n.x > 0 && n.x < boundsX - 1 && n.y > 0 && n.y < boundsY - 1){
                img.setRGB(n.x, n.y, color.getRGB());

                fill.add(new Point(n.x + 1, n.y));
                toFill.add(new Point(n.x + 1, n.y));

                fill.add(new Point(n.x - 1, n.y));
                toFill.add(new Point(n.x - 1, n.y));

                fill.add(new Point(n.x, n.y + 1));
                toFill.add(new Point(n.x, n.y + 1));

                fill.add(new Point(n.x, n.y - 1));
                toFill.add(new Point(n.x, n.y - 1));
            }
        }
        return new BucketContainer(toFill, color);
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
