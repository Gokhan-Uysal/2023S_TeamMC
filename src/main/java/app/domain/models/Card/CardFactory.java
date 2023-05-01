package app.domain.models.Card;

public class CardFactory {

    public ArmyCard createArmyCard(CardType type, String description, ImageIcon imageIcon) {
        if (type.equals(CardType.Artillery)) {
            return new ArtilleryCard(description, imageIcon);
        }
        else if (type.equals(CardType.Infantry)){
            return new InfantryCard(description, imageIcon);
        }
        else if (type.equals(CardType.Cavalry)){
            return new CavalryCard(description, imageIcon);
        }
        else{
            return null;
        }

    }

    public BaseCard createTerritoryCard(String description, ImageIcon imageIcon, int territoryId){
        return new TerritoryCard(description, imageIcon, territoryId);
    }

    public BaseCard createChanceCard(String description, ImageIcon imageIcon){
        return new ChanceCard(description, imageIcon);
    }

}
