package com.sour;

import com.vfx.Assets;
import com.vfx.ImageLoader;
import com.vfx.Score;
import com.vfx.SpriteSheet;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Game implements Runnable, MouseListener {

    Thread thread;

    private BufferStrategy bs;
    Graphics g;
    ImageLoader imageLoader = new ImageLoader();

    Long randomizeTimer = 1000L;
    LocationRandomiser randomizeLocationGreen;
    LocationRandomiser randomizeLocationRed;

    private int clickTimer = 0;
    private boolean clicked = false;

    ScoreBoard scoreBoard;
    private int scoreNumber = 0;

    private int xLocationR = 0;
    private int yLocationR = 0;
    private int xLocationG = 0;
    private int yLocationG = 0;

    BufferedImage GBox;
    BufferedImage RBox;

    private Frame frame;
    String title;
    private int width, height;

    boolean running = false;

    Game(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;
    }

    private void init(){
        frame = new Frame(title, height,width);
        frame.getCanvas().addMouseListener(this);
        scoreBoard = new ScoreBoard("Score: " + scoreNumber, 0,0,100,35, new Font("MV Boli", Font.BOLD, 20));
        frame.add(scoreBoard);
        frame.add(frame.getCanvas());
        frame.setVisible(true);

        Assets.init();
        GBox = Assets.exlemationBox;
        RBox = Assets.XBox;

        System.out.println(frame.getLayout().toString());

        randomizeLocationGreen = new LocationRandomiser(frame.getCanvas().getWidth()-32,frame.getCanvas().getHeight()-32,randomizeTimer);
        randomizeLocationRed = new LocationRandomiser(frame.getCanvas().getWidth()-32,frame.getCanvas().getHeight()-32,randomizeTimer);

    }

    @Override
    public void run() {
        init();

        int fps = 60;
        double timerPerTick = 1000000000/fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running){

            now = System.nanoTime();
            delta +=(now - lastTime)/timerPerTick;
            timer +=now - lastTime;
            lastTime = now;

            if (delta>=1) {
                render();
                update();
                ticks ++;
                delta --;
            }

            if (timer>=1000000000){
                //System.out.println("Fps: " + ticks);
                ticks = 0;
                timer = 0;
                clickTimer = 0;

            }

        }
    }

    public synchronized void start(){
        if (running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void stop() throws InterruptedException {
        if (!running) return;
        running = false;
        thread.join();
    }

    public void update(){

        randomizeLocaiton();
        clickTimer++;
        if (clickTimer == 60){
            clicked = false;
        }
        scoreBoard.setText("Score: " + scoreNumber);
    }

    public void render(){
        draw();
    }

    public void draw(){

        bs = frame.getCanvas().getBufferStrategy();
        if (bs == null){
            frame.getCanvas().createBufferStrategy(3);
            return;
        }


        g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        g.clearRect(0,0,width,height);

        //Start drawing

        g2d.drawImage(GBox, xLocationG, yLocationG,null);
        g2d.drawImage(RBox,xLocationR,yLocationR,null);

        //End drawing

        bs.show();
        g.dispose();

    }

    public void randomizeLocaiton(){
        xLocationG = randomizeLocationGreen.getLocationX();
        yLocationG = randomizeLocationGreen.getLocationY();
        xLocationR = randomizeLocationRed.getLocationX();
        yLocationR = randomizeLocationRed.getLocationY();
    }



    //Mouse detecting
    @Override
    public void mouseClicked(MouseEvent e) {

    }
    @Override
    public void mousePressed(MouseEvent e) {

        int clickX = e.getX();
        int clickY = e.getY();

        if (clickX>xLocationG && clickX<xLocationG+32 &&clickY>yLocationG&&clickY<yLocationG+32){
            System.out.println("This is green");
            if (!clicked){
            scoreNumber++;
            clicked = true;
            }

        }else if(clickX>xLocationR && clickX<xLocationR+32 &&clickY>yLocationR&&clickY<yLocationR+32){
            System.out.println("This is red");
            if (!clicked) {
                scoreNumber--;
                clicked = true;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
