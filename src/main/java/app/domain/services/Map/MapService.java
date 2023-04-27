package app.domain.services.Map;

import java.util.List;

import app.domain.models.GameMap.Territory;

public class MapService implements Runnable {
        private MapFactory _mapFactory;

        public MapService(MapFactory mapFactory) {
                this._mapFactory = mapFactory;
        }

        public void loadGameMapData() {
                _mapFactory.loadGameDataToGraph();
        }

        public List<Territory> getTerritoryList() {
                return _mapFactory.getTerritoryList();
        }

        @Override
        public void run() {

        }
}
