package com.company.view.container.paint;

import com.company.controlls.keybind.control.ControlY;
import com.company.controlls.mouselistener.PaintMouseListener;
import com.company.controlls.mouselistener.ZoomManager;
import com.company.drawable.Drawable;
import com.company.drawable.DrawableShape;
import com.company.shapemaker.ShapeContainer;
import com.company.shapemaker.ShapeMaker;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Paint extends JPanel {

    private final ShapeMaker shapeMaker;

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
    private PaintMouseListener mouse;

    @Getter
    private final ArrayList<Drawable> drawables = new ArrayList<>();

    public Paint(ShapeMaker currentShape){
        this.shapeMaker = currentShape;

        Dimension defaultSize = currentSize;

        this.setSize(defaultSize);

        mouse = new PaintMouseListener(shapeMaker, this);

//        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);
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

        Stroke defaultStroke = gd.getStroke();

        gd.setColor(Color.black);
        drawables.forEach(drawable -> drawable.draw(gd));

//        mouse.getToPaint().forEach((painting)->{
//            if (painting.getShapes() != null){
//                gd.setColor(painting.getColor());
//                gd.setStroke(painting.getStroke());
//
//                painting.getShapes().forEach((paint -> gd.draw(paint.getShape())));
//
//                gd.setStroke(defaultStroke);
//            }else{
//                gd.setColor(painting.getColor());
//                switch (painting.getPaintType()){
//                    case DRAW -> {
//                        gd.draw(painting.getShape());
//                        gd.setStroke(painting.getStroke());
//                    }
//                    case IMAGE -> gd.drawImage(painting.getImg(), 0, 0, null);
//                }
//            }
//        });

        gd.dispose();
    }

    public void addToLoadedImages(BufferedImage img){
        mouse.getToPaint().add(new ShapeContainer(shapeMaker.getColor(), img, PaintType.IMAGE));
    }

    public void setCtrlY(ControlY ctrlY){
        this.mouse.setCtrlY(ctrlY);
    }

}
