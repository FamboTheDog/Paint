package com.company;

import com.company.controlls.keybind.control.ControlY;
import com.company.controlls.mouselistener.BrushListener;
import com.company.controlls.mouselistener.shapelisneter.EllipseListener;
import com.company.view.container.actionbar.ActionBar;
import com.company.view.container.MainPanel;
import com.company.view.container.paint.Paint;
import com.company.view.container.Toolbar;
import com.company.view.container.paint.PaintContainer;

import javax.swing.*;
import java.awt.*;

public class Main {

    private static final JFrame frame = new JFrame();
    private static final String APP_NAME = "Paint";

    public static void main(String[] args) {
        initializeFrame();

        Paint paint = new Paint();
        PaintContainer paintContainer = new PaintContainer(paint);
        ControlY controlY = new ControlY(paint);

        Toolbar toolbar = new Toolbar(paint, controlY);

        ActionBar actionBar = new ActionBar(paint);

        MainPanel mainPanel = new MainPanel(paintContainer, toolbar, actionBar, controlY);
        frame.add(mainPanel);

        frame.revalidate();
        frame.repaint();
    }

    private static void initializeFrame() {
        frame.setTitle(APP_NAME);
        frame.setSize(new Dimension(800, 700));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static JFrame getFrame(){
        return Main.frame;
    }

}
