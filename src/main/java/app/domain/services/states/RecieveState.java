package app.domain.services.states;

import app.domain.services.map.MapService;

public class RecieveState {
    private MapService _mapService;

    public RecieveState() {
        _mapService = new MapService();
    }
}
