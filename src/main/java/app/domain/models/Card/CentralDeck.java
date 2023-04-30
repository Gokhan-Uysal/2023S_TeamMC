package app.domain.models.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CentralDeck implements Deck {

    private HashMap<CardType, ArrayList<BaseCard>> cardContainer = new HashMap<>();
    private CardFactory cardFactory = new CardFactory();

    public CentralDeck() {
        this.cardContainer.put(CardType.Army, new ArrayList<>());
        this.cardContainer.put(CardType.Territory, new ArrayList<>());
        this.cardContainer.put(CardType.Chance, new ArrayList<>());
    }

    @Override
    public void addCards(CardType type, BaseCard baseCard) {
        BaseCard addedCard = cardFactory.create(type, baseCard);
        if (type.equals(CardType.Artillery) || type.equals(CardType.Infantry) || type.equals(CardType.Cavalry)
                || type.equals(CardType.Territory)) {
            this.cardContainer.get(CardType.Army).add(addedCard);
        } else if (type.equals(CardType.Chance)) {
            this.cardContainer.get(CardType.Chance).add(addedCard);
        }
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
