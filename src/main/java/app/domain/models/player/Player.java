package app.domain.models.player;

import app.domain.models.card.MainDecks;
import app.domain.models.card.army.ArmyCard;

public class Player {
    private int _id;
    private String _username;
    private MainDecks _playerDecks;

    public Player(int id, String username) {
        this._username = username;
        this._id = id;

        this._playerDecks = new MainDecks();
    }

    public Integer getId() {
        return _id;
    }

    public String getUsername() {
        return _username;
    }

    public MainDecks getPlayerDecks() {
        return this._playerDecks;
    }
}
