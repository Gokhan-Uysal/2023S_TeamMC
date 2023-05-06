package app.domain.models.card;

import javax.swing.*;

public interface Deck {

    void addArmyCards(CardType type, int amount);

    void addTerritoryCards(String description, ImageIcon imageIcon);

    void addChanceCards(String description, ImageIcon imageIcon);

    BaseCard drawCard(CardType type);

    void shuffle();
}
