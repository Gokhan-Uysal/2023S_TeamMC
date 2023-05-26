package app.domain.services.states;

import app.common.Logger;
import app.common.errors.DbException;
import app.domain.services.map.MapService;

public class BuildState {
    private MapService _mapService;

    public BuildState() {
    }

    public void loadGameMap() {
        try {
            _mapService.loadGameMapDataToGraph();
        } catch (DbException e) {
            Logger.error(e);
        }
    }
}
