package app.presentation.controllers.Body;

import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

import app.business.services.MapService;
import app.common.Logger;
import app.presentation.views.Body.Map.MapView;
import app.presentation.views.Body.Map.Territory;

public class MapController {

    private MapService mapService;
    private MapView mapView;

    private int latitudes;
    private int longitudes;

    public int getLatitudes() {
        return latitudes;
    }

    public int getLongitudes() {
        return longitudes;
    }

    public MapController(MapService mapService, MapView mapView) {
        this.mapService = mapService;
        this.mapView = mapView;
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
                Image resizedImage = pixelMap[i][j].getScaledInstance(mapView.getPixelSize(), mapView.getPixelSize(),
                        Image.SCALE_SMOOTH);
                mapGrid[i][j] = new Territory("Canada", resizedImage);
            }
        }
        Logger.log(Arrays.toString(mapGrid));
        return mapGrid;
    }

    public void drawMap() {
        mapView.drawMap(loadMap(), latitudes, longitudes);
        mapView.refresh();
    }
}
