package app.domain.services;

import java.util.List;

import app.domain.models.game.GameState;
import app.domain.models.game.map.Territory;
import app.domain.models.player.Player;

public class JsonSaveLoadService implements ISaveLoadAdapter {

    @Override
    public void saveMap(List<Territory> territories) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveMap'");
    }

    @Override
    public void savePlayer(List<Player> players) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'savePlayer'");
    }

    @Override
    public void saveGameState(GameState gameState) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveGameState'");
    }

    @Override
    public List<Territory> loadMap() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadMap'");
    }

    @Override
    public List<Player> loadPlayer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadPlayer'");
    }

    @Override
    public GameState loadGameState() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadGameState'");
    }

}
