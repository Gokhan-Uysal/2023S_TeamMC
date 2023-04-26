package app.domain.services.Map;

import java.awt.image.BufferedImage;

import app.domain.models.GameMap.Continent;
import app.domain.models.GameMap.Territory;

public class MapFactory {
    private MapReadService _mapReadService;

    public MapFactory(MapReadService mapReadService) {
        this._mapReadService = mapReadService;
        try {
            mapReadService.buildGameMapData();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Continent initilizeContinet(String continentName) {
        return new Continent(continentName);
    }

    public Territory initilizeTerritory(String territoryName) {
        return new Territory(territoryName);
    }

    public Territory initilizeTerritory(String name, BufferedImage bufferedImage) {
        return new Territory(name, bufferedImage);
    }
}
