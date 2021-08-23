package com.company.keybinds;

import com.company.view.container.Toolbar;
import lombok.AllArgsConstructor;

import javax.swing.*;
import java.awt.event.ActionEvent;

@AllArgsConstructor
public class P extends AbstractAction {

    Toolbar toolbar;

    @Override
    public void actionPerformed(ActionEvent e) {
        toolbar.getPencil().doClick();
    }
}
