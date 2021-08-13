package com.company.keybinds;

import com.company.util.ShapeContainer;
import com.company.view.Paint;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class ControlZ extends AbstractAction {

    private Paint paint;
    private ControlY controlY;

    public ControlZ(Paint paint, ControlY controlY){
        this.paint = paint;
        this.controlY = controlY;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<Object> copy = paint.getAllShapes();
        if(copy.size() > 0){
            Object toRemove = copy.get(copy.size() - 1);

            if(toRemove.getClass() == ShapeContainer.class){
                ArrayList<ShapeContainer> c = paint.getShapesToDraw();
                controlY.setRemovedDraws(new ShapeContainer(c.get(c.size() - 1).getColor(), c.get(c.size() - 1).getShape()));
                if(c.size() > 0) c.remove(c.size() - 1);
            }else{
                ArrayList<ArrayList<ShapeContainer>> c = paint.getShapesToFill();
                controlY.setRemovedFills(new ArrayList<>(c.get(c.size() - 1)));
                if(c.size() > 0) c.remove(c.size() - 1);
            }

            copy.remove(toRemove);
        }

        paint.repaint();
    }
}
