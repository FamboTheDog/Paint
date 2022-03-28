package com.company.view.container.actionbar.image_actions;

import lombok.Getter;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SaveDialog extends JPanel implements ChangeListener {

    @Getter int compression = 9;

    SaveDialog() {
        JLabel qualityText = new JLabel("Quality: ");
        this.add(qualityText);
        JSlider quality = new JSlider(1,10, 9);
        quality.setSnapToTicks(true);
        quality.setMajorTickSpacing(1);
        quality.setPaintTicks(true);
        quality.setPaintLabels(true);
        quality.addChangeListener(this);
        this.add(quality);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        this.compression = source.getValue();
    }
}
