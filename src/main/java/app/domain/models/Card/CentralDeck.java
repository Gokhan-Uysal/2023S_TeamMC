package app.domain.models.Card;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CentralDeck implements Deck {

    private HashMap<CardType, ArrayList<BaseCard>> cardContainer = new HashMap<>();
    private CardFactory cardFactory = new CardFactory();

    public CentralDeck() {
        this.cardContainer.put(CardType.Army, new ArrayList<>());
        this.cardContainer.put(CardType.Chance, new ArrayList<>());
    }

    public void addArmyCards(CardType type, String description, ImageIcon imageIcon) {
        this.cardContainer.get(type).add(this.cardFactory.createArmyCard(type, description, imageIcon));
    }

    public void addTerritoryCards(String description, ImageIcon imageIcon, int territoryId){
        this.cardContainer.get(CardType.Army).add(this.cardFactory.createTerritoryCard(description, imageIcon, territoryId));
    }

    public void addChanceCards(String description, ImageIcon imageIcon){
        this.cardContainer.get(CardType.Chance).add(this.cardFactory.createChanceCard(description, imageIcon));
    }

    @Override
    public BaseCard drawCard(CardType type) {
        return this.cardContainer.get(type).remove(0);
    }

    @Override
    public void shuffle() {
        for (Map.Entry<CardType, ArrayList<BaseCard>> entry : this.cardContainer.entrySet()) {
            Collections.shuffle(entry.getValue());
        }
    }
}
