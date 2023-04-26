package app.ui.views.game.map;

import java.awt.*;
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
        this.setPreferredSize(new Dimension(15, 15));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2d = (Graphics2D) graphics;
        graphics2d.drawImage(_territory.image, null, ALLBITS, ABORT);
        graphics.drawString(_territory.name, 100, 100);
    }
}
