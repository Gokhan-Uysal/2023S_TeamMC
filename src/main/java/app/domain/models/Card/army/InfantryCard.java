package app.domain.models.card.army;

import javax.swing.ImageIcon;

public class InfantryCard extends ArmyCard {

    public InfantryCard() {
        super(InfantryCard.class.getName(), new ImageIcon("InfantryCard.png"), 10);
    }

}
