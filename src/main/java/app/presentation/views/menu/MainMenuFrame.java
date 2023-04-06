package app.presentation.views.menu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;

import app.common.AppConfig;
import app.presentation.views.components.BaseJFrame;

public class MainMenuFrame extends BaseJFrame {

    public MainMenuFrame(String title, Dimension size, Point location) {
        super(title, size, location);
        this.setLayout(new BorderLayout());
        buildComponents();
    }

    @Override
    public void buildComponents() {
        this.pack();
        this.setLocation(AppConfig.screenSize);
        this.setVisible(true);

        ButtonsPanel buttonsPanel = new ButtonsPanel(3, 1);
        this.add(buttonsPanel, BorderLayout.CENTER);

        this.refresh();
    }
}
