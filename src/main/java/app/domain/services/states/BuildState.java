package app.domain.services.states;

import app.domain.services.map.MapService;

public class BuildState {
    private MapService _mapService;

    public BuildState() {

    }

    public void loadGameMap() {
        _mapService.loadGameMapDataToGraph();
    }

    public void getMap() {
        _mapService.getTerritoryListFromGraph();
    }
}
