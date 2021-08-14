package com.company.view;

import com.company.util.ShapeMaker;
import com.company.util.ShapeMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Toolbar extends JPanel{

    ShapeMaker currentShape;

    public Toolbar(ShapeMaker currentShape){
        this.currentShape = currentShape;
        init();
    }

    private void init() {
        this.setBackground(Color.lightGray);


        JButton lineDraw = buttonMaker("Line", e-> currentShape.setMode(ShapeMode.LINE));
        this.add(lineDraw);

        JButton rectangleDraw = buttonMaker("Rect", e-> currentShape.setMode(ShapeMode.RECTANGLE));
        this.add(rectangleDraw);

        JButton brush = buttonMaker("brush", e-> {
            currentShape.setMode(ShapeMode.BRUSH);
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
