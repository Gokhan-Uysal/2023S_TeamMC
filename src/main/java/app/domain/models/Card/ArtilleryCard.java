package app.domain.models.Card;

import javax.swing.ImageIcon;

public class ArtilleryCard extends ArmyCard {

    protected ArtilleryCard(String description, ImageIcon imageIcon) {
        super(description, imageIcon, 60);
        this.imageIcon = imageIcon;
    }

}
