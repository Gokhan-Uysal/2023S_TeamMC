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

    public Integer get_id() {
        return _id;
    }

    public String get_username() {
        return _username;
    }

    public MainDecks get_playerDecks() {
        return this._playerDecks;
    }

    public void addArmyCard(ArmyCard armyCard) {
        _playerDecks.addArmyCard(armyCard);
    }
}
