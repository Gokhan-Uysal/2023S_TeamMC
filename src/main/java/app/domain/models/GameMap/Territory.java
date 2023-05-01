package app.domain.models.GameMap;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import app.common.AppConfig;

public class Territory {
    public String name;
    public String imageName;
    public TerritoryPosition territoryPosition;

    public Territory(String name, String imageName, TerritoryPosition territoryPosition) {
        this.name = name;
        this.imageName = imageName;
        this.territoryPosition = territoryPosition;
    }

    public BufferedImage getImage() throws IOException {
        File imageFile = new File(AppConfig.basePath + "/resources/assets/territories/" + imageName);
        BufferedImage originalImage = ImageIO.read(imageFile);
        int newWidth = originalImage.getWidth() / 2;
        int newHeight = originalImage.getHeight() / 2;
        BufferedImage scaledImage = new BufferedImage(newWidth, newHeight, originalImage.getType());

        Graphics2D g = scaledImage.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        g.dispose();

        return scaledImage;
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
