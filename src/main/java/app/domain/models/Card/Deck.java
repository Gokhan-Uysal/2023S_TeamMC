package app.domain.models.Card;

public interface Deck {

    void addCards(CardType type);

    BaseCard drawCard(CardType type);

    void shuffle();
}
