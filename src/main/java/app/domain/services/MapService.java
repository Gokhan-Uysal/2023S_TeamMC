package app.domain.services;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import app.common.Logger;

public class MapService {
    private File mapFile;
    private int tileSize;
    private MapFactory _mapFactory;
    private MapGraphService _mapGraphService;

    public MapService(File mapFile, int tileSize, MapFactory mapFactory, MapGraphService mapGraphService) {
        this.tileSize = tileSize;
        this.mapFile = mapFile;
        this._mapFactory = mapFactory;
        this._mapGraphService = mapGraphService;
    }

    public void initializeTerritories() {
        buildTerritoryAdj("Eastern Australia", new String[] { "Western Australia", "New Guinea" });
        buildTerritoryAdj("Indonesia", new String[] { "New Guinea", "Siam (Southeast Asia)", "Western Australia" });
        buildTerritoryAdj("New Guinea", new String[] { "Eastern Australia", "Indonesia", "Western Australia" });
        buildTerritoryAdj("Western Australia", new String[] { "Eastern Australia", "Indonesia", "New Guinea" });

        buildTerritoryAdj("Afghanistan", new String[] { "China", "India", "Middle East", "Ural" });
        buildTerritoryAdj("China",
                new String[] { "Afghanistan", "India", "Mongolia", "Siam (Southeast Asia)", "Siberia", "Ural" });
        buildTerritoryAdj("India", new String[] { "Afghanistan", "China", "Middle East", "Siam (Southeast Asia)" });
        buildTerritoryAdj("Irkutsk", new String[] { "Kamchatka", "Mongolia", "Siberia", "Yakutsk" });
        buildTerritoryAdj("Japan", new String[] { "Kamchatka", "Mongolia" });
        buildTerritoryAdj("Kamchatka", new String[] { "Alaska", "Irkutsk", "Japan", "Mongolia", "Yakutsk" });
        buildTerritoryAdj("Middle East", new String[] { "Afghanistan", "East Africa", "Egypt", "India",
                "Southern Europe", "Ukraine (Eastern Europe, Russia)" });
        buildTerritoryAdj("Mongolia", new String[] { "China", "Irkutsk", "Japan", "Kamchatka", "Siberia" });
        buildTerritoryAdj("Siam (Southeast Asia)", new String[] { "China", "India", "Indonesia" });
        buildTerritoryAdj("Siberia", new String[] { "China", "Irkutsk", "Mongolia", "Ural", "Yakutsk" });
        buildTerritoryAdj("Ural",
                new String[] { "Afghanistan", "China", "Siberia", "Ukraine (Eastern Europe, Russia)" });
        buildTerritoryAdj("Yakutsk", new String[] { "Irkutsk", "Kamchatka", "Siberia" });

        buildTerritoryAdj("Congo (Central Africa)", new String[] { "East Africa", "North Africa", "South Africa" });
        buildTerritoryAdj("East Africa", new String[] { "Congo (Central Africa)", "Egypt", "Madagascar", "North Africa",
                "Middle East", "South Africa" });
        buildTerritoryAdj("Egypt", new String[] { "East Africa", "Middle East", "North Africa", "Southern Europe" });
        buildTerritoryAdj("Madagascar", new String[] { "East Africa", "South Africa" });
        buildTerritoryAdj("North Africa",
                new String[] { "Congo (Central Africa)", "East Africa", "Egypt", "Southern Europe", "Western Europe" });
        buildTerritoryAdj("South Africa", new String[] { "Congo (Central Africa)", "East Africa", "Madagascar" });

        buildTerritoryAdj("Northern Europe", new String[] { "Great Britain (Great Britain & Ireland)", "Scandinavia",
                "Southern Europe", "Ukraine (Eastern Europe, Russia)", "Western Europe" });
        buildTerritoryAdj("Scandinavia", new String[] { "Great Britain (Great Britain & Ireland)", "Iceland",
                "Northern Europe", "Ukraine (Eastern Europe, Russia)" });
        buildTerritoryAdj("Southern Europe", new String[] { "Egypt", "Middle East", "Northern Europe",
                "Ukraine (Eastern Europe, Russia)", "Western Europe" });
        buildTerritoryAdj("Ukraine (Eastern Europe, Russia)",
                new String[] { "Middle East", "Northern Europe", "Scandinavia", "Southern Europe", "Ural" });
        buildTerritoryAdj("Western Europe", new String[] { "Great Britain (Great Britain & Ireland)", "North Africa",
                "Northern Europe", "Southern Europe" });

        buildTerritoryAdj("Alaska", new String[] { "Alberta (Western Canada)", "Kamchatka", "Northwest Territory" });
        buildTerritoryAdj("Alberta (Western Canada)",
                new String[] { "Alaska", "Northwest Territory", "Ontario (Central Canada)", "Western United States" });
        buildTerritoryAdj("Central America",
                new String[] { "Eastern United States", "Venezuela", "Western United States" });
        buildTerritoryAdj("Eastern United States", new String[] { "Central America", "Ontario (Central Canada)",
                "Quebec (Eastern Canada)", "Western United States" });
        buildTerritoryAdj("Greenland", new String[] { "Iceland", "Northwest Territory", "Quebec (Eastern Canada)" });
        buildTerritoryAdj("Northwest Territory",
                new String[] { "Alaska", "Alberta (Western Canada)", "Greenland", "Ontario (Central Canada)" });
        buildTerritoryAdj("Ontario (Central Canada)",
                new String[] { "Alberta (Western Canada)", "Eastern United States",
                        "Northwest Territory", "Quebec (Eastern Canada)", "Western United States" });
        buildTerritoryAdj("Quebec (Eastern Canada)",
                new String[] { "Eastern United States", "Greenland", "Ontario (Central Canada)" });
        buildTerritoryAdj("Western United States", new String[] { "Alberta (Western Canada)", "Central America",
                "Eastern United States", "Ontario (Central Canada)" });

        buildTerritoryAdj("Argentina", new String[] { "Brazil", "Peru" });
        buildTerritoryAdj("Brazil", new String[] { "Argentina", "Peru", "Venezuela", "North Africa" });
        buildTerritoryAdj("Peru", new String[] { "Argentina", "Brazil", "Venezuela" });
        buildTerritoryAdj("Venezuela", new String[] { "Brazil", "Peru", "Central America" });
        System.out.println(_mapGraphService.toString());
    }

    public void buildTerritoryVerticies() {
        _mapFactory.getTerritoryList().forEach((territory) -> _mapGraphService.addVertex(territory));
    }

    public void buildTerritoryAdj(String source, String[] adjList) {
        for (String adjTerritory : adjList) {
            _mapGraphService.addEdge(source, adjTerritory);
        }
    }

    public BufferedImage[][] splitImage() {
        BufferedImage[][] tiles;
        try {
            BufferedImage image = ImageIO.read(this.mapFile);
            int width = image.getWidth();
            int height = image.getHeight();

            int tilesX = Math.round(width / tileSize);
            int tilesY = Math.round(height / tileSize);

            tiles = new BufferedImage[tilesX][tilesY];

            for (int x = 0; x < tilesX; x++) {
                for (int y = 0; y < tilesY; y++) {
                    tiles[x][y] = image.getSubimage(x * tileSize, y * tileSize, tileSize, tileSize);
                }
            }
            return tiles;
        } catch (IOException e) {
            Logger.error(e);
        }

        return new BufferedImage[0][0];
    }

    public Image resizeImage(Image image, int newWidth, int newHeight) {
        return image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
    }
}
