package app.domain.models.Card;

import javax.swing.ImageIcon;

public class TerritoryCard extends BaseCard {
    private int territoryId;

    protected TerritoryCard(String description, ImageIcon imageIcon, int territoryId) {
        super(TerritoryCard.class.getName(), description, imageIcon);
        this.territoryId = territoryId;
    }

    public int getTerritoryId() {
        return territoryId;
    }
}
