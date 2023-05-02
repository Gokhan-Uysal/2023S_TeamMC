package app.domain.models.Player;

import app.domain.models.ArmyUnit.Army;
import app.domain.models.Card.Deck;
import app.domain.models.Card.PlayerDeck;
import app.domain.models.GameMap.Territory;

import java.util.ArrayList;

public class Player {
    private String username;
    private Integer id;
    private Army playerArmy;
    private Deck playerDeck;
    private ArrayList<Territory> territoryList = new ArrayList<>();

    public Player(String username, Integer idx){
        this.username = username;
        this.id = idx;

        this.playerArmy = new Army();
        this.playerDeck = new PlayerDeck();
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

    public ArrayList<Territory> getTerritoryList() {
        return territoryList;
    }

    public boolean isOwnerOf(String territoryName){
        for (Territory t: this.territoryList){
            if (territoryName.equals(t.getName())){
                return true;
            }
        }
        return false;
    }

    public Territory getTerritory(String territoryName){
        for (Territory t: this.territoryList){
            if (territoryName.equals(t.getName())){
                return t;
            }
        }
        return null;
    }

}
