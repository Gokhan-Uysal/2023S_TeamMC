package app.ui.controllers.game.map;

import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;

import app.domain.services.MapService;
import app.ui.views.game.map.MapPanel;
import app.ui.views.game.map.TerritoryLabel;

public class MapController {

    private MapService mapService;
    private MapPanel mapPanel;

    private int latitudes;
    private int longitudes;

    public int getLatitudes() {
        return latitudes;
    }

    public int getLongitudes() {
        return longitudes;
    }

    public MapController(MapService mapService, MapPanel mapView) {
        this.mapService = mapService;
        this.mapPanel = mapView;
    }

    private BufferedImage[][] loadMapPixels() {
        BufferedImage[][] pixelMap = mapService.splitImage();
        this.latitudes = pixelMap.length;
        this.longitudes = pixelMap[0].length;
        return pixelMap;
    }

    private Component[][] loadMap() {
        BufferedImage[][] pixelMap = loadMapPixels();
        Component[][] mapGrid = new Component[latitudes][longitudes];
        for (int i = 0; i < latitudes; i++) {
            for (int j = 0; j < longitudes; j++) {
                Image resizedImage = pixelMap[i][j].getScaledInstance(mapPanel.getPixelSize(), mapPanel.getPixelSize(),
                        Image.SCALE_SMOOTH);
                mapGrid[i][j] = new TerritoryLabel("Canada", resizedImage);
            }
        }
        return mapGrid;
    }

    public void drawMap() {
        mapPanel.drawMap(loadMap(), latitudes, longitudes);
    }
}
