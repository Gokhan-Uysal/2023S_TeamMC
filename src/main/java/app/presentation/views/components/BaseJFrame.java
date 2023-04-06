package app.presentation.views.components;

import javax.swing.*;

import app.presentation.views.game.GameFrame;

import java.awt.*;

public abstract class BaseJFrame extends JFrame {

    public BaseJFrame(String title, Dimension size, Point location) {
        initApp(title, size, location);
    }

    private void initApp(String title, Dimension size, Point location) {
        this.setPreferredSize(size);
        this.setLocation(location);

        this.setTitle(title);
        this.setDefaultCloseOperation(GameFrame.EXIT_ON_CLOSE);
    }

    public abstract void buildComponents();

    public void setLocation(Dimension screenSize) {
        int centerX = screenSize.width / 2;
        int centerY = screenSize.height / 2;

        this.setLocation(centerX - this.getWidth() / 2, centerY - this.getHeight() / 2);

    };

    public void refresh() {
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    };
}
