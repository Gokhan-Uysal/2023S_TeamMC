package app.domain.models.card;

import app.domain.models.army.ArmyUnitType;
import app.domain.models.card.army.*;
import app.domain.models.card.territory.TerritoryCard;
import app.domain.models.game.map.Territory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    private Deck<BaseCard> _deck;

    @BeforeEach
    void setUp() {
        _deck = new Deck<>();
    }

    @AfterEach
    void tearDown() {
        _deck.emptyDeck();
    }

    @Test
    void repOk(){

    }

    @Test
    void addCard() {
        this.setUp();

        TerritoryCard testTerritoryCard = new TerritoryCard("testCard", null, 1);
        this._deck.addCard(testTerritoryCard);
        assertEquals(testTerritoryCard, _deck.getDeck().get(0));

        this.tearDown();
    }

    @Test
    void drawCard() {
        this.setUp();

        TerritoryCard testTerritoryCard = new TerritoryCard("testCard", null, 1);
        this._deck.addCard(testTerritoryCard);
        this._deck.drawCard();
        assertTrue(this._deck.isEmpty());

        this.tearDown();
    }

    @Test
    void drawTerritoryCard() {
        this.setUp();

        TerritoryCard testTerritoryCard = new TerritoryCard("testCard", null, 1);
        TerritoryCard testTerritoryCard2 = new TerritoryCard("testCard2", null, 7);
        TerritoryCard testTerritoryCard3 = new TerritoryCard("testCard3", null, 4);

        this._deck.addCard(testTerritoryCard);
        this._deck.addCard(testTerritoryCard2);
        this._deck.addCard(testTerritoryCard3);

        //from the overview, we assume that the Deck is a stack object.
        assertEquals(testTerritoryCard, this._deck.drawTerritoryCard(1));
        assertEquals(testTerritoryCard2, this._deck.drawTerritoryCard(7));
        assertNull(this._deck.drawTerritoryCard(34));
        this.tearDown();
    }

    @Test
    void armyUnitCardNumber() {
        this.setUp();

        InfantryCard testCard1 = new InfantryCard();
        InfantryCard testCard11 = new InfantryCard();
        CavalryCard testCard2 = new CavalryCard();
        ArtilleryCard testCard3 = new ArtilleryCard();

        this._deck.addCard(testCard1);
        this._deck.addCard(testCard11);
        this._deck.addCard(testCard2);
        this._deck.addCard(testCard3);

        assertEquals(2, _deck.armyUnitCardNumber(ArmyUnitType.Infantry));
        assertEquals(1, _deck.armyUnitCardNumber(ArmyUnitType.Chivalry));
        assertEquals(1, _deck.armyUnitCardNumber(ArmyUnitType.Artillery));

        this.tearDown();
    }

    @Test
    void getTerritoryIdsFromTerritoryCards() {
        this.setUp();

        TerritoryCard testTerritoryCard = new TerritoryCard("testCard", null, 1);
        TerritoryCard testTerritoryCard2 = new TerritoryCard("testCard2", null, 7);
        TerritoryCard testTerritoryCard3 = new TerritoryCard("testCard3", null, 4);

        this._deck.addCard(testTerritoryCard);
        this._deck.addCard(testTerritoryCard2);
        this._deck.addCard(testTerritoryCard3);

        Integer[] intArray = new Integer[]{1,7,4};
        Integer[] intArrayFromCards = this._deck.getTerritoryIdsFromTerritoryCards().toArray(new Integer[0]);

        assertArrayEquals(intArray, intArrayFromCards);

        this.tearDown();
    }

}