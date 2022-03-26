package com.company.view.container.paint;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public class PaintContainer extends JPanel {

    @Getter
    private final JPanel scrollPaneContainer;
    @Getter
    private final Paint paint;

    public PaintContainer(Paint paint){
        this.paint = paint;
        paint.setPaintC(this);
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        this.setBackground(Color.decode("#d0dae8"));

        scrollPaneContainer = new JPanel();
        scrollPaneContainer.setLayout(null);
        scrollPaneContainer.add(paint);
        scrollPaneContainer.setBorder(this.getBorder());
        scrollPaneContainer.setBackground(this.getBackground());
        scrollPaneContainer.setPreferredSize(new Dimension(640,480));

        JScrollPane scrollPane = new JScrollPane(scrollPaneContainer);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // removes the ugly outline that JScrollPane creates
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        scrollPaneContainer.addMouseWheelListener(paint.getZoomManager());

        this.add(scrollPane, BorderLayout.CENTER);
    }

}
