package com.vfx;

import java.awt.image.BufferedImage;

public class Assets {

    public static final int width = 32, height = 32;

    public static BufferedImage exlemationBox, XBox;

    public static void init(){

        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/Textures/spriteSheet.png"));

        exlemationBox = sheet.crop(0,0, width, height);
        XBox = sheet.crop(width,0,width,height);

    }

}
