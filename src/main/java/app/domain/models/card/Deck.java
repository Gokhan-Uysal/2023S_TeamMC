package app.domain.models.card;

import app.domain.models.army.ArmyUnitType;
import app.domain.models.card.army.ArtilleryCard;
import app.domain.models.card.army.CavalryCard;
import app.domain.models.card.army.InfantryCard;
import app.domain.models.card.territory.TerritoryCard;
import app.domain.models.game.map.Territory;

import java.util.*;

public class Deck<CardType> {
    private List<CardType> _deck;

    public Deck() {
        _deck = new ArrayList<>();
    }

    protected void addCard(CardType card) {
        _deck.add(card);
    }

    protected void addCards(Collection<CardType> cards) {
        _deck.addAll(cards);
    }

    protected CardType drawCard() throws IndexOutOfBoundsException, UnsupportedOperationException {
        return _deck.remove(0);
    }

    protected void shuffle() {
        Collections.shuffle(_deck);
    }

    protected void drawTerritoryCard(int territoryId){
        for (CardType card: this._deck){
            TerritoryCard territoryCard = (TerritoryCard) card;
            if (territoryCard.getTerritoryId() == territoryId){
                this._deck.remove(territoryCard);
            }
        }
    }

    protected int size(){
        return this._deck.size();
    }

    protected int armyUnitCardNumber(ArmyUnitType type){

        int cardCount = 0;

        for (CardType card: this._deck){
            if (type.equals(ArmyUnitType.Infantry) && card instanceof InfantryCard){
                cardCount++;
            } else if (type.equals(ArmyUnitType.Chivalry) && card instanceof CavalryCard) {
                cardCount++;
            } else if (type.equals(ArmyUnitType.Artillery) && card instanceof ArtilleryCard) {
                cardCount++;
            }
        }
        return cardCount;
    }

    protected void drawArmyUnitCard(ArmyUnitType type){
        for (CardType card: this._deck){
            if (type.equals(ArmyUnitType.Infantry) && card instanceof InfantryCard){
                this._deck.remove(card);
            } else if (type.equals(ArmyUnitType.Chivalry) && card instanceof CavalryCard) {
                this._deck.remove(card);
            } else if (type.equals(ArmyUnitType.Artillery) && card instanceof ArtilleryCard) {
                this._deck.remove(card);
            }
        }
    }

    protected ArrayList<Integer> getTerritoryIdsFromTerritoryCards(){

        ArrayList<Integer> territoryIds = new ArrayList<>();

        for (CardType card: _deck){

            TerritoryCard territoryCard = (TerritoryCard) card;

            territoryIds.add(territoryCard.getTerritoryId());
        }

        return territoryIds;
    }
}
