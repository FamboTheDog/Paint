package com.company.program_controlls.keybinds.control;

import com.company.view.container.ActionBar.image_actions.SaveImage;
import com.company.view.container.paint.Paint;
import lombok.AllArgsConstructor;

import javax.swing.*;
import java.awt.event.ActionEvent;

@AllArgsConstructor
public class ControlS extends AbstractAction {

    Paint paint;

    @Override
    public void actionPerformed(ActionEvent e) {
        SaveImage imageSaver = new SaveImage(paint);
        imageSaver.createJPGImage();
    }
}
