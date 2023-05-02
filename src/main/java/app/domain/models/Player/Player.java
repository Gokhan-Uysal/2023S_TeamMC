package app.domain.models.Player;

import app.domain.models.ArmyUnit.Army;
import app.domain.models.Card.Deck;

import java.util.ArrayList;

public class Player {
    private String username;
    private Integer id;
    private Army playerArmy;
    private Deck playerDeck;
    private ArrayList<Integer> territoryList;

    public Player(String username, Integer idx){
        this.username = username;
        this.id = idx;
    }

    public String getUsername() {
        return username;
    }

    public Integer getId() {
        return id;
    }

    public Army getPlayerArmy() {
        return playerArmy;
    }

    public Deck getPlayerDeck() {
        return playerDeck;
    }

    public ArrayList<Integer> getTerritoryList() {
        return territoryList;
    }


}
