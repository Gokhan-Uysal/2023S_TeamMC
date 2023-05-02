package app.ui.controllers.game.map;

import java.io.IOException;
import java.util.List;

import app.common.Logger;
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
        List<Territory> territoryList = _mapService.getTerritoryListFromGraph();
        territoryList.forEach((territory) -> {
            TerritoryComponentController trController;
            try {
                trController = new TerritoryComponentController(territory);
                _mapPanel.drawTerriotry(trController.getTerritoryComponent());
            } catch (IOException e) {
                Logger.error(e);
            }
        });
    }

    private void loadMap() {
        _mapService.loadGameMapDataToGraph();
    }

    public void drawMap() {
        this.loadMap();
        this.displayMap();
    }
}
