package app.domain.services.Map;

import java.io.IOException;

import org.json.simple.parser.ParseException;

public class MapFactory {
    private MapReadService _mapReadService;
    private MapGraphService _mapGraphService;

    public MapFactory(MapReadService mapReadService, MapGraphService mapGraphService) {
        this._mapReadService = mapReadService;
        this._mapGraphService = mapGraphService;
        readMap();
    }

    private void readMap() {
        try {
            _mapReadService.buildGameMapData();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
