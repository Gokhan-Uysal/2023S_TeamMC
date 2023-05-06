package app.domain.models.card;

import javax.swing.ImageIcon;

public class InfantryCard extends ArmyCard {

    protected InfantryCard(String description, ImageIcon imageIcon) {
        super(description, imageIcon, 10);
        this.imageIcon = imageIcon;
    }

}
