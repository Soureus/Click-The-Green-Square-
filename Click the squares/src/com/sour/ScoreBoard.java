package com.sour;

import com.vfx.Score;

import java.awt.*;

public class ScoreBoard extends Label{

    String text;
    Rectangle bounds;
    Font font;

    ScoreBoard(String text, int x, int y, int width, int height, Font font){
        this.text = text;
        this.bounds = (new Rectangle(x,y,width,height));
        this.font = font;

        this.setText(text);
        this.setFont(font);
        this.setBounds(bounds);
        this.setBackground(Color.DARK_GRAY);
        this.setForeground(Color.white);
    }

    public void updateScoreboard(int score){
        this.setText(this.text);
    }

}
