package app.domain.models.Card.chance;

import java.awt.image.BufferedImage;

import app.domain.models.Card.BaseCard;

public class ChanceCard extends BaseCard {
    public ChanceCard(String description, BufferedImage image) {
        super(ChanceCard.class.getName(), description, image);
    }

}
