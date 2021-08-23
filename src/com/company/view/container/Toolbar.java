package com.company.view.container;

import com.company.Main;
import com.company.shapeMaker.ShapeMaker;
import com.company.shapeMaker.ShapeModes;

import javax.imageio.ImageIO;
import javax.swing.*;
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
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JLabel strokeSetterText;
    private JSpinner strokeSetter;

    private ArrayList<JButton> buttons;

    private void init() throws IOException {
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


        JButton brush = buttonMaker("Pencil", action(e-> {
            currentShape.setMode(ShapeModes.BRUSH);
            currentShape.setStrokeWidth((Integer) strokeSetter.getValue());

            strokeSetter.setVisible(true);
            strokeSetterText.setVisible(true);
        }));

        this.add(brush);
        this.add(strokeSetterText);
        this.add(strokeSetter);

        JButton bucket = buttonMaker("", action(e->currentShape.setMode(ShapeModes.BUCKET)));
        Image bucketIcon = ImageIO.read(new File("./res/img/bucket.png"));
        bucketIcon = bucketIcon.getScaledInstance(15, 16, Image.SCALE_SMOOTH);
        bucket.setIcon(new ImageIcon(bucketIcon));

        this.add(bucket);

        JButton eraser = buttonMaker("Eraser", action(e->{
            currentShape.setMode(ShapeModes.ERASER);
            currentShape.setStrokeWidth((Integer) strokeSetter.getValue());

            strokeSetter.setVisible(true);
            strokeSetterText.setVisible(true);
        }));
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

    public void colorChooserMaker(ActionListener action, String text, Color bgColor){
        JButton colorChooser = buttonMaker(" ", action);
        colorChooser.setBackground(bgColor);
        colorChooser.setFocusPainted(false);
        colorChooser.setAlignmentX(JButton.CENTER_ALIGNMENT);

        buttons.remove(colorChooser);


        JPanel colorPanel = new JPanel();
        colorPanel.setLayout(new BoxLayout(colorPanel, BoxLayout.Y_AXIS));
        colorPanel.setSize(new Dimension(50, 50));
        colorPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        colorPanel.add(colorChooser);
        JLabel colorText = new JLabel(text);
        colorText.setAlignmentX(JLabel.CENTER_ALIGNMENT);
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
        buttons.add(newButton);
        return newButton;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSpinner source = (JSpinner) e.getSource();
        currentShape.setStrokeWidth((Integer) source.getValue());
    }
}
