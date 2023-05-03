package app.domain.models.Player;

import app.domain.models.Card.Deck;
import app.domain.models.Card.PlayerDeck;
import app.domain.models.GameMap.Territory;

import java.util.ArrayList;

public class Player {
    private String username;
    private Integer id;
    private Deck playerDeck;
    private ArrayList<Territory> territoryList = new ArrayList<>();

    public Player(String username, Integer idx) {
        this.username = username;
        this.id = idx;

        this.playerDeck = new PlayerDeck();
    }

    public String getUsername() {
        return username;
    }

    public Integer getId() {
        return id;
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

    public Territory getTerritory(int territoryId){
        for (Territory t: this.territoryList){
            if (territoryId == t.getTerritoryId()){
                return t;
            }
        }
        return null;
    }

    public Territory removeTerritory(int territoryId){

        Territory removedTerritory = this.getTerritory(territoryId);
        this.territoryList.remove(removedTerritory);

        return removedTerritory;
    }

    public void addTerritory(Territory t){
        this.territoryList.add(t);
    }

}
