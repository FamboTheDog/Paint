package com.company.view.container.ActionBar.FileActions.sizeEdit;

import com.company.Main;
import com.company.view.container.paint.Paint;
import lombok.AllArgsConstructor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@AllArgsConstructor
public class SizeEdit implements ActionListener {

    Paint paint;

    @Override
    public void actionPerformed(ActionEvent e) {
        new JOptionPane();
        PopupPanel panel = new PopupPanel(paint);
        int popup = JOptionPane.showConfirmDialog(Main.getFrame(), panel, "Select your width and height", JOptionPane.OK_CANCEL_OPTION);

        if(popup == JOptionPane.OK_OPTION){
            Dimension selectedWidth = new Dimension(panel.getSelectedWidth(), panel.getSelectedHeight());
            paint.setSize(selectedWidth);
            paint.setCurrentSize(selectedWidth);
            paint.getPaintC().getScrollPaneContainer().setPreferredSize(selectedWidth);
            paint.getPaintC().revalidate();
            paint.getPaintC().repaint();
        }
    }
}
