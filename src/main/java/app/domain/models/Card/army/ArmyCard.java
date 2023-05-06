package app.domain.models.card.army;

import javax.swing.ImageIcon;

import app.domain.models.card.BaseCard;

public abstract class ArmyCard extends BaseCard {
    protected int value;

    protected ArmyCard(String description, ImageIcon imageIcon, int value) {
        super(ArmyCard.class.getName(), description, imageIcon);
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
