package app.domain.services.Map;

import java.awt.*;
import java.io.File;

public class MapService {
        private MapFactory _mapFactory;
        private MapGraphService _mapGraphService;

        public MapService(MapFactory mapFactory, MapGraphService mapGraphService) {
                this._mapFactory = mapFactory;
                this._mapGraphService = mapGraphService;
        }

        public void buildTerritoryVerticies() {

        }

        public void buildTerritoryAdj(String source, String[] adjList) {
                for (String adjTerritory : adjList) {
                        _mapGraphService.addEdge(source, adjTerritory);
                }
        }

        public Image resizeImage(Image image, int newWidth, int newHeight) {
                return image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        }
}
