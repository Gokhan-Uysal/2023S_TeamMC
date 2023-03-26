package app.presentation.views.Body;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import javax.swing.JPanel;

import app.common.Exceptions.ItemAlreadyExists;

public class Map extends JPanel {
    private Component[][] mapGrid;

    public Map(int latitudes, int longitudes) {
        mapGrid = new Component[latitudes][longitudes];
        this.setLayout(new GridLayout(latitudes, longitudes, 0, 0));
    }

    public void putTerritoryPiece(Component component, int x, int y)
            throws IndexOutOfBoundsException, ItemAlreadyExists {
        if (x >= mapGrid.length || y >= mapGrid[0].length) {
            throw new IndexOutOfBoundsException(String.format("Cannot add component to x: %d, y: %d", x, y));
        }
        if (mapGrid[x][y] != null) {
            throw new ItemAlreadyExists("Territory is not emptry");
        }

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = x;
        gridBagConstraints.gridy = y;

        this.mapGrid[x][y] = component;
        this.add(component, gridBagConstraints);
        this.refresh();
    }

    public void refresh() {
        this.revalidate();
        this.repaint();
    }
}