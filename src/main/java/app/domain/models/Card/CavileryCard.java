package app.domain.models.Card;

import javax.swing.ImageIcon;

public class CavileryCard extends ArmyCard {
    protected CavileryCard(ImageIcon imageIcon) {
        super(CavileryCard.class.getName(), imageIcon, 40);
        this.imageIcon = imageIcon;
    }

}
