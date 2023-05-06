package app.domain.models.card.territory;

import java.awt.image.BufferedImage;

import app.domain.models.card.BaseCard;

public class TerritoryCard extends BaseCard {
    public TerritoryCard(String description, BufferedImage image) {
        super(TerritoryCard.class.getName(), description, image);
    }
}
