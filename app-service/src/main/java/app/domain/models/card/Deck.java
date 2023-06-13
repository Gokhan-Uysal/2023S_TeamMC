package app.domain.models.card;

import app.domain.models.army.ArmyUnitType;
import app.domain.models.card.army.ArtilleryCard;
import app.domain.models.card.army.CavalryCard;
import app.domain.models.card.army.InfantryCard;
import app.domain.models.card.territory.TerritoryCard;

import java.util.*;

public class Deck<CardType> {
    //Overview: Decks are unbounded, mutable stacks of Card object. They act as the containers and creators of
    //              card objects.

    private List<CardType> _deck;
    /*
     * Contains card objects.
     * Representation Invariant:
     *      The _deck list should not be null.
     *      All elements in the _deck list should correspond to a non-null Card object.
     * Abstraction Function:
     *      The deck object represents a deck of Card objects.
     *      The ordering of the elements in the "_deck" list correspond to the order of the cards in the deck. The first
     *              element represents the top card in the deck, and the last element represents the bottom card in the
     *              deck.
     *      The "_deck" list can be manipulated using standard list operations such as adding or removing cards,
     *              shuffling the deck and dealing cards from the top.
     *      A(c) = c._deck[i] | 0 <= i < c._deck.size
     *
     */

    public Deck() {
        _deck = new ArrayList<>();
    }

    public List<CardType> getDeck(){
        return this._deck;
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

    protected CardType drawTerritoryCard(int territoryId) {
        for (CardType card : this._deck) {
            if ((((TerritoryCard) card).getTerritoryId()) == territoryId) {
                this._deck.remove(card);
                return card;
            }
        }
        return null;
    }

    protected int size() {
        return this._deck.size();
    }

    protected int armyUnitCardNumber(ArmyUnitType type) {

        int cardCount = 0;

        for (CardType card : this._deck) {
            if (type.equals(ArmyUnitType.Infantry) && card instanceof InfantryCard) {
                cardCount++;
            } else if (type.equals(ArmyUnitType.Chivalry) && card instanceof CavalryCard) {
                cardCount++;
            } else if (type.equals(ArmyUnitType.Artillery) && card instanceof ArtilleryCard) {
                cardCount++;
            }
        }
        return cardCount;
    }

    protected void drawArmyUnitCard(ArmyUnitType type) {
        for (CardType card : this._deck) {
            if (type.equals(ArmyUnitType.Infantry) && card instanceof InfantryCard) {
                this._deck.remove(card);
                break;
            } else if (type.equals(ArmyUnitType.Chivalry) && card instanceof CavalryCard) {
                this._deck.remove(card);
                break;
            } else if (type.equals(ArmyUnitType.Artillery) && card instanceof ArtilleryCard) {
                this._deck.remove(card);
                break;
            }
        }
    }

    protected ArrayList<Integer> getTerritoryIdsFromTerritoryCards() {
        ArrayList<Integer> territoryIds = new ArrayList<>();

        for (CardType card : _deck) {
            TerritoryCard territoryCard = (TerritoryCard) card;
            territoryIds.add(territoryCard.getTerritoryId());
        }

        return territoryIds;
    }

    protected void emptyDeck(){
        this._deck.clear();
    }

    protected boolean isEmpty(){
        return this._deck.isEmpty();
    }
}
