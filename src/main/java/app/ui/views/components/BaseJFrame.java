package app.ui.views.components;

import javax.swing.*;

import app.common.AppConfig;
import app.ui.views.game.GameFrame;

import java.awt.*;

public abstract class BaseJFrame extends JFrame {

    public BaseJFrame(String title, Dimension size, Point location) {
        initApp(title, size);
        this.setLocation(location);
    }

    public BaseJFrame(String title, Dimension size) {
        initApp(title, size);
        this.setLocation(AppConfig.appLocation);
    }

    private void initApp(String title, Dimension size) {
        this.setPreferredSize(size);

        this.setTitle(title);
        this.setResizable(false);
        this.setDefaultCloseOperation(GameFrame.EXIT_ON_CLOSE);
    }

    public abstract void initializeComponents();

    public abstract void buildComponents();

    public abstract void addComponents();

    public void refresh() {
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }
}
