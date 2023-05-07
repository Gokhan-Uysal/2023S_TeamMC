package app.domain.models.card.chance;

import java.awt.image.BufferedImage;

import app.domain.models.card.BaseCard;

public class ChanceCard extends BaseCard {
    public ChanceCard(String description, BufferedImage image) {
        super(ChanceCard.class.getName(), description, image);
    }

}
