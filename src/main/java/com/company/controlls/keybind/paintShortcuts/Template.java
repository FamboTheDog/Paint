package com.company.controlls.keybind.paintShortcuts;

import lombok.AllArgsConstructor;

import javax.swing.*;
import java.awt.event.ActionEvent;

@AllArgsConstructor
public class Template extends AbstractAction {

    private JButton toClick;

    @Override
    public void actionPerformed(ActionEvent e){
        toClick.doClick();
    }
}
