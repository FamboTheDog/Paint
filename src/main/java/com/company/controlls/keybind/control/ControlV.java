package com.company.controlls.keybind.control;

import com.company.view.paint.Paint;
import lombok.AllArgsConstructor;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

@AllArgsConstructor
public class ControlV extends AbstractAction {

    private Paint paint;

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("sex");
        BufferedImage loadedImage = getImageFromClipboard();
        if (loadedImage != null) paint.addToLoadedImages(loadedImage);
        paint.repaint();
    }

    public BufferedImage getImageFromClipboard() {
        Transferable transferable = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
        if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.imageFlavor)) {
            try {
                return (BufferedImage) transferable.getTransferData(DataFlavor.imageFlavor);
            } catch (UnsupportedFlavorException | IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
