package app.domain.models.Card.army;

import java.awt.image.BufferedImage;

import app.domain.models.Card.BaseCard;

public abstract class ArmyCard extends BaseCard {
    protected int value;

    protected ArmyCard(String description, BufferedImage image, int value) {
        super(ArmyCard.class.getName(), description, image);
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
