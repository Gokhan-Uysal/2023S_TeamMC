package app.domain.models.Card;

public interface Deck {
    void addCards(CardType type, BaseCard baseCard);

    BaseCard drawCard(CardType type);

    void shuffle();
}
