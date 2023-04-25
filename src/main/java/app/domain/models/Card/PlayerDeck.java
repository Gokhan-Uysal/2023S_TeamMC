package app.domain.models.Card;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerDeck implements Deck {

    private HashMap<CardType, ArrayList<BaseCard>> cardContainer;

    public PlayerDeck(){
        this.cardContainer = new HashMap<>();
    }

    @Override
    public void addCards(CardType type) {

    }

    @Override
    public BaseCard drawCard(CardType type) {
        return null;
    }

    @Override
    public void shuffle() {

    }
}
