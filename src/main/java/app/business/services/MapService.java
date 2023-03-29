package app.business.services;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import app.common.Layer;
import app.common.Logger;

public class MapService {
    private File mapFile;
    private int tileSize;

    public MapService(File mapFile, int tileSize) {
        this.tileSize = tileSize;
        this.mapFile = mapFile;
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
            Logger.error(Layer.Business, e);
        }

        return new BufferedImage[0][0];
    }

    public Image resizeImage(Image image, int newWidth, int newHeight) {
        return image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
    }
}
