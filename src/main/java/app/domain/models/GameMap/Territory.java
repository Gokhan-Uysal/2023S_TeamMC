package app.domain.models.GameMap;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Territory {
    public String name;
    public TerritoryPosition territoryPosition;
    public BufferedImage image;

    public Territory(String name, TerritoryPosition territoryPosition) throws IOException {
        this.name = name;
        this.territoryPosition = territoryPosition;
        // this.image = getImage(name + ".png");
    }

    public BufferedImage getImage(String imageName) throws IOException {
        File imageFile = new File("src/main/java/app/" + imageName);
        return ImageIO.read(imageFile);
    }

    @Override
    public String toString() {
        String info = "";
        info += name + "\s\s" + territoryPosition.toString();
        info += "\n";
        return info;
    }
}
