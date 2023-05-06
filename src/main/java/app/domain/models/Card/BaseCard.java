package app.domain.models.Card;

import javax.swing.ImageIcon;

public abstract class BaseCard {

    protected String name;
    protected String description;
    protected ImageIcon imageIcon;

    protected BaseCard(String name, String description, ImageIcon imageIcon) {
        this.name = name;
        this.description = description;
        this.imageIcon = imageIcon;
    }

    protected String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public ImageIcon getImageIcon() {
        return this.imageIcon;
    }
}