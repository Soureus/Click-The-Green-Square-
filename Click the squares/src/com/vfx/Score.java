package com.vfx;

import javax.swing.*;
import java.awt.*;

public class Score extends JPanel {

    String text;
    int width;
    int height;
    int x;
    int y;

    public Score(String text, int width, int height, int x, int y){
        this.text = text;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y =y;

        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setBounds(width,height,x,y);
        this.setBackground(Color.red);
        this.setOpaque(true);
    }

}
