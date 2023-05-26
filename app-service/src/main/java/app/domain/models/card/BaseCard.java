package app.domain.models.card;

import java.awt.image.BufferedImage;

public abstract class BaseCard {

    protected String name;
    protected String description;
    protected BufferedImage image;

    protected BaseCard(String name, String description, BufferedImage image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public BufferedImage getImage() {
        return this.image;
    }
}