package com.company.view.container;

import com.company.Main;
import com.company.shapeMaker.ShapeMaker;
import com.company.shapeMaker.ShapeModes;
import lombok.Getter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Toolbar extends JPanel implements ChangeListener {

    ShapeMaker currentShape;

    public Toolbar(ShapeMaker currentShape){
        this.currentShape = currentShape;
        init();
    }

    private JLabel strokeSetterText;
    private JSpinner strokeSetter;

    private ArrayList<JButton> buttons;

    @Getter private JButton pencil;
    @Getter private JButton bucket;
    @Getter private JButton eraser;

    private void init() {
        buttons = new ArrayList<>();

        this.setBackground(Color.lightGray);

        JPanel shapes = new JPanel();
        shapes.setBorder(BorderFactory.createLineBorder(Color.black));

        SpinnerModel strokeValues = new SpinnerNumberModel(5, 1, 99, 1);
        strokeSetter = new JSpinner(strokeValues);
        strokeSetter.addChangeListener(this);
        strokeSetter.setVisible(false);

        strokeSetterText = new JLabel("Stroke:");
        strokeSetterText.setVisible(false);

        JButton lineDraw = buttonMaker("", action(e->currentShape.setMode(ShapeModes.LINE)));
        setIcon(lineDraw, "line.png", "Line");
        shapes.add(lineDraw);
        lineDraw.setBackground(Color.decode("#c0cce4"));

        JButton rectangleDraw = buttonMaker("", action(e->currentShape.setMode(ShapeModes.RECTANGLE)));
        setIcon(rectangleDraw, "rectangle.png", "Rectangle");
        shapes.add(rectangleDraw);

        this.add(shapes);

        pencil = buttonMaker("", action(e-> {
            currentShape.setMode(ShapeModes.BRUSH);
            currentShape.setStrokeWidth((Integer) strokeSetter.getValue());

            currentShape.setStroke(new BasicStroke(currentShape.getStrokeWidth(),
                    BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

            strokeSetter.setVisible(true);
            strokeSetterText.setVisible(true);
        }));
        setIcon(pencil, "pencil.png", "Pencil");

        this.add(pencil);
        this.add(strokeSetterText);
        this.add(strokeSetter);

        bucket = buttonMaker("", action(e->currentShape.setMode(ShapeModes.BUCKET)));
        setIcon(bucket, "bucket.png", "Bucket");


        this.add(bucket);

         eraser = buttonMaker("", action(e->{
            currentShape.setMode(ShapeModes.ERASER);
            currentShape.setStrokeWidth((Integer) strokeSetter.getValue());

             currentShape.setStroke(new BasicStroke(currentShape.getStrokeWidth(), BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));

            strokeSetter.setVisible(true);
            strokeSetterText.setVisible(true);
        }));

        setIcon(eraser, "eraser.png", "Eraser");
        this.add(eraser);

        colorChooserMaker(e->{
            Color newColor = JColorChooser.showDialog(
                    Main.getFrame(),
                    "Choose Color",
                    currentShape.getColor());
            if(newColor != null) {
                currentShape.setColor(newColor);
                JButton source = (JButton) e.getSource();
                source.setBackground(currentShape.getColor());
            }
        }, "FG", currentShape.getColor());

        colorChooserMaker(e->{
            Color newColor = JColorChooser.showDialog(
                    Main.getFrame(),
                    "Choose Color",
                    currentShape.getColor());
            if(newColor != null) {
                currentShape.setBgColor(newColor);
                JButton source = (JButton) e.getSource();
                source.setBackground(currentShape.getBgColor());
            }
        }, "BG", currentShape.getBgColor());

    }

    public void setIcon(JButton button, String imgName, String backupText){
        try {
            Image icon = ImageIO.read(new File("./res/img/" + imgName));
            icon = icon.getScaledInstance(15, 16,
                    Image.SCALE_SMOOTH); // scale the image so it fits nicely into the button
            button.setIcon(new ImageIcon(icon));
        } catch (IOException e) {
            button.setText(backupText);
        }
    }

    public void colorChooserMaker(ActionListener action, String text, Color bgColor){
        JButton colorChooser = buttonMaker("", action);
        colorChooser.setBackground(bgColor);
        colorChooser.setFocusPainted(false);
        colorChooser.setAlignmentX(JButton.CENTER_ALIGNMENT);
        colorChooser.setPreferredSize(new Dimension(30,30));

        buttons.remove(colorChooser);

        JPanel colorPanel = new JPanel();
        colorPanel.setLayout(new BoxLayout(colorPanel, BoxLayout.Y_AXIS));
        colorPanel.setPreferredSize(new Dimension(30, 45));
        colorPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        colorPanel.add(colorChooser);
        JLabel colorText = new JLabel(text);
        colorText.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        colorText.setPreferredSize(new Dimension(10,15));
        colorPanel.add(colorText);

        this.add(colorPanel);
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
        newButton.setFocusPainted(false);
        newButton.setPreferredSize(new Dimension(35,35));
        buttons.add(newButton);
        return newButton;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSpinner source = (JSpinner) e.getSource();
        currentShape.setStrokeWidth((Integer) source.getValue());
    }
}
