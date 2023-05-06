package app.domain.models.card;

import javax.swing.*;

public interface Deck {

    void addArmyCards(CardType type, String description, ImageIcon imageIcon, int amount);

    void addTerritoryCards(String description, ImageIcon imageIcon, int territoryID);

    void addChanceCards(String description, ImageIcon imageIcon);

    BaseCard drawCard(CardType type);

    void shuffle();
}
