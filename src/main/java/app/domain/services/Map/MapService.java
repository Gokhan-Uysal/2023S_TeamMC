package app.domain.services.Map;

import java.awt.*;

public class MapService {
        private MapFactory _mapFactory;

        public MapService(MapFactory mapFactory) {
                this._mapFactory = mapFactory;
        }

        public void buildTerritoryVerticies() {

        }

        public Image resizeImage(Image image, int newWidth, int newHeight) {
                return image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        }
}
