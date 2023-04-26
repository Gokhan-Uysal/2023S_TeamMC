package app.domain.models.Card;

import javax.swing.ImageIcon;

public class CavalryCard extends ArmyCard {

    protected CavalryCard(String description, ImageIcon imageIcon) {
        super(description, imageIcon, 40);
        this.imageIcon = imageIcon;
    }

}
