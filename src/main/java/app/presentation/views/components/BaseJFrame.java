package app.presentation.views.components;

import app.presentation.views.GameFrame;

import javax.swing.*;
import java.awt.*;

public abstract class BaseJFrame extends JFrame {

    public BaseJFrame(String title, Dimension size, Color bgColor){
        initApp(title, size, bgColor);
    }

    private void initApp(String title, Dimension size, Color bgColor) {
        this.setPreferredSize(size);
        this.setTitle(title);
        this.setDefaultCloseOperation(GameFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(bgColor);
        this.buildComponents();

    }

    public abstract void buildComponents();
    public void setLocation(Dimension screenSize){
        int centerX = screenSize.width / 2;
        int centerY = screenSize.height / 2;

        this.setLocation(centerX - this.getWidth() / 2, centerY - this.getHeight() / 2);

    };
    public void refresh(){
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    };
}
