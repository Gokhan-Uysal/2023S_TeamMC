package app.domain.models.player;

import app.domain.models.card.MainDecks;
import app.domain.models.game.map.Territory;

import java.util.ArrayList;

public class Player {
    private int id;
    private String username;
    private MainDecks playerDecks;
    // private ArrayList<Territory> territoryList = new ArrayList<>();

    public Player(int id, String username) {
        this.username = username;
        this.id = id;

        this.playerDecks = new MainDecks();
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public MainDecks getPlayerDecks(){
        return this.playerDecks;
    }

    // public boolean isOwnerOf(String territoryName) {
    // for (Territory t : this.territoryList) {
    // if (territoryName.equals(t.getName())) {
    // return true;
    // }
    // }
    // return false;
    // }

    // public void addTerritory(Territory t) {
    // this.territoryList.add(t);
    // }

}
