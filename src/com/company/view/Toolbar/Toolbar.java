package com.company.view.Toolbar;

import com.company.Main;
import com.company.shapeMaker.ShapeMaker;
import com.company.shapeMaker.ShapeModes;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Toolbar extends JPanel implements ChangeListener {

    ShapeMaker currentShape;

    public Toolbar(ShapeMaker currentShape){
        this.currentShape = currentShape;
        init();
    }

    private JLabel strokeSetterText;
    private JSpinner strokeSetter;
    private JButton colorChooser;

    private ArrayList<JButton> buttons;

    private void init() {
        buttons = new ArrayList<>();

        this.setBackground(Color.lightGray);

        SpinnerModel strokeValues = new SpinnerNumberModel(5, 1, 99, 1);
        strokeSetter = new JSpinner(strokeValues);
        strokeSetter.addChangeListener(this);
        strokeSetter.setVisible(false);

        strokeSetterText = new JLabel("Stroke:");
        strokeSetterText.setVisible(false);

        JButton lineDraw = buttonMaker("Line", action(e->currentShape.setMode(ShapeModes.LINE)));
        this.add(lineDraw);
        lineDraw.setBackground(Color.decode("#c0cce4"));

        JButton rectangleDraw = buttonMaker("Rect", action(e->currentShape.setMode(ShapeModes.RECTANGLE)));
        this.add(rectangleDraw);


        JButton brush = buttonMaker("Brush", action(e-> {
            currentShape.setMode(ShapeModes.BRUSH);
            currentShape.setWidth((Integer) strokeSetter.getValue());
            currentShape.setHeight((Integer) strokeSetter.getValue());

            strokeSetter.setVisible(true);
            strokeSetterText.setVisible(true);
        }));

        this.add(brush);
        this.add(strokeSetterText);
        this.add(strokeSetter);

        JButton bucket = buttonMaker("Bucket", action(e->currentShape.setMode(ShapeModes.BUCKET)));

        this.add(bucket);

        colorChooser = buttonMaker(" ", e->{
            Color newColor = JColorChooser.showDialog(
                    Main.getFrame(),
                    "Choose Color",
                    currentShape.getColor());
            if(newColor != null) {
                currentShape.setColor(newColor);
                colorChooser.setBackground(currentShape.getColor());
            }
        });
        colorChooser.setSize(new Dimension(50, 50));
        colorChooser.setBackground(currentShape.getColor());
        colorChooser.setFocusPainted(false);
        buttons.remove(colorChooser);


        this.add(colorChooser);
    }

    private ActionListener action(ActionListener action){
        return e -> {
            buttons.forEach((button) -> button.setBackground(new JButton().getBackground()));

            JButton source = (JButton) e.getSource();
            source.setBackground(Color.decode("#c0cce4"));

            strokeSetter.setVisible(false);
            strokeSetterText.setVisible(false);
            action.actionPerformed(e);
        };
    }

    private JButton buttonMaker(String name, ActionListener action){
        JButton newButton = new JButton(name);
        newButton.addActionListener(action);
        buttons.add(newButton);
        return newButton;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSpinner source = (JSpinner) e.getSource();
        currentShape.setWidth((Integer) source.getValue());
        currentShape.setHeight((Integer) source.getValue());
    }
}
