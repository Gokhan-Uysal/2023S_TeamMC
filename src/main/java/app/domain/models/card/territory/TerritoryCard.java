package app.domain.models.card.territory;

import java.awt.image.BufferedImage;

import app.domain.models.card.BaseCard;

public class TerritoryCard extends BaseCard {

    private int territoryId;

    public TerritoryCard(String description, BufferedImage image, int territoryId) {
        super(TerritoryCard.class.getName(), description, image);

        this.territoryId = territoryId;
    }

    public int getTerritoryId(){
        return this.territoryId;
    }
}
