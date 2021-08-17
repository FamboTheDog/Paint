package com.company.keybinds;

import com.company.shapeMaker.ShapeContainer;
import com.company.view.Paint;

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
        ArrayList<Object> allShapes = paint.getAllShapes();
        if(allShapes.size() > 0){
            Object toRemove = allShapes.get(allShapes.size() - 1);

            if(toRemove.getClass() == ShapeContainer.class){
                ArrayList<ShapeContainer> c = paint.getShapesToDraw();
                controlY.setRemovedDraws(new ShapeContainer(c.get(c.size() - 1).getColor(), c.get(c.size() - 1).getShape()));
                if(c.size() > 0) c.remove(c.size() - 1);
            }else{
                ArrayList<ArrayList<ShapeContainer>> c = paint.getShapesToFill();
                controlY.setRemovedFills(new ArrayList<>(c.get(c.size() - 1)));
                if(c.size() > 0) c.remove(c.size() - 1);
            }

            allShapes.remove(toRemove);
        }

        paint.repaint();
    }

}
