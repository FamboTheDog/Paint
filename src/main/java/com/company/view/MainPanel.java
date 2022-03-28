package com.company.view;

import com.company.controlls.keybind.arrows.LeftArrow;
import com.company.controlls.keybind.arrows.RightArrow;
import com.company.controlls.keybind.control.ControlS;
import com.company.controlls.keybind.control.ControlY;
import com.company.controlls.keybind.control.ControlZ;
import com.company.controlls.keybind.paintShortcuts.Template;
import com.company.view.actionbar.ActionBar;
import com.company.view.paint.Paint;
import com.company.view.paint.PaintContainer;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MainPanel extends JPanel {

    @Getter
    private final ControlY controlY;

    public MainPanel(PaintContainer paintContainer, Toolbar toolbar, ActionBar actionBar, ControlY controlY){
        this.controlY = controlY;
        addKeyboardShortcuts(paintContainer.getPaint(), toolbar);
        this.setLayout(new BorderLayout());
        this.add(actionBar, BorderLayout.NORTH);
        this.add(paintContainer, BorderLayout.CENTER);
        this.add(toolbar, BorderLayout.SOUTH);
        this.requestFocusInWindow();
    }

    private void addKeyboardShortcuts(com.company.view.paint.Paint paint, Toolbar toolbar) {
        addControlKeyShortcuts(paint);
        addToolShortcuts(toolbar);
        addArrowShortcuts(toolbar);
    }

    private void addArrowShortcuts(Toolbar toolbar) {
        LeftArrow la = new LeftArrow(toolbar);
        createKeyBinding("LEFT_ARROW", KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0), la);

        RightArrow ra = new RightArrow(toolbar);
        createKeyBinding("RIGHT_ARROW", KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0), ra);
    }

    private void addControlKeyShortcuts(Paint paint) {
        createKeyBinding("CONTROL_Y", KeyStroke.getKeyStroke('Y',
                InputEvent.CTRL_DOWN_MASK), controlY);

        ControlZ controlZ = new ControlZ(paint, controlY);
        createKeyBinding("CONTROL_Z", KeyStroke.getKeyStroke('Z',
                InputEvent.CTRL_DOWN_MASK), controlZ);

        ControlS controlS = new ControlS(paint);
        createKeyBinding("CONTROL_S", KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK), controlS);
    }

    private void addToolShortcuts(Toolbar toolbar) {
        Template p = new Template(toolbar.getPencil());
        createKeyBinding("PENCIL", KeyStroke.getKeyStroke('p'), p);

        Template b = new Template(toolbar.getBucket());
        createKeyBinding("BUCKET", KeyStroke.getKeyStroke('b'), b);

        Template e = new Template(toolbar.getEraser());
        createKeyBinding("ERASER", KeyStroke.getKeyStroke('e'), e);
    }

    private void createKeyBinding(String name, KeyStroke keyStroke,Action action){
        this.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(keyStroke, name);
        this.getActionMap().put(name, action);
    }

}
