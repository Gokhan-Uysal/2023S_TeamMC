package app.domain.models.GameMap;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Set;

import javax.imageio.ImageIO;

import app.common.AppConfig;

public class Territory {
    public String name;
    public String imageName;
    public TerritoryPosition territoryPosition;
    public Set<String> adjList;

    public Territory(String name, String imageName, TerritoryPosition territoryPosition, Set<String> adjList) {
        this.name = name;
        this.imageName = imageName;
        this.territoryPosition = territoryPosition;
        this.adjList = adjList;
    }

    public BufferedImage getImage() throws IOException {
        File imageFile = new File(AppConfig.basePath + "/resource/assets/territories/" + imageName);
        return ImageIO.read(imageFile);
    }

    @Override
    public String toString() {
        String info = "";
        info += name;
        info += "\s\s";
        info += imageName;
        info += "\s\s";
        info += territoryPosition.toString();
        info += "\s\s";
        info += "\n";
        return info;
    }
}
