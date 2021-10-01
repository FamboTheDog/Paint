package com.company.view.container.ActionBar.FileActions.sizeEdit;

import com.company.view.container.paint.Paint;
import lombok.Getter;

import javax.swing.*;

public class PopupPanel extends JPanel {

    @Getter int selectedWidth;
    @Getter int selectedHeight;

    PopupPanel(Paint paint){

        this.selectedWidth = paint.getWidth();
        this.selectedHeight = paint.getHeight();

        SpinnerModel widthValues  = new SpinnerNumberModel(paint.getWidth(), 1, Integer.MAX_VALUE, 1);
        SpinnerModel heightValues = new SpinnerNumberModel(paint.getHeight(), 1, Integer.MAX_VALUE, 1);

        JSpinner width = new JSpinner(widthValues);
        width.addChangeListener(e -> {
            JSpinner source = (JSpinner) e.getSource();
            selectedWidth = (int) source.getValue();
        });
        JSpinner height = new JSpinner(heightValues);
        height.addChangeListener(e -> {
            JSpinner source = (JSpinner) e.getSource();
            selectedHeight = (int) source.getValue();
        });

        this.add(width);
        this.add(height);
    }
}
