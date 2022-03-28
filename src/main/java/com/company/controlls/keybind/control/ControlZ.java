package com.company.controlls.keybind.control;

import com.company.view.paint.Paint;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ControlZ extends AbstractAction {

    private final Paint paint;
    private final ControlY controlY;

    public ControlZ(Paint paint, ControlY controlY){
        this.paint = paint;
        this.controlY = controlY;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO refactor
        if (paint.getDrawables().size() > 0) {
            int index = paint.getDrawables().size() - 1;
            controlY.getWhatToRevive().add(paint.getDrawables().get(index));
            paint.getDrawables().remove(index);
            paint.repaint();
        }
    }

}
