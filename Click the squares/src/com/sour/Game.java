package com.sour;

import com.vfx.*;

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
    BufferedImage plusOne;
    BufferedImage minusOne;
    BufferedImage miss;

    private Frame frame;
    private String title;
    private int width, height;

    int clickedGreen = 0;
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
        plusOne = Assets.plusOne;
        minusOne = Assets.minusOne;
        miss = Assets.miss;


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
            clickedGreen = 0;
        }
        scoreBoard.setText("Score: " + scoreNumber);
    }

    public void render(){
        draw();
    }

    public void draw(){

        bs = frame.getCanvas().getBufferStrategy();
        if (bs == null){
            frame.getCanvas().createBufferStrategy(2);
            return;
        }


        g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        g.clearRect(0,0,width,height);

        //Start drawing

        g2d.drawImage(GBox, xLocationG, yLocationG,null);
        g2d.drawImage(RBox,xLocationR,yLocationR,null);

            //for plus minus and miss
        if (clickedGreen == 1){
            g2d.drawImage(plusOne, xLocationG+50,yLocationG-50,null);
        }else if (clickedGreen == 2){
            g2d.drawImage(minusOne, xLocationR+50,yLocationR-50,null);
        }else if(clickedGreen == 3){
            g2d.drawImage(miss, width/2 - miss.getWidth()/2,height/2 - miss.getHeight()/2,null);
        }
            //end of plus and minus and miss

        //End drawing

        bs.show();
        g.dispose();
        g2d.dispose();

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
            if (!clicked){
            scoreNumber++;
            clickedGreen = 1;
            clicked = true;
            }

        }else if(clickX>xLocationR && clickX<xLocationR+32 &&clickY>yLocationR&&clickY<yLocationR+32){
            if (!clicked) {
                scoreNumber--;
                clickedGreen = 2;
                clicked = true;
            }
        }else if (!clicked){
            clickedGreen = 3;
            scoreNumber = 0;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
