package app.domain.models.Card;

import javax.swing.ImageIcon;

public class ArtilaryCard extends ArmyCard {

    protected ArtilaryCard(String description, ImageIcon imageIcon) {
        super(description, imageIcon, 60);
        this.imageIcon = imageIcon;
    }

}
