package app.domain.models.GameMap;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Territory {
    public String name;
    public BufferedImage image;

    public Territory(String name) throws IOException {
        this.name = name;
        this.image = getImage(name + ".png");
    }

    public BufferedImage getImage(String imageName) throws IOException {
        File imageFile = new File("src/main/java/app/" + imageName);
        return ImageIO.read(imageFile);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
