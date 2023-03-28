package app.presentation.views.Body.Map;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import app.common.Layer;
import app.common.Logger;
import app.common.Exceptions.ItemAlreadyExists;

public class Map extends JPanel {
    private int latitudes;
    private int longitudes;

    public Component[][] mapGrid;

    public Map(int latitudes, int longitudes) {
        buildClass(latitudes, longitudes);
        buildView();
    }

    private void buildView() {
        this.setLayout(new GridBagLayout());

    }

    private void buildClass(int latitudes, int longitudes) {
        this.latitudes = latitudes;
        this.longitudes = longitudes;
        this.mapGrid = new Component[latitudes][longitudes];
    }

    // Asks business layer to load map data from db
    public void loadMap() {
        for (int i = 0; i < latitudes; i++) {
            for (int j = 0; j < longitudes; j++) {
                JPanel ter = new JPanel();
                ter.setBackground(Color.BLUE);
                ter.setPreferredSize(new Dimension(20, 20));
                if (i >= 20 && j >= 20 && i <= 25 && j <= 25) {
                    ter.setBackground(Color.BLACK);
                }
                this.mapGrid[i][j] = ter;
            }
        }

        this.mapGrid[0][0] = new Teritory("Canada", null);
        Logger.info(Layer.Presentation, "Requesting game map components");
    }

    public void drawMapPiece(Component component, int x, int y)
            throws IndexOutOfBoundsException, ItemAlreadyExists, NullPointerException {

        if (x >= mapGrid.length || y >= mapGrid[0].length) {
            throw new IndexOutOfBoundsException(String.format("Cannot add component to x: %d, y: %d", x, y));
        }

        if (mapGrid[x][y] == null) {
            throw new NullPointerException("Map grid piece is null");
        }

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = x;
        gridBagConstraints.gridy = y;
        gridBagConstraints.insets = new Insets(1, 1, 1, 1);

        this.add(component, gridBagConstraints);
    }

    public void drawMap() {
        for (int i = 0; i < latitudes; i++) {
            for (int j = 0; j < longitudes; j++) {
                try {
                    this.drawMapPiece(mapGrid[i][j], i, j);
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