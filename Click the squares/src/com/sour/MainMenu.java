package com.sour;

import javax.swing.*;
import java.awt.*;

public class MainMenu {

    private JPanel mainMenu;
    String title;
    int width;
    int height;


    MainMenu(int width, int height){

        this.width = width;
        this.height = height;

        mainMenu = new JPanel();
        mainMenu.setBackground(Color.lightGray);
        mainMenu.setPreferredSize(new Dimension(width,height));

    }

    public JPanel getMainMenu() {
        return mainMenu;
    }
}
