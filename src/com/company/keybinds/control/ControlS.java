package com.company.keybinds.control;

import com.company.view.container.paint.Paint;
import lombok.AllArgsConstructor;

import javax.swing.*;
import java.awt.event.ActionEvent;

@AllArgsConstructor
public class ControlS extends AbstractAction {

    Paint paint;

    @Override
    public void actionPerformed(ActionEvent e) {
        paint.createImage();
    }
}
