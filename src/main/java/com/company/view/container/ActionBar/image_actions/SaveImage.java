package com.company.view.container.ActionBar.image_actions;

import com.company.Main;
import com.company.view.container.paint.Paint;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*
* It could be interesting to save metadata as well
* */
public class SaveImage implements ActionListener {

    Paint paint;
    public SaveImage(Paint paint){
        this.paint = paint;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        createJPGImage();
    }

    public void createJPGImage(){
        SaveDialog dialog = new SaveDialog();
        int popup = JOptionPane.showConfirmDialog(Main.getFrame(), dialog, "Select your width and height", JOptionPane.OK_CANCEL_OPTION);
        if (popup != 0) return;

        float compressionLevel = (float) dialog.getCompression() / 10;

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
                ImageWriter jpgWriter = ImageIO.getImageWritersByFormatName("jpg").next();
                ImageWriteParam jpgWriteParam = jpgWriter.getDefaultWriteParam();
                jpgWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                jpgWriteParam.setCompressionQuality(compressionLevel);

                ImageOutputStream outputStream = new FileImageOutputStream(new File(directory + filename));
                jpgWriter.setOutput(outputStream);
                IIOImage outputImage = new IIOImage(bImg, null, null);
                jpgWriter.write(null, outputImage, jpgWriteParam);
                jpgWriter.dispose();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
