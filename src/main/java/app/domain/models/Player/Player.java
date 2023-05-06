package app.domain.models.player;

import app.domain.models.card.PlayerDeck;
import app.domain.models.game.map.Territory;

import java.util.ArrayList;

public class Player {
    private Integer id;
    private String username;
    private PlayerDeck playerDeck;
    private ArrayList<Territory> territoryList = new ArrayList<>();

    public Player(String username, Integer id) {
        this.username = username;
        this.id = id;

        this.playerDeck = new PlayerDeck();
    }

    public String getUsername() {
        return username;
    }

    public Integer getId() {
        return id;
    }

    public PlayerDeck getPlayerDeck() {
        return playerDeck;
    }

    public ArrayList<Territory> getTerritoryList() {
        return territoryList;
    }

    public boolean isOwnerOf(String territoryName) {
        for (Territory t : this.territoryList) {
            if (territoryName.equals(t.getName())) {
                return true;
            }
        }
        return false;
    }

    public Territory getTerritory(int territoryId) {
        for (Territory t : this.territoryList) {
            if (territoryId == t.getTerritoryId()) {
                return t;
            }
        }
        return null;
    }

    public Territory removeTerritory(int territoryId) {

        Territory removedTerritory = this.getTerritory(territoryId);
        this.territoryList.remove(removedTerritory);

        return removedTerritory;
    }

    public void addTerritory(Territory t) {
        this.territoryList.add(t);
    }

}
