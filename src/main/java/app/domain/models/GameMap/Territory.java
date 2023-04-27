package app.domain.models.GameMap;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import app.common.AppConfig;

public class Territory {
    public String name;
    public String imageName;
    public TerritoryPosition territoryPosition;
    public BufferedImage image;

    public Territory(String name, String imageName, TerritoryPosition territoryPosition) {
        this.name = name;
        this.imageName = imageName;
        this.territoryPosition = territoryPosition;
    }

    public BufferedImage getImage() throws IOException {
        File imageFile = new File(AppConfig.basePath + "/resources/terriotories/" + imageName);
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
