package com.tripleS.window;

import javax.swing.*;

public class Frame extends JFrame {
    public Frame(String title, int width, int height) {
        super();
        windowInit(title, width, height);
    }
    public void setVisible() {
        setVisible(true);
    }
    private void windowInit(String title, int width, int height) {
        setTitle(title);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
