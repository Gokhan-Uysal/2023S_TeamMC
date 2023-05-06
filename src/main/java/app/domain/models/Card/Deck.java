package app.domain.models.Card;

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
}
