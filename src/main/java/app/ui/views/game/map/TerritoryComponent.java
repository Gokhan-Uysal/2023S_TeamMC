package app.ui.views.game.map;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class TerritoryComponent extends JComponent {
    private BufferedImage _territoryImage;
    private String _territoryName;

    public TerritoryComponent(String territoryName, BufferedImage territoryImage) {
        this.buildClass(territoryName, territoryImage);
        this.buildView();
    }

    private void buildClass(String territoryName, BufferedImage territoryImage) {
        this._territoryName = territoryName;
        this._territoryImage = territoryImage;
    }

    private void buildView() {
        this.setPreferredSize(new Dimension(300, 300));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        System.out.printf("Painting %s\n", _territoryName);

        Graphics2D graphics2d = (Graphics2D) graphics;
        graphics2d.drawImage(_territoryImage, null, 0, 0);

        graphics.drawString(_territoryName, 20, 20);
    }
}
