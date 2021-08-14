package com.company.view.ActionBar;

import com.company.view.Paint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveImage implements ActionListener {

    Paint paint;
    SaveImage(Paint paint){
        this.paint = paint;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("save, haha");

        paint.createImage();
    }
}
