package app.ui.controllers.game.map;

import app.domain.services.Map.MapService;
import app.ui.views.game.map.MapPanel;

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

    private void buildMap() {
        _mapService.run();
    }

    public void drawMap() {
        this.buildMap();
        // _mapPanel.drawMap(loadMap(), latitudes, longitudes);
    }
}
