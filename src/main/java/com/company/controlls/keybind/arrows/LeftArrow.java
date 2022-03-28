package com.company.controlls.keybind.arrows;

import com.company.view.Toolbar;
import lombok.AllArgsConstructor;

import javax.swing.*;
import java.awt.event.ActionEvent;

@AllArgsConstructor
public class LeftArrow extends AbstractAction {

    Toolbar toolbar;

    @Override
    public void actionPerformed(ActionEvent e) {
        // O(N) solution in the worst scenario, if I add more shapes, I should start storing current selected shape
        JButton[] buttons = toolbar.getShapeButtons();
        for (int i = 0; i < buttons.length; i++) {
            if(buttons[i].getBackground() == toolbar.getClickedColor()){
                if(i > 0){
                    buttons[i].setBackground(new JButton().getBackground());
                    buttons[i - 1].doClick();
                }
                break;
            }
        }
    }
}
