package com.company.controlls.keybind.control;

import com.company.shapemaker.ShapeContainer;
import com.company.view.container.paint.Paint;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

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
//        ArrayList<ShapeContainer> toPaintCopy = paint.getMouse().getToPaint();
//        if(toPaintCopy.size() > 0){
//            ShapeContainer removedContainer = toPaintCopy.get(toPaintCopy.size() - 1);
//
//            controlY.getWhatToRevive().add(removedContainer);
//
//            toPaintCopy.remove(toPaintCopy.size() - 1);
//
//            paint.repaint();
//        }
    }

}
