package app.domain.models.card;

import javax.swing.*;

import app.common.Logger;
import app.domain.models.card.army.ArmyCard;
import app.domain.models.game.map.Territory;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

public class CentralDeck implements Deck {

    private HashMap<CardType, ArrayList<BaseCard>> _cardContainer = new HashMap<>();
    private CardFactory _cardFactory = new CardFactory();

    public CentralDeck() {
        this._cardContainer.put(CardType.Army, new ArrayList<>());
        this._cardContainer.put(CardType.Chance, new ArrayList<>());
    }

    public void initilizeArmyDeck(int amount) {
        addArmyCards(CardType.Infantry, amount * 3);
        addArmyCards(CardType.Cavalry, amount * 2);
        addArmyCards(CardType.Artillery, amount);
    }

    public void initilizeTerritoryDeck(List<Territory> territoryList) {
        for (Territory territory : territoryList) {
            try {
                addTerritoryCards(territory.getName(), territory.getImage());
            } catch (IOException e) {
                Logger.error(e);
            }
        }
    }

    private void addTerritoryCards(String name, BufferedImage image) {
    }

    public HashMap<CardType, ArrayList<BaseCard>> get_cardContainer() {
        return _cardContainer;
    }

    public void addArmyCards(CardType type, int amount) {
        for (int i = 0; i < amount; i++) {
            ArmyCard armyCard = _cardFactory.createArmyCard(type);
            // this._cardContainer.get(type).add(armyCard);
        }
    }

    public void addTerritoryCards(String description, ImageIcon imageIcon) {
        this._cardContainer.get(CardType.Army)
                .add(this._cardFactory.createTerritoryCard(description, imageIcon));
    }

    public void addChanceCards(String description, ImageIcon imageIcon) {
        this._cardContainer.get(CardType.Chance).add(this._cardFactory.createChanceCard(description, imageIcon));
    }

    @Override
    public BaseCard drawCard(CardType type) {
        return this._cardContainer.get(type).remove(0);
    }

    @Override
    public void shuffle() {
        for (Map.Entry<CardType, ArrayList<BaseCard>> entry : this._cardContainer.entrySet()) {
            Collections.shuffle(entry.getValue());
        }
    }
}
