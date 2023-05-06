package app.domain.models.card;

import javax.swing.*;

import app.domain.models.card.army.ArmyCard;
import app.domain.models.card.army.ArtilleryCard;
import app.domain.models.card.army.CavalryCard;
import app.domain.models.card.army.InfantryCard;
import app.domain.models.card.chance.ChanceCard;
import app.domain.models.card.territory.TerritoryCard;

public class CardFactory {

    public ArmyCard createArmyCard(CardType type) {
        if (type.equals(CardType.Artillery)) {
            return new ArtilleryCard();
        } else if (type.equals(CardType.Infantry)) {
            return new InfantryCard();
        } else if (type.equals(CardType.Cavalry)) {
            return new CavalryCard();
        } else {
            return null;
        }
    }

    public BaseCard createTerritoryCard(String description, ImageIcon imageIcon) {
        return new TerritoryCard(description, imageIcon);
    }

    public BaseCard createChanceCard(String description, ImageIcon imageIcon) {
        return new ChanceCard(description, imageIcon);
    }

}
