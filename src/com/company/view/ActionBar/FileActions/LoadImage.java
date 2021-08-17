package com.company.view.ActionBar.FileActions;

import com.company.Main;
import com.company.view.Paint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoadImage implements ActionListener {

    Paint paint;
    public LoadImage(Paint paint){
        this.paint = paint;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        FileDialog fileExplorer = new FileDialog(Main.getFrame(), "Save image", FileDialog.LOAD);
        fileExplorer.setDirectory(System.getProperty("user.dir"));
        fileExplorer.setFile("*.jpg");
        fileExplorer.setVisible(true);

        if(fileExplorer.getFile() != null){
            File loadedFile = new File(fileExplorer.getDirectory() + fileExplorer.getFile());
            try {
                BufferedImage loadedImage = ImageIO.read(loadedFile);
                paint.addToLoadedImages(loadedImage);
                paint.repaint();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
}
