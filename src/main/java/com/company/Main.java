package com.company;

import com.company.shapemaker.ShapeMaker;
import com.company.view.container.actionbar.ActionBar;
import com.company.view.container.Container;
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

        Container container = new Container(paint, toolbar);
        container.setLayout(new BorderLayout());
        frame.add(container);

        paint.setCtrlY(container.getControlY());

        ActionBar actionBar = new ActionBar(paint);
        container.add(actionBar, BorderLayout.NORTH);

        container.add(paintContainer, BorderLayout.CENTER);
        container.add(toolbar, BorderLayout.SOUTH);

        container.requestFocusInWindow();

        frame.revalidate();
        frame.repaint();
    }

    public static JFrame getFrame(){
        return Main.frame;
    }

}
