package com.company.view.container.ActionBar.FileActions;

import com.company.Main;
import com.company.view.container.paint.Paint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SaveImage implements ActionListener {

    Paint paint;
    public SaveImage(Paint paint){
        this.paint = paint;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        createImage();
    }

    public void createImage(){
        FileDialog fileExplorer = new FileDialog(Main.getFrame(), "Save image", FileDialog.SAVE);
        fileExplorer.setDirectory(System.getProperty("user.dir"));
        fileExplorer.setFile("*.jpg");
        fileExplorer.setVisible(true);
        String filename = fileExplorer.getFile();
        String directory = fileExplorer.getDirectory();

        if (filename != null){
            BufferedImage bImg = new BufferedImage(paint.getWidth(), paint.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D cg = bImg.createGraphics();
            paint.paintAll(cg);

            try {
                if (ImageIO.write(bImg, "jpg", new File(directory + filename))) {
                    System.out.println("image saved");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
