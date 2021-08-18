package com.company.keybinds;

import com.company.shapeMaker.ShapeContainer;
import com.company.view.Paint;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ControlY extends AbstractAction {

    ArrayList<ShapeContainer> removedDraws;
    ArrayList<ArrayList<ShapeContainer>> removedFills;
    ArrayList<BufferedImage> removedBucket;

    ArrayList<DeleteModes> whatToRevive;
    Paint paint;



    public ControlY(Paint paint){
        removedDraws = new ArrayList<>();
        removedFills = new ArrayList<>();
        removedBucket = new ArrayList<>();

        whatToRevive = new ArrayList<>();
        this.paint = paint;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(whatToRevive.size() > 0 ){
            switch (whatToRevive.get(whatToRevive.size() - 1)){
                case DRAW -> remove(removedDraws, paint.getShapesToDraw(), DeleteModes.DRAW);
                case FILL -> remove(removedFills, paint.getShapesToFill(), DeleteModes.FILL);
                case BUCKET -> remove(removedBucket, paint.getBucket(), DeleteModes.BUCKET);
            }

            whatToRevive.remove(whatToRevive.size() - 1);
        }
        paint.repaint();
    }

    private <T> void remove(ArrayList<T> removeFrom, ArrayList<T> addTo, DeleteModes mode){
        if(removeFrom.size() > 0) {
            int lastIndex = removeFrom.size() - 1;
            T lastInsert = removeFrom.get(lastIndex);

            addTo.add(lastInsert);

            paint.getAllShapes().add(mode);

            removeFrom.remove(lastIndex);
        }
    }

    public void addToRemovedDraws(ShapeContainer removedDraws) {
        this.removedDraws.add(removedDraws);
        whatToRevive.add(DeleteModes.DRAW);
    }

    public void addToRemovedFills(ArrayList<ShapeContainer> removedFills) {
        this.removedFills.add(removedFills);
        whatToRevive.add(DeleteModes.FILL);
    }

    public void addToRemovedBucket(BufferedImage removedBucket) {
        this.removedBucket.add(removedBucket);
        whatToRevive.add(DeleteModes.BUCKET);
    }

    public void reset(){
        removedFills.clear();
        removedDraws.clear();
        whatToRevive.clear();
    }
}
