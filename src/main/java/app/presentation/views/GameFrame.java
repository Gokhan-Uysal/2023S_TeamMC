package app.presentation.views;

import app.common.AppConfig;
import app.presentation.views.components.BaseJFrame;
import app.presentation.views.main.view.MapPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class GameFrame extends BaseJFrame {

    public GameFrame(String title, Dimension size, Color bgColor) {
        super(title, size, bgColor);
        this.setLayout(new BorderLayout());
    }

    public void buildComponents(){
        this.pack();
        this.setLocation(AppConfig.screenSize);
        this.setVisible(true);

        MapPanel mapPanel = new MapPanel();
        mapPanel.drawMap();
        this.add(mapPanel, BorderLayout.CENTER);

        this.refresh();
    }

}
