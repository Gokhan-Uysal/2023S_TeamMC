package app.presentation.views;

import javax.swing.*;
import java.awt.*;

abstract class BaseJFrame extends JFrame {

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

    abstract void buildComponents();
    void setLocation(Dimension screenSize){
        int centerX = screenSize.width / 2;
        int centerY = screenSize.height / 2;

        this.setLocation(centerX - this.getWidth() / 2, centerY - this.getHeight() / 2);

    };
    void refresh(){
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    };
    void setVisibility(boolean isVisible){
        this.setVisible(isVisible);
    };
}
