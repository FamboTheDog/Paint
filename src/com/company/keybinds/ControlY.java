package com.company.keybinds;

import com.company.util.ShapeContainer;
import com.company.view.Paint;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class ControlY extends AbstractAction {

    // TODO lastRemovedDraw and lastRemovedFill story only one value, turn it into an arraylist to store more

    ShapeContainer lastRemovedDraw;
    long lastRemovedDrawTime;
    ArrayList<ShapeContainer> lastRemovedFill;
    long lastRemovedFillTime;
    Paint paint;



    public ControlY(Paint paint){
        lastRemovedDraw = null;
        this.paint = paint;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(lastRemovedDrawTime > lastRemovedFillTime){
            if(lastRemovedDraw != null) {
                paint.getShapesToDraw().add(lastRemovedDraw);
                paint.getAllShapes().add(new ShapeContainer(lastRemovedDraw.getColor(), lastRemovedDraw.getShape()));
                lastRemovedDraw = null;
            }
        }else{
            if(lastRemovedFill != null) {
                paint.getShapesToFill().add(lastRemovedFill);
                paint.getAllShapes().add(new ArrayList<>(lastRemovedFill));
                lastRemovedFill = null;
            }
        }

        paint.repaint();
    }

    public void setLastRemovedDraw(ShapeContainer lastRemovedDraw) {
        this.lastRemovedDraw = lastRemovedDraw;
        lastRemovedDrawTime = System.currentTimeMillis();
    }

    public void setLastRemovedFill(ArrayList<ShapeContainer> lastRemovedFill) {
        this.lastRemovedFill = lastRemovedFill;
        lastRemovedFillTime = System.currentTimeMillis();
    }
}
