package app.presentation.views.Body;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import javax.swing.JPanel;

import app.common.Layer;
import app.common.Logger;
import app.common.Exceptions.ItemAlreadyExists;

public class Map extends JPanel {
    private int latitudes;
    private int longitudes;

    public Component[][] mapGrid;

    public Map(int latitudes, int longitudes) {
        buildView(latitudes, longitudes);
    }

    private void buildView(int latitudes, int longitudes) {
        this.setLayout(new GridLayout(latitudes, longitudes, 5, 5));
        buildClass(latitudes, longitudes);
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
                if (i == 20 && j == 20) {
                    ter.setBackground(Color.BLACK);
                }
                this.mapGrid[i][j] = ter;
            }
        }
        Logger.info(Layer.Presentation, "Requesting game map components");
    }

    public void drawMapPiece(Component component, int x, int y)
            throws IndexOutOfBoundsException, ItemAlreadyExists {

        if (x >= mapGrid.length || y >= mapGrid[0].length) {
            throw new IndexOutOfBoundsException(String.format("Cannot add component to x: %d, y: %d", x, y));
        }

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = x;
        gridBagConstraints.gridy = y;

        this.add(component, gridBagConstraints);
        this.refresh();
    }

    public void drawMap() {
        for (int i = 0; i < latitudes; i++) {
            for (int j = 0; j < longitudes; j++) {
                try {
                    this.drawMapPiece(mapGrid[i][j], i, j);
                } catch (IndexOutOfBoundsException | ItemAlreadyExists e) {
                    Logger.error(Layer.Presentation, e);
                }
            }
        }
    }

    public void refresh() {
        this.revalidate();
        this.repaint();
    }
}