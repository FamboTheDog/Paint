package com.company.keybinds;

import com.company.util.ShapeContainer;
import com.company.view.Paint;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class ControlY extends AbstractAction {

    /*
    * TO THINK ABOUT:
    *   make a maximum length for the arraylists?
    * */

    ArrayList<ShapeContainer> removedDraws;
    ArrayList<ArrayList<ShapeContainer>> removedFills;
    ArrayList<Boolean> whatToRevive;
    Paint paint;



    public ControlY(Paint paint){
        removedDraws = new ArrayList<>();
        removedFills = new ArrayList<>();
        whatToRevive = new ArrayList<>();
        this.paint = paint;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(whatToRevive.size() > 0 ){
            if (whatToRevive.get(whatToRevive.size() - 1)){
                if(removedDraws.size() > 0) {
                    int lastIndex = removedDraws.size() - 1;
                    ShapeContainer lastInsert = removedDraws.get(lastIndex);
                    paint.getShapesToDraw().add(lastInsert);
                    paint.getAllShapes().add(new ShapeContainer(lastInsert.getColor(), lastInsert.getShape()));
                    removedDraws.remove(lastIndex);
                }
            }else{
                if(removedFills.size() > 0) {
                    int lastIndex = removedFills.size() - 1;
                    ArrayList<ShapeContainer> lastInsert = removedFills.get(lastIndex);
                    paint.getShapesToFill().add(lastInsert);
                    paint.getAllShapes().add(new ArrayList<>(removedFills.get(lastIndex)));
                    removedFills.remove(lastIndex);
                }
            }
            whatToRevive.remove(whatToRevive.size() - 1);
        }
        paint.repaint();
    }

    public void setRemovedDraws(ShapeContainer removedDraws) {
        this.removedDraws.add(removedDraws);
        whatToRevive.add(true);
    }

    public void setRemovedFills(ArrayList<ShapeContainer> removedFills) {
        this.removedFills.add(removedFills);
        whatToRevive.add(false);
    }
}
