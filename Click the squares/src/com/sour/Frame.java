package com.sour;

import com.vfx.Score;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    String title;
    int height;
    int width;

    private Canvas canvas;

    private int scoreNumber =0;

    private ScoreBoard scoreBoard;

    Frame(String title, int height, int width){
        this.title = title;
        this.height = height;
        this.width = width;

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.setLayout(null);
        this.setSize(this.width, this.height);
        this.setResizable(false);
        this.setTitle(this.title);
        this.setLocationRelativeTo(null);
        //GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(this);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(this.width,this.height));
        canvas.setBackground(Color.black);
        canvas.setFocusable(false);

        //this.add(scoreBoard);

    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public int getScoreNumber() {
        return scoreNumber;
    }

    public void setScoreNumber(int scoreNumber) {
        this.scoreNumber = scoreNumber;
    }

    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }

    public void setScoreBoard(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }
}
