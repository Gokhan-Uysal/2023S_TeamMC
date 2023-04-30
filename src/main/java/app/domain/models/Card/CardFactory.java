package app.domain.models.Card;

import javax.swing.*;

public class CardFactory {

    public BaseCard create(CardType type, BaseCard baseCard) {
        if (type.equals(CardType.Artillery)) {
            return new ArtilleryCard(baseCard.name, baseCard.imageIcon);
        } else if (type.equals(CardType.Cavalry)) {
            return new CavalryCard(baseCard.name, baseCard.imageIcon);
        } else if (type.equals(CardType.Infantry)) {
            return new InfantryCard(baseCard.name, baseCard.imageIcon);
        } else if (type.equals(CardType.Territory)) {
            return new TerritoryCard(baseCard.name, baseCard.imageIcon, 10);
        } else if (type.equals(CardType.Chance)) {
            return new ChanceCard(baseCard.name, baseCard.imageIcon);
        } else {
            return null;
        }
    }

}
