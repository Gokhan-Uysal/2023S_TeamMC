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
        this.setPreferredSize(new Dimension(100, 100));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2d = (Graphics2D) graphics;
        try {
            graphics2d.drawImage(_territory.getImage(), null, _territory.territoryPosition.x,
                    _territory.territoryPosition.y);
        } catch (IOException e) {
            System.err.println("Unable to find territory image");
        }
        graphics.drawString(_territory.name, 100, 100);
    }

    public Territory getTerritory() {
        return this._territory;
    }
}
