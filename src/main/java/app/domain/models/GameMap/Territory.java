package app.domain.models.GameMap;

import java.awt.image.BufferedImage;

public class Territory {
    public String name;
    public BufferedImage image;

    public Territory(String name, BufferedImage shape) {
        this.name = name;
        this.image = shape;
    }

    public Territory(String name) {
        this.name = name;
        image = null;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
