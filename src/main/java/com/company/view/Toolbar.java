package com.company.view;

import com.company.Main;
import com.company.controlls.keybind.control.ControlY;
import com.company.controlls.mouselistener.BrushListener;
import com.company.controlls.mouselistener.BucketListener;
import com.company.controlls.mouselistener.shapelisneter.EllipseListener;
import com.company.controlls.mouselistener.shapelisneter.LineListener;
import com.company.controlls.mouselistener.shapelisneter.RectangleListener;
import com.company.view.paint.Paint;
import lombok.Getter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Toolbar extends JPanel implements ChangeListener {

    private final com.company.view.paint.Paint paint;
    private final ControlY controlY;

    private LineListener lineListener;
    private RectangleListener rectangleListener;
    private EllipseListener ellipseListener;
    private BucketListener bucketListener;
    private BrushListener brushListener;

    public Toolbar(Paint paint, ControlY controlY){
        this.paint = paint;
        this.controlY = controlY;
        initializeListeners();
        init();
    }

    private void initializeListeners() {
        lineListener = new LineListener(paint, controlY);
        rectangleListener = new RectangleListener(paint, controlY);
        ellipseListener = new EllipseListener(paint, controlY);
        bucketListener = new BucketListener(paint);
        brushListener = new BrushListener(paint, controlY);
    }

    private JLabel strokeSetterText;
    private JSpinner strokeSetter;

    private ArrayList<JButton> buttons;

    @Getter
    private JButton pencil;
    @Getter
    private JButton bucket;
    @Getter
    private JButton eraser;
    @Getter
    private JButton[] shapeButtons;
    @Getter
    private final Color clickedColor = Color.decode("#c0cce4");

    private void init() {
        buttons = new ArrayList<>();

        this.setBackground(Color.lightGray);

        addShapeBox();

        pencil = buttonMaker(action(e-> {
            paint.switchToListener(brushListener);
            paint.setStroke(new BasicStroke(paint.getStrokeWidth(),
                    BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        }));
        setIcon(pencil, "pencil.png", "Pencil");

        this.add(pencil);
        this.add(strokeSetterText);
        this.add(strokeSetter);

        bucket = buttonMaker(action(e-> paint.switchToListener(bucketListener)));
        setIcon(bucket, "bucket.png", "Bucket");

        this.add(bucket);

        addColorListeners();
    }

    private void addShapeBox() {
        JPanel shapes = new JPanel();
        shapes.setBorder(BorderFactory.createLineBorder(Color.black));

        SpinnerModel strokeValues = new SpinnerNumberModel(5, 1, 99, 1);
        strokeSetter = new JSpinner(strokeValues);
        strokeSetter.addChangeListener(this);
        Integer strokeWidth = (Integer) strokeSetter.getValue();
        paint.setStrokeWidth(strokeWidth);
        paint.setStroke(new BasicStroke(strokeWidth));

        strokeSetterText = new JLabel("Stroke:");

        shapeButtons = new JButton[3];
        JButton lineDraw = buttonMaker(action(e -> paint.switchToListener(lineListener)));
        setIcon(lineDraw, "line.png", "Line");
        shapes.add(lineDraw);
        lineDraw.setBackground(clickedColor);
        lineDraw.doClick();
        shapeButtons[0] = lineDraw;

        JButton rectangleDraw = buttonMaker(action(e -> paint.switchToListener(rectangleListener)));
        setIcon(rectangleDraw, "rectangle.png", "Rectangle");
        shapes.add(rectangleDraw);
        shapeButtons[1] = rectangleDraw;

        JButton circleDraw = buttonMaker(action(e -> paint.switchToListener(ellipseListener)));
        setIcon(circleDraw, "circle.png", "Circle");
        shapes.add(circleDraw);
        shapeButtons[2] = circleDraw;

        this.add(shapes);

    }

    private void addColorListeners() {
        colorChooserMaker(e->{
            Color newColor = JColorChooser.showDialog(
                    Main.getFrame(),
                    "Choose Color",
                    paint.getColor());
            if(newColor != null) {
                paint.setColor(newColor);
                JButton source = (JButton) e.getSource();
                source.setBackground(paint.getColor());
            }
        }, "FG", paint.getColor());

        colorChooserMaker(e->{
            Color newColor = JColorChooser.showDialog(
                    Main.getFrame(),
                    "Choose Color",
                    paint.getColor());
            if(newColor != null) {
                paint.setBgColor(newColor);
                JButton source = (JButton) e.getSource();
                source.setBackground(paint.getBgColor());
            }
        }, "BG", paint.getBgColor());
    }

    public void setIcon(JButton button, String imgName, String backupText){
        try {
            Image icon = ImageIO.read(new File("./res/img/" + imgName));
            icon = icon.getScaledInstance(15, 16,
                    Image.SCALE_SMOOTH); // scale the image so it fits nicely into the button
            button.setIcon(new ImageIcon(icon));
        } catch (IOException e) {
            button.setPreferredSize(null);
            button.setText(backupText);
        }
    }

    public void colorChooserMaker(ActionListener action, String text, Color bgColor){
        JButton colorChooser = buttonMaker(action);
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
            source.setBackground(clickedColor);

            action.actionPerformed(e);
        };
    }

    private JButton buttonMaker(ActionListener action){
        JButton newButton = new JButton();
        newButton.addActionListener(action);
        newButton.setFocusPainted(false);
        newButton.setPreferredSize(new Dimension(35,35));
        buttons.add(newButton);
        return newButton;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSpinner source = (JSpinner) e.getSource();
        paint.setStrokeWidth((Integer) source.getValue());
        MouseListener currentMouseListener = paint.getMouseListeners()[0];
        if (currentMouseListener instanceof BrushListener) {
            paint.setStroke(new BasicStroke(paint.getStrokeWidth(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        } else if (currentMouseListener instanceof RectangleListener || currentMouseListener instanceof EllipseListener ||
                    currentMouseListener instanceof LineListener) {
            paint.setStroke(new BasicStroke(paint.getStrokeWidth()));
        }
    }
}
