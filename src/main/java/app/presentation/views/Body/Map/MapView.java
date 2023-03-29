package app.presentation.views.Body.Map;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

import app.common.Layer;
import app.common.Logger;
import app.common.Exceptions.ItemAlreadyExists;

public class MapView extends JPanel {

    private int pixelSize;

    public int getPixelSize() {
        return pixelSize;
    }

    public MapView(int pixelSize) {
        buildClass(pixelSize);
        buildView();
    }

    private void buildView() {
        this.setLayout(new GridBagLayout());
    }

    private void buildClass(int pixelSize) {
        this.pixelSize = pixelSize;
    }

    public void drawMapPixel(Component component, int i, int j) {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = i;
        gridBagConstraints.gridy = j;

        this.add(component, gridBagConstraints);
    }

    public void validatePixel(Component[][] mapGrid, int i, int j)
            throws IndexOutOfBoundsException, ItemAlreadyExists, NullPointerException {
        if (i >= mapGrid.length || j >= mapGrid[0].length) {
            throw new IndexOutOfBoundsException(String.format("Cannot add component to x: %d, y: %d", i, j));
        }
        if (mapGrid[i][j] == null) {
            throw new NullPointerException("Map grid piece is null");
        }
    }

    public void drawMap(Component[][] mapGrid, int latitudes, int longitudes) {
        for (int i = 0; i < latitudes; i++) {
            for (int j = 0; j < longitudes; j++) {
                try {
                    this.validatePixel(mapGrid, i, j);
                    this.drawMapPixel(mapGrid[i][j], i, j);
                } catch (IndexOutOfBoundsException | ItemAlreadyExists | NullPointerException e) {
                    Logger.error(Layer.Presentation, e);
                }
            }
        }
        this.refresh();
    }

    public void refresh() {
        this.revalidate();
        this.repaint();
    }
}