package app.domain.services;

import java.util.List;

import app.domain.models.game.GameState;
import app.domain.models.game.map.Territory;
import app.domain.models.player.Player;
import app.domain.repositories.CountryRepository;
import app.domain.repositories.MapRepository;
import app.domain.repositories.PlayerRepository;

public class DbSaveLoadService implements ISaveLoadAdapter {
    private PlayerRepository _playerRepository;
    private CountryRepository _countryRepository;
    private MapRepository _mapRepsitory;

    public DbSaveLoadService() {
        _playerRepository = new PlayerRepository();
        _countryRepository = new CountryRepository();
        _mapRepsitory = new MapRepository();
    }

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
        return _mapRepsitory.buildGameMapData();
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
