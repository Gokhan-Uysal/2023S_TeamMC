package app.domain.models.card;

import app.domain.models.army.ArmyUnitType;
import app.domain.models.card.army.ArmyCard;
import app.domain.models.card.army.ArmyCardType;
import app.domain.models.card.chance.ChanceCard;
import app.domain.models.card.territory.TerritoryCard;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MainDecks {
    private Deck<ArmyCard> _armyDeck;
    private Deck<TerritoryCard> _territoryDeck;
    private Deck<ChanceCard> _chanceDeck;
    private CardFactory _cardFactory;

    public MainDecks() {
        _cardFactory = new CardFactory();
        _armyDeck = new Deck<>();
        _territoryDeck = new Deck<>();
        _chanceDeck = new Deck<>();
    }

    public void addArmyCards(ArmyCardType type, int amount) {
        for (int i = 0; i < amount; i++) {
            addArmyCard(type);
        }
    }

    public void addArmyCard(ArmyCardType type) {
        ArmyCard armyCard = _cardFactory.createArmyCard(type);
        _armyDeck.addCard(armyCard);
    }

    public void addArmyCard(ArmyCard armyCard) {
        _armyDeck.addCard(armyCard);
    }

    public void addTerritoryCards(String description, BufferedImage image, int territoryId) {
        TerritoryCard territoryCard = _cardFactory.createTerritoryCard(description, image, territoryId);
        _territoryDeck.addCard(territoryCard);
    }

    public void addChanceCards(String description, BufferedImage image) {
        ChanceCard chanceCard = _cardFactory.createChanceCard(description, image);
        _chanceDeck.addCard(chanceCard);
    }

    public BaseCard drawCard(DeckType deckType) throws IndexOutOfBoundsException {
        return switch (deckType) {
            case Army -> _armyDeck.drawCard();
            case Territory -> _territoryDeck.drawCard();
            case Chance -> _chanceDeck.drawCard();
            default -> null;
        };
    }

    public BaseCard drawTerritoryCard(int territoryId) {
        return this._territoryDeck.drawTerritoryCard(territoryId);
    }

    public int armyUnitCardNumbers(ArmyUnitType type) {
        return this._armyDeck.armyUnitCardNumber(type);
    }

    public void drawArmyUnitCard(ArmyUnitType type) {
        this._armyDeck.drawArmyUnitCard(type);
    }

    public ArrayList<Integer> territoryIds() {
        return this._territoryDeck.getTerritoryIdsFromTerritoryCards();
    }

    public void shuffle() {
        _armyDeck.shuffle();
        _territoryDeck.shuffle();
        _chanceDeck.shuffle();
    }

    public void emptyDeck(){
        _armyDeck.emptyDeck();
        _territoryDeck.emptyDeck();
    }

    public boolean isEmpty(){
        return _territoryDeck.isEmpty() && _armyDeck.isEmpty();
    }

    public boolean isEmpty(DeckType deckType){
        if (deckType.equals(DeckType.Army)){
            return _armyDeck.isEmpty();
        }
        else if (deckType.equals(DeckType.Territory)){
            return _territoryDeck.isEmpty();
        }
        return false;
    }
}
