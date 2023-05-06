package app.domain.models.card.army;

import javax.swing.ImageIcon;

public class ArtilleryCard extends ArmyCard {

    public ArtilleryCard() {
        super(ArtilleryCard.class.getName(), new ImageIcon("ArtilleryCard.png"), 60);
    }

}
