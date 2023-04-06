package app.presentation.views.game;

import app.common.AppConfig;
import app.presentation.views.components.BaseJFrame;
import app.presentation.views.game.Map.MapFactory;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;

public class GameFrame extends BaseJFrame {

    public GameFrame(String title, Dimension size, Point location) {
        super(title, size, location);
        this.setLayout(new BorderLayout());
        buildComponents();
    }

    public void buildComponents() {
        this.pack();
        this.setLocation(AppConfig.screenSize);
        this.setVisible(true);

        MapFactory mapView = new MapFactory();
        mapView.drawMap();
        this.add(mapView, BorderLayout.CENTER);

        this.refresh();
    }

}
