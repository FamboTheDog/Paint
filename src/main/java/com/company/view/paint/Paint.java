package com.company.view.paint;

import com.company.controlls.mouselistener.scrolllistener.ZoomManager;
import com.company.drawable.Drawable;
import com.company.drawable.Image;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Paint extends JPanel {

    @Getter
    @Setter
    private PaintContainer paintC;

    @Getter
    @Setter
    double scale = 1;

    @Getter
    private final ZoomManager zoomManager;

    @Getter
    @Setter
    private Dimension currentSize = new Dimension(640,480);

    @Getter
    private final ArrayList<Drawable> drawables = new ArrayList<>();

    @Getter
    @Setter
    private Color color = Color.BLACK;

    @Getter
    @Setter
    private Color BgColor = Color.WHITE;

    @Getter
    @Setter
    private Stroke stroke;

    @Getter
    @Setter
    private int strokeWidth;

    public Paint(){
        Dimension defaultSize = currentSize;
        this.setSize(defaultSize);

        this.zoomManager = new ZoomManager(this);
        this.addMouseWheelListener(zoomManager);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D gd = (Graphics2D) g;

        gd.setColor(Color.white);
        gd.fillRect(0, 0, getWidth(), getHeight());

        gd.scale(scale, scale);

        gd.setColor(Color.black);
        drawables.forEach(drawable -> drawable.draw(gd));

        gd.dispose();
    }

    public void addToLoadedImages(BufferedImage img){
        this.drawables.add(new Image(img));
    }

    public void switchToListener(MouseAdapter lineListener) {
        for (MouseListener mouseListener : this.getMouseListeners()) {
            this.removeMouseListener(mouseListener);
        }
        for (MouseMotionListener mouseMotionListener : this.getMouseMotionListeners()) {
            this.removeMouseMotionListener(mouseMotionListener);
        }
        this.addMouseListener(lineListener);
        this.addMouseMotionListener(lineListener);
    }
}
