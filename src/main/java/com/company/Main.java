package com.company;

import com.company.controlls.keybind.control.ControlY;
import com.company.controlls.mouselistener.BucketListener;
import com.company.controlls.mouselistener.LineListener;
import com.company.drawable.Line;
import com.company.shapemaker.ShapeMaker;
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
        frame.setTitle(APP_NAME);
        frame.setSize(new Dimension(800, 700));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        ShapeMaker shapeMaker = new ShapeMaker();
        Paint paint = new Paint(shapeMaker);
        PaintContainer paintContainer = new PaintContainer(paint);

        Toolbar toolbar = new Toolbar(shapeMaker);

        ActionBar actionBar = new ActionBar(paint);

        MainPanel mainPanel = new MainPanel(paintContainer, toolbar, actionBar);
        frame.add(mainPanel);
        paint.setCtrlY(mainPanel.getControlY());
        LineListener lineListener = new LineListener(paint, mainPanel.getControlY());
        paint.addMouseListener(lineListener);
        paint.addMouseMotionListener(lineListener);

        frame.revalidate();
        frame.repaint();
    }

    public static JFrame getFrame(){
        return Main.frame;
    }

}
