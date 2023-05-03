package app.domain.models.Card;

import javax.swing.*;
import java.util.*;

public class PlayerDeck implements Deck {

    private HashMap<CardType, ArrayList<BaseCard>> cardContainer = new HashMap<>();
    private CardFactory cardFactory = new CardFactory();

    public PlayerDeck() {
        this.cardContainer.put(CardType.Infantry, new ArrayList<>());
        this.cardContainer.put(CardType.Cavalry, new ArrayList<>());
        this.cardContainer.put(CardType.Artillery, new ArrayList<>());
        this.cardContainer.put(CardType.Territory, new ArrayList<>());
        this.cardContainer.put(CardType.Chance, new ArrayList<>());
    }

    public void addArmyCards(CardType type, String description, ImageIcon imageIcon) {
        this.cardContainer.get(type).add(this.cardFactory.createArmyCard(type, description, imageIcon));
    }

    public void addTerritoryCards(String description, ImageIcon imageIcon, int territoryId) {
        this.cardContainer.get(CardType.Territory).add(this.cardFactory.createTerritoryCard(description, imageIcon, territoryId));
    }

    public void addChanceCards(String description, ImageIcon imageIcon) {
        this.cardContainer.get(CardType.Chance).add(this.cardFactory.createChanceCard(description, imageIcon));
    }

    @Override
    public void drawCard(CardType type, int amount) {
        if (amount > 0) {
            this.cardContainer.get(type).subList(0, amount).clear();
        }
    }

    @Override
    public void shuffle() {
        for (Map.Entry<CardType, ArrayList<BaseCard>> entry : this.cardContainer.entrySet()) {
            Collections.shuffle(entry.getValue());
        }
    }

    public int findCardAmount(CardType type){
        return this.cardContainer.get(type).size();
    }
}
