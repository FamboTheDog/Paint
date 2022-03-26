package com.company.program_controlls.keybinds.paintShortcuts;

import com.company.view.container.Toolbar;
import lombok.AllArgsConstructor;

import javax.swing.*;
import java.awt.event.ActionEvent;

@AllArgsConstructor
public class Template extends AbstractAction {
    Toolbar toolbar;
    JButton toClick;

    @Override
    public void actionPerformed(ActionEvent e){
        toClick.doClick();
    }
}
