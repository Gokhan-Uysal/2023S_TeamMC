package app.domain.models.Card;

import javax.swing.ImageIcon;

public class InfantryCard extends ArmyCard {

    protected InfantryCard(String description, ImageIcon imageIcon) {
        super(description, imageIcon, 10);
        this.imageIcon = imageIcon;
    }

}
