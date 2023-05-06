package app.domain.models.player;

import app.domain.models.card.MainDecks;

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

    public String getUsername() {
        return username;
    }
}
