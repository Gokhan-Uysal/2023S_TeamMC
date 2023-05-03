package app.ui.views.game.map;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class TerritoryComponent extends JComponent {
    private BufferedImage _territoryImage;
    private boolean _isVisible;

    public TerritoryComponent(BufferedImage territoryImage) {
        this.buildClass(territoryImage);
        this.buildView();
    }

    private void buildClass(BufferedImage territoryImage) {
        this._territoryImage = territoryImage;
    }

    private void buildView() {
        this._isVisible = true;
        this.setPreferredSize(new Dimension(_territoryImage.getWidth(), _territoryImage.getHeight()));
    }

    public void toggleVisiblity() {
        this._isVisible = !_isVisible;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        if (!_isVisible) {
            return;
        }
        super.paintComponent(graphics);
        Graphics2D graphics2d = (Graphics2D) graphics;
        graphics2d.drawImage(_territoryImage, null, 0, 0);
    }
}
