package app.presentation.views;

import app.common.AppConfig;
import app.presentation.views.components.BaseJFrame;

import javax.swing.*;
import java.awt.*;

public class MainMenuFrame extends BaseJFrame {

    public MainMenuFrame(String title, Dimension size, Color bgColor){
        super(title, size, bgColor);
        this.setLayout(new BorderLayout());
    }

    @Override
    public void buildComponents() {
        this.pack();
        this.setLocation(AppConfig.screenSize);
        this.setVisible(true);
    }

}
