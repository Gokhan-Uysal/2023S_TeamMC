package app.domain.models.card.chance;

import javax.swing.ImageIcon;

import app.domain.models.card.BaseCard;

public class ChanceCard extends BaseCard {

    public ChanceCard(String description, ImageIcon imageIcon) {
        super(ChanceCard.class.getName(), description, imageIcon);
    }

}
