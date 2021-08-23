package com.company.view.container.paint;

import javax.swing.*;
import java.awt.*;

public class PaintContainer extends JPanel {

    public PaintContainer(Paint paint){
        this.setLayout(null);
        this.setBackground(Color.decode("#d0dae8"));
        this.add(paint);
    }

}
