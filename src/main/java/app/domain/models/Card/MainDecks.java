package app.domain.models.Card;

import app.domain.models.Card.BaseCard;
import app.domain.models.Card.army.ArmyCard;
import app.domain.models.Card.army.ArmyCardType;
import app.domain.models.Card.chance.ChanceCard;
import app.domain.models.Card.territory.TerritoryCard;

import java.awt.image.BufferedImage;

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

    public void addTerritoryCards(String description, BufferedImage image, int territoryId) {
        TerritoryCard territoryCard = _cardFactory.createTerritoryCard(description, image, territoryId);
        _territoryDeck.addCard(territoryCard);
    }

    public void addChanceCards(String description, BufferedImage image) {
        ChanceCard chanceCard = _cardFactory.createChanceCard(description, image);
        _chanceDeck.addCard(chanceCard);
    }

    public BaseCard drawCard(DeckType deckType) throws IndexOutOfBoundsException {
        switch (deckType) {
            case Army:
                return _armyDeck.drawCard();
            case Territory:
                return _territoryDeck.drawCard();
            case Chance:
                return _chanceDeck.drawCard();
            default:
                return null;
        }
    }

    public void shuffle() {
        _armyDeck.shuffle();
        _territoryDeck.shuffle();
        _chanceDeck.shuffle();
    }
}
