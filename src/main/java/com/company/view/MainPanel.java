package com.company.view;

import com.company.controlls.keybind.arrows.LeftArrow;
import com.company.controlls.keybind.arrows.RightArrow;
import com.company.controlls.keybind.control.ControlS;
import com.company.controlls.keybind.control.ControlV;
import com.company.controlls.keybind.control.ControlY;
import com.company.controlls.keybind.control.ControlZ;
import com.company.controlls.keybind.paintShortcuts.Template;
import com.company.controlls.mouselistener.scrolllistener.ZoomManager;
import com.company.utility.KeyBindingUtil;
import com.company.view.actionbar.ActionBar;
import com.company.view.paint.Paint;
import com.company.view.paint.PaintContainer;
import lombok.Getter;

import javax.swing.*;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.awt.*;
import java.awt.event.ActionEvent;
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
        KeyBindingUtil.createKeyBinding(this, "LEFT_ARROW", KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0), la);

        RightArrow ra = new RightArrow(toolbar);
        KeyBindingUtil.createKeyBinding(this, "RIGHT_ARROW", KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0), ra);
    }

    private void addControlKeyShortcuts(Paint paint) {
        KeyBindingUtil.createKeyBinding(this, "CONTROL_Y", KeyStroke.getKeyStroke('Y', InputEvent.CTRL_DOWN_MASK), controlY);

        ControlZ controlZ = new ControlZ(paint, controlY);
        KeyBindingUtil.createKeyBinding(this, "CONTROL_Z", KeyStroke.getKeyStroke('Z', InputEvent.CTRL_DOWN_MASK), controlZ);

        ControlS controlS = new ControlS(paint);
        KeyBindingUtil.createKeyBinding(this, "CONTROL_S", KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK), controlS);

        ControlV controlV = new ControlV(paint);
        KeyBindingUtil.createKeyBinding(this, "CONTROL_V", KeyStroke.getKeyStroke('V', InputEvent.CTRL_DOWN_MASK), controlV);
    }

    private void addToolShortcuts(Toolbar toolbar) {
        Template p = new Template(toolbar.getPencil());
        KeyBindingUtil.createKeyBinding(this, "PENCIL", KeyStroke.getKeyStroke('p'), p);

        Template b = new Template(toolbar.getBucket());
        KeyBindingUtil.createKeyBinding(this, "BUCKET", KeyStroke.getKeyStroke('b'), b);

    }


}
