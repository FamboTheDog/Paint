package com.company;

import com.company.shapeMaker.ShapeMaker;
import com.company.view.ActionBar.ActionBar;
import com.company.view.CanvasContainer;
import com.company.view.Paint;
import com.company.view.Toolbar.Toolbar;

import javax.swing.*;
import java.awt.*;

public class Main {

    private final static JFrame f = new JFrame();

    public static void main(String[] args) {
        final String APP_NAME = "Paint";

        f.setTitle(APP_NAME);
        f.setSize(new Dimension(640, 480));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setVisible(true);

        ShapeMaker currentShape = new ShapeMaker();

        Paint paint = new Paint(currentShape);

        CanvasContainer canvasContainer = new CanvasContainer(paint);
        canvasContainer.setLayout(new BorderLayout());
        f.add(canvasContainer);

        paint.setCtrlY(canvasContainer.getControlY());

        ActionBar actionBar = new ActionBar(paint);
        canvasContainer.add(actionBar, BorderLayout.NORTH);

        canvasContainer.add(paint, BorderLayout.CENTER);

        Toolbar toolbar = new Toolbar(currentShape);
        canvasContainer.add(toolbar, BorderLayout.SOUTH);

        canvasContainer.requestFocusInWindow();

        f.revalidate();
        f.repaint();
    }

    public static JFrame getFrame(){
        return Main.f;
    }

}
