package app.domain.models.card;

import java.awt.image.BufferedImage;

import app.domain.models.card.army.ArmyCard;
import app.domain.models.card.army.ArmyCardType;
import app.domain.models.card.army.ArtilleryCard;
import app.domain.models.card.army.CavalryCard;
import app.domain.models.card.army.InfantryCard;
import app.domain.models.card.chance.ChanceCard;
import app.domain.models.card.territory.TerritoryCard;

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

    public TerritoryCard createTerritoryCard(String description, BufferedImage image) {
        return new TerritoryCard(description, image);
    }

    public ChanceCard createChanceCard(String description, BufferedImage image) {
        return new ChanceCard(description, image);
    }
}
