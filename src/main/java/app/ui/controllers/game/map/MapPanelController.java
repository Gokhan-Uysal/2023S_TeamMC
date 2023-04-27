package app.ui.controllers.game.map;

import java.util.List;

import app.domain.models.GameMap.Territory;
import app.domain.services.Map.MapService;
import app.ui.views.game.map.MapPanel;

public class MapPanelController {

    private MapService _mapService;
    private MapPanel _mapPanel;

    public MapPanelController(MapPanel mapPanel, MapService mapService) {
        this._mapPanel = mapPanel;
        this._mapService = mapService;
    }

    public void displayMap() {
        List<Territory> territoryList = _mapService.getTerritoryList();
        territoryList.forEach((territory) -> {
            TerritoryComponentController trController = new TerritoryComponentController(territory);
            _mapPanel.drawTerriotry(trController.getTerritoryComponent());
        });
    }

    private void loadMap() {
        _mapService.loadGameMapData();
    }

    public void drawMap() {
        this.loadMap();
        this.displayMap();
    }
}
