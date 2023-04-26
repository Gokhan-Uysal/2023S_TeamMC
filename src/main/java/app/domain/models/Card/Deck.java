package app.domain.models.Card;

public interface Deck {

    void addCards(CardType type, Object... args);

    BaseCard drawCard(CardType type);

    void shuffle();
}
