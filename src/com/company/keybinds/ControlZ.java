package com.company.keybinds;

import com.company.shapeMaker.ShapeContainer;
import com.company.view.Paint;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
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
        ArrayList<DeleteModes> allShapes = paint.getAllShapes();
        if(allShapes.size() > 0){
            DeleteModes toRemove = allShapes.get(allShapes.size() - 1);
            switch (toRemove) {
                case DRAW -> {
                    ArrayList<ShapeContainer> c = paint.getShapesToDraw();
                    controlY.addToRemovedDraws((c.get(c.size() - 1)));
                    c.remove(c.size() - 1);
                }
                case FILL -> {
                    ArrayList<ArrayList<ShapeContainer>> c2 = paint.getShapesToFill();
                    controlY.addToRemovedFills(c2.get(c2.size() - 1));
                    c2.remove(c2.size() - 1);
                }
                case BUCKET -> {
                    ArrayList<BufferedImage> c3 = paint.getBucket();
                    controlY.addToRemovedBucket(c3.get(c3.size() - 1));
                    c3.remove(c3.size() - 1);
                }
            }

            allShapes.remove(toRemove);
        }

        paint.repaint();
    }

}
