package app.domain.models.Card;

import javax.swing.ImageIcon;

abstract class BaseCard {

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

    protected String getDiscription() {
        return this.description;
    }

    protected ImageIcon getImageIcon() {
        return this.imageIcon;
    }
}