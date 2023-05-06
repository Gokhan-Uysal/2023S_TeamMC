package app.domain.models.Card;

import java.awt.image.BufferedImage;

import app.domain.models.Card.army.ArmyCard;
import app.domain.models.Card.army.ArmyCardType;
import app.domain.models.Card.army.ArtilleryCard;
import app.domain.models.Card.army.CavalryCard;
import app.domain.models.Card.army.InfantryCard;
import app.domain.models.Card.chance.ChanceCard;
import app.domain.models.Card.territory.TerritoryCard;

public class CardFactory {

    public ArmyCard createArmyCard(ArmyCardType type) {
        if (type.equals(ArmyCardType.Artillery)) {
            return new ArtilleryCard();
        } else if (type.equals(ArmyCardType.Infantry)) {
            return new InfantryCard();
        } else if (type.equals(ArmyCardType.Cavalry)) {
            return new CavalryCard();
        } else {
            return null;
        }
    }

    public TerritoryCard createTerritoryCard(String description, BufferedImage image, int territoryId) {
        return new TerritoryCard(description, image, territoryId);
    }

    public ChanceCard createChanceCard(String description, BufferedImage image) {
        return new ChanceCard(description, image);
    }
}
