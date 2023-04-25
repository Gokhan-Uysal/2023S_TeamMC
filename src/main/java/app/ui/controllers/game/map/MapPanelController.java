package app.ui.controllers.game.map;

import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;

import app.domain.services.MapService;
import app.ui.views.game.map.MapPanel;
import app.ui.views.game.map.TerritoryLabel;

public class MapPanelController {

    private MapService _mapService;
    private MapPanel _mapPanel;

    private int latitudes;
    private int longitudes;

    public int getLatitudes() {
        return latitudes;
    }

    public int getLongitudes() {
        return longitudes;
    }

    public MapPanelController(MapService mapService, MapPanel mapView) {
        this._mapService = mapService;
        this._mapPanel = mapView;
    }

    private BufferedImage[][] loadMapPixels() {
        BufferedImage[][] pixelMap = _mapService.splitImage();
        this.latitudes = pixelMap.length;
        this.longitudes = pixelMap[0].length;
        return pixelMap;
    }

    private void buildMap() {
        this._mapService.buildTerritoryVerticies();
        this._mapService.initializeTerritories();

    }

    private Component[][] loadMap() {
        BufferedImage[][] pixelMap = loadMapPixels();
        Component[][] mapGrid = new Component[latitudes][longitudes];
        for (int i = 0; i < latitudes; i++) {
            for (int j = 0; j < longitudes; j++) {
                Image resizedImage = pixelMap[i][j].getScaledInstance(_mapPanel.getPixelSize(),
                        _mapPanel.getPixelSize(),
                        Image.SCALE_SMOOTH);
                mapGrid[i][j] = new TerritoryLabel("Canada", resizedImage);
            }
        }
        return mapGrid;
    }

    public void drawMap() {
        this.buildMap();
        _mapPanel.drawMap(loadMap(), latitudes, longitudes);
    }
}
