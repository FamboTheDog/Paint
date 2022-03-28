package com.company.utility;

import javax.swing.*;

public class KeyBindingUtil {
    public static void createKeyBinding(JComponent component, String name, KeyStroke keyStroke, Action action) {
        component.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(keyStroke, name);
        component.getActionMap().put(name, action);
    }
}
