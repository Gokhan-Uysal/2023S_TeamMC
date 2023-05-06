package app.domain.models.card.army;

import javax.swing.ImageIcon;

public class CavalryCard extends ArmyCard {

    public CavalryCard() {
        super(CavalryCard.class.getName(), new ImageIcon("CavaltryCard.png"), 40);
    }
}
