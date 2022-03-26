package com.company.controlls.keybind.control;

import com.company.shapemaker.ShapeContainer;
import com.company.view.container.paint.Paint;
import lombok.Getter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class ControlY extends AbstractAction {

    @Getter private final ArrayList<ShapeContainer> whatToRevive = new ArrayList<>();
    Paint paint;

    public ControlY(Paint paint){
        this.paint = paint;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(whatToRevive.size() > 0 ){
            paint.getMouse().getToPaint().add(whatToRevive.get(whatToRevive.size() - 1 ));
            whatToRevive.remove(whatToRevive.size() - 1);
        }
        paint.repaint();
    }

    public void reset(){
        whatToRevive.clear();
    }
}
