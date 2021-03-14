package com.sour;

import java.util.Timer;
import java.util.Random;
import java.util.TimerTask;

public class LocationRandomiser {

    Random rand = new Random();
    private int locationX;
    private int locationY;
    int upperX;
    int upperY;
    Timer timer;

    long delay = 1000L;

    LocationRandomiser(int upperX, int upperY, Long itterations){
        this.upperX = upperX;
        this.upperY = upperY;
        timer = new Timer();

        timer.scheduleAtFixedRate(randomiseLocation,0, itterations);
    }

    TimerTask randomiseLocation = new TimerTask() {

        @Override
        public void run() {
        randomiseX(upperX);
        randomiseY(upperY);
        }
    };


    public int randomiseX(int upperBound){
        locationX = rand.nextInt(upperBound);
        return locationX;
    }

    public int randomiseY(int upperBound){
        locationY = rand.nextInt(upperBound);
        return locationY;
    }

    public int getLocationX() {
        return locationX;
    }

    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public void setLocationY(int locationY) {
        this.locationY = locationY;
    }
}
