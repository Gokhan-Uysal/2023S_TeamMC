package app.domain.models.Card;

import javax.swing.*;

public class CardFactory {

    public BaseCard create(CardType type, Object... args){
        if (type.equals(CardType.Artillery)){
            return new ArtilleryCard((String) args[0], (ImageIcon) args[1]);
        }
        else if (type.equals(CardType.Cavalry)){
            return new CavalryCard((String) args[0], (ImageIcon) args[1]);
        }
        else if (type.equals(CardType.Infantry)){
            return new InfantryCard((String) args[0], (ImageIcon) args[1]);
        }
        else if (type.equals(CardType.Territory)){
            return new TerritoryCard((String) args[0], (ImageIcon) args[1], (int) args[2]);
        }
        else if (type.equals(CardType.Chance)){
            return new ChanceCard((String) args[0], (ImageIcon) args[1]);
        }
        else{
            return null;
        }
    }

}
