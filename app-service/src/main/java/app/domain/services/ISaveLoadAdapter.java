package app.domain.services;

import java.util.List;

import app.domain.models.game.GameState;
import app.domain.models.game.map.Territory;
import app.domain.models.player.Player;

public interface ISaveLoadAdapter {

    public void saveMap(List<Territory> territories);

    public void savePlayer(List<Player> players);

    public void saveGameState(GameState gameState);

    public List<Territory> loadMap();

    public List<Player> loadPlayer();

    public GameState loadGameState();

}
