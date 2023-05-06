package app.domain.models.card;

import javax.swing.ImageIcon;

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
