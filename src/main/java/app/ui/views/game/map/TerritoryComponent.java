package app.ui.views.game.map;

import java.awt.*;
import java.io.IOException;
import javax.swing.*;

import app.domain.models.GameMap.Territory;

public class TerritoryComponent extends JComponent {
    private Territory _territory;

    public TerritoryComponent(Territory territory) {
        this.buildClass(territory);
        this.buildView();
    }

    private void buildClass(Territory territory) {
        this._territory = territory;
    }

    private void buildView() {
        this.setPreferredSize(new Dimension(300, 300));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        System.out.printf("Painting %s\n", _territory.name);

        Graphics2D graphics2d = (Graphics2D) graphics;
        try {
            graphics2d.drawImage(_territory.getImage(), null, 0, 0);
        } catch (IOException e) {
            System.err.printf("Unable to find %s image\n", _territory.name);
        }

        graphics.drawString(_territory.name, 20, 20);
    }

    public Territory getTerritory() {
        return this._territory;
    }
}
