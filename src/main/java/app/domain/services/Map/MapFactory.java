package app.domain.services.Map;

import java.util.List;

import app.domain.models.GameMap.Territory;

public class MapFactory {
    private MapReadService _mapReadService;
    private MapGraphService _mapGraphService;

    public MapFactory(MapReadService mapReadService, MapGraphService mapGraphService) {
        this._mapReadService = mapReadService;
        this._mapGraphService = mapGraphService;
    }

    public void loadGameDataToGraph() {
        loadMapData();
        loadGraphVerticies();
    }

    private void loadMapData() {
        _mapReadService.buildGameMapData();
    }

    private void addTerriotoryVerticies(List<Territory> territoryList) {
        _mapGraphService.addVerticies(territoryList);
    }

    private void loadGraphVerticies() {
        List<Territory> territoryList = _mapReadService.getGameMapTerritories();
        addTerriotoryVerticies(territoryList);
    }
}
