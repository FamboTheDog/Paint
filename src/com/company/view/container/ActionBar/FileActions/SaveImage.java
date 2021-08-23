package com.company.view.container.ActionBar.FileActions;

import com.company.view.container.paint.Paint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveImage implements ActionListener {

    Paint paint;
    public SaveImage(Paint paint){
        this.paint = paint;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        paint.createImage();
    }
}
