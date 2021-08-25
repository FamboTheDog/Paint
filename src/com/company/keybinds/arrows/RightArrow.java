package com.company.keybinds.arrows;

import com.company.view.container.Toolbar;
import lombok.AllArgsConstructor;

import javax.swing.*;
import java.awt.event.ActionEvent;

@AllArgsConstructor
public class RightArrow extends AbstractAction {
    Toolbar toolbar;
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton[] buttons = toolbar.getShapeButtons();
        for (int i = 0; i < buttons.length; i++) {
            if(buttons[i].getBackground() == toolbar.getClickedColor()){
                if(i + 1 < buttons.length){
                    buttons[i].setBackground(new JButton().getBackground());
                    buttons[i + 1].doClick();
                }
                break;
            }
        }
    }
}
