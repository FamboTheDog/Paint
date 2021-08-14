package com.company.view.ActionBar;

import com.company.view.Paint;

import javax.swing.*;
import java.awt.*;

public class ActionBar extends JPanel {


    com.company.view.Paint paint;
    public ActionBar(Paint paint){
        this.paint = paint;
        init();
    }

    private void init() {
        this.setBackground(Color.lightGray);
        FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
        layout.setVgap(0);
        layout.setHgap(0);
        this.setLayout(layout);

        JMenuBar menuBar = new JMenuBar();

        JMenu fileOpt = new JMenu("File");
        JMenuItem save = new JMenuItem("Save as jpg");
        SaveImage saveImage = new SaveImage(paint);
        save.addActionListener(saveImage);
        fileOpt.add(save);

        menuBar.add(fileOpt);

        this.add(menuBar);
    }
}
