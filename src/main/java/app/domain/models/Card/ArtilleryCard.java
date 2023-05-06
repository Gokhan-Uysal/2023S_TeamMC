package app.domain.models.card;

import javax.swing.ImageIcon;

public class ArtilleryCard extends ArmyCard {

    protected ArtilleryCard(String discription, ImageIcon imageIcon) {
        super(ArtilleryCard.class.getName(), imageIcon, 60);
        this.imageIcon = imageIcon;
    }

}
