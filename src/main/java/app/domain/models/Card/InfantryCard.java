package app.domain.models.Card;

import javax.swing.ImageIcon;

public class InfantryCard extends ArmyCard {

    protected InfantryCard(ImageIcon imageIcon) {
        super(InfantryCard.class.getName(), imageIcon, 10);
        this.imageIcon = imageIcon;
    }

}
