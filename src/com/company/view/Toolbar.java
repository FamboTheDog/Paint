package com.company.view;

import com.company.util.ShapeMaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class Toolbar extends JPanel{

    ShapeMaker currentShape;

    public Toolbar(ShapeMaker currentShape){
        this.currentShape = currentShape;
        init();
    }

    private void init() {
        this.setBackground(Color.lightGray);


        JButton lineDraw = buttonMaker("Line", e-> currentShape.setShape(new Line2D.Double()));
        this.add(lineDraw);

        JButton rectangleDraw = buttonMaker("Rect", e-> currentShape.setShape(new Rectangle()));
        this.add(rectangleDraw);

        JButton brush = buttonMaker("brush", e-> {
            currentShape.setShape(new Ellipse2D.Double());
            // TODO MAKE AND OPTION FOR THE USER TO CHANGE THE WIDTH
            currentShape.setWidth(5);
            currentShape.setHeight(5);
        });
        this.add(brush);
    }

    private JButton buttonMaker(String name, ActionListener action){
        JButton newButton = new JButton(name);
        newButton.addActionListener(action);
        return newButton;
    }

}
