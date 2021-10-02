package com.company;

import com.company.shapeMaker.ShapeMaker;
import com.company.view.container.ActionBar.ActionBar;
import com.company.view.container.Container;
import com.company.view.container.paint.Paint;
import com.company.view.container.Toolbar;
import com.company.view.container.paint.PaintContainer;

import javax.swing.*;
import java.awt.*;

public class Main {

    private static final JFrame f = new JFrame();
    private static final String APP_NAME = "Paint";

    public static void main(String[] args) {

        f.setTitle(APP_NAME);
        f.setSize(new Dimension(800, 700));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);

        ShapeMaker currentShape = new ShapeMaker();

        Paint paint = new Paint(currentShape);

        PaintContainer paintC = new PaintContainer(paint);

        Toolbar toolbar = new Toolbar(currentShape);

        Container container = new Container(paint, toolbar);
        container.setLayout(new BorderLayout());
        f.add(container);

        paint.setCtrlY(container.getControlY());

        ActionBar actionBar = new ActionBar(paint);
        container.add(actionBar, BorderLayout.NORTH);

        container.add(paintC, BorderLayout.CENTER);
        container.add(toolbar, BorderLayout.SOUTH);

        container.requestFocusInWindow();

        f.revalidate();
        f.repaint();
    }

    public static JFrame getFrame(){
        return Main.f;
    }

}
