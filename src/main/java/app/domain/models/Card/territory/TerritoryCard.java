package app.domain.models.card.territory;

import javax.swing.ImageIcon;

import app.domain.models.card.BaseCard;

public class TerritoryCard extends BaseCard {
    public TerritoryCard(String description, ImageIcon imageIcon) {
        super(TerritoryCard.class.getName(), description, imageIcon);
    }
}
