package com.company.view;

import com.company.util.ShapeMaker;
import com.company.util.ShapeMode;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class Toolbar extends JPanel implements ChangeListener {

    ShapeMaker currentShape;

    public Toolbar(ShapeMaker currentShape){
        this.currentShape = currentShape;
        init();
    }

    private void init() {
        this.setBackground(Color.lightGray);

        SpinnerModel strokeValues = new SpinnerNumberModel(5, 0, 99, 1);
        JSpinner strokeSetter = new JSpinner(strokeValues);
        strokeSetter.addChangeListener(this);
        strokeSetter.setVisible(false);

        JLabel strokeSetterText = new JLabel("Stroke:");
        strokeSetterText.setVisible(false);

        JButton lineDraw = buttonMaker("Line", e-> {
            currentShape.setMode(ShapeMode.LINE);
            strokeSetter.setVisible(false);
            strokeSetterText.setVisible(false);
        });
        this.add(lineDraw);

        JButton rectangleDraw = buttonMaker("Rect", e-> {
            currentShape.setMode(ShapeMode.RECTANGLE);
            strokeSetter.setVisible(false);
            strokeSetterText.setVisible(false);
        });
        this.add(rectangleDraw);


        JButton brush = buttonMaker("Brush", e-> {
            currentShape.setMode(ShapeMode.BRUSH);
            currentShape.setWidth((Integer) strokeSetter.getValue());
            currentShape.setHeight((Integer) strokeSetter.getValue());

            strokeSetter.setVisible(true);
            strokeSetterText.setVisible(true);

        });
        this.add(brush);
        this.add(strokeSetterText);
        this.add(strokeSetter);
    }

    private JButton buttonMaker(String name, ActionListener action){
        JButton newButton = new JButton(name);
        newButton.addActionListener(action);
        return newButton;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSpinner source = (JSpinner) e.getSource();
        currentShape.setWidth((Integer) source.getValue());
        currentShape.setHeight((Integer) source.getValue());
    }
}
