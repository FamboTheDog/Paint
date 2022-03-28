package com.company.controlls.keybind.control;

import com.company.drawable.Drawable;
import com.company.view.paint.Paint;
import lombok.Getter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class ControlY extends AbstractAction {

    @Getter
    private final ArrayList<Drawable> whatToRevive = new ArrayList<>();

    private final Paint paint;

    public ControlY(Paint paint){
        this.paint = paint;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(whatToRevive.size() > 0 ){
            paint.getDrawables().add(whatToRevive.get(whatToRevive.size() - 1));
            whatToRevive.remove(whatToRevive.size() - 1);
        }
        paint.repaint();
    }

    public void reset(){
        whatToRevive.clear();
    }
}
