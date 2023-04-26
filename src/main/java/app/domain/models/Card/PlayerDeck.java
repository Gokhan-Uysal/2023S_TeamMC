package app.domain.models.Card;

import java.util.*;

public class PlayerDeck implements Deck {

    private HashMap<CardType, ArrayList<BaseCard>> cardContainer = new HashMap<>();
    private CardFactory cardFactory = new CardFactory();

    public PlayerDeck(){
        this.cardContainer.put(CardType.Infantry, new ArrayList<>());
        this.cardContainer.put(CardType.Chivalry, new ArrayList<>());
        this.cardContainer.put(CardType.Artillery, new ArrayList<>());
        this.cardContainer.put(CardType.Territory, new ArrayList<>());
        this.cardContainer.put(CardType.Chance, new ArrayList<>());
    }

    @Override
    public void addCards(CardType type, Object...args) {
        BaseCard addedCard = cardFactory.create(type, args);
        this.cardContainer.get(type).add(addedCard);
    }

    @Override
    public BaseCard drawCard(CardType type) {
        return this.cardContainer.get(type).remove(0);
    }

    @Override
    public void shuffle() {
        for (Map.Entry<CardType, ArrayList<BaseCard>> entry: this.cardContainer.entrySet()){
            Collections.shuffle(entry.getValue());
        }
    }
}
