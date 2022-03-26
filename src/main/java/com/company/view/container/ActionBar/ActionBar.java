package com.company.view.container.ActionBar;

import com.company.view.container.ActionBar.image_actions.LoadImage;
import com.company.view.container.ActionBar.image_actions.SaveImage;
import com.company.view.container.ActionBar.FileActions.sizeEdit.SizeEdit;
import com.company.view.container.paint.Paint;

import javax.swing.*;
import java.awt.*;

public class ActionBar extends JPanel {


    Paint paint;
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

        JMenuItem load = new JMenuItem("Load image");
        LoadImage loadImage = new LoadImage(paint);
        load.addActionListener(loadImage);

        fileOpt.add(save);
        fileOpt.add(load);

        menuBar.add(fileOpt);

        JMenu editOpt = new JMenu("Edit");

        JMenuItem setSize = new JMenuItem("Set project size");
        setSize.addActionListener(new SizeEdit(paint));

        editOpt.add(setSize);

        menuBar.add(editOpt);

        this.add(menuBar);
    }

}
