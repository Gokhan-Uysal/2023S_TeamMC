package app.domain.services;

import java.util.*;

import app.domain.models.game.map.Territory;
import app.domain.models.player.Player;
import app.domain.services.map.MapService;

public class PlayerService {
    private static PlayerService _playerService;

    private final int UPPER_BOUND = 6;
    private int _currentPlayerCount;

    private List<Player> _players;
    private int _currentPlayerId;
    private MapService _mapService;

    public Player getCurrentPlayer() throws IndexOutOfBoundsException {
        return _players.get(_currentPlayerId);
    }

    private PlayerService() {
        _players = new ArrayList<>();
        _currentPlayerCount = 0;
        _mapService = MapService.getInstance();
    }

    public static PlayerService getInstance() {
        if (_playerService == null) {
            _playerService = new PlayerService();
        }
        return _playerService;
    }

    public void createPlayers(List<String> names) {
        names.forEach((name) -> {
            if (_currentPlayerCount > UPPER_BOUND) {
                return;
            }
            Player player = new Player(_currentPlayerCount, name);
            _players.add(player);
            _currentPlayerCount++;
        });
    }

    public void removePlayer(Player player) {
        _players.remove(player);
    }

    public void removePlayer(int index) {
        _playerService.removePlayer(index);
    }

    public int getPlayerCount() {
        return _players.size();
    }

    public Player getPlayerById(int id) {
        for (Player player : _players) {
            if (player.getId() == id) {
                return player;
            }
        }
        throw new Error(String.format("Player not found by: %d\n", id));
    }

    public void turnChange() {
        if (_currentPlayerId == _players.size() - 1) {
            _currentPlayerId = 0;
            return;
        }
        _currentPlayerId += 1;
    }

    public void resatrtTurn() {
        _currentPlayerId = 0;
    }

    public Player getPlayer(int playerId) {
        for (Player player : this._players) {
            if (player.getId() == playerId) {
                return player;
            }
        }
        return null;
    }

    public ArrayList<Territory> getTerritoriesFromTerritoryCards(int playerId) {

        Player player = getPlayer(playerId);
        ArrayList<Integer> territoryIds = player.getPlayerDecks().territoryIds();
        return _mapService.findTerritories(territoryIds);
    }

    public boolean checkIfPlayerOwnsTerritory(int playerId, int territoryId) {
        return playerId == _mapService.findTerritory(territoryId).getOwnerId();
    }

    public void emptyPlayerDecks() {
        for (Player player : this._players) {
            player.getPlayerDecks().emptyDeck();
        }
    }

    public ArrayList<String> getTerritoryCardNames() {
        ArrayList<String> territoryNames = new ArrayList<>();
        ArrayList<Territory> territoryList = this.getTerritoriesFromTerritoryCards(_currentPlayerId);

        for (Territory territory : territoryList) {
            territoryNames.add(territory.getName());
        }

        return territoryNames;
    }

    public int numberOfTerritories(int playerId) {
        Player player = this.getPlayer(playerId);
        int territoryCount = 0;

        for (Territory territory : _mapService.getTerritoryListFromGraph()) {
            if (territory.getOwnerId() == player.getId()) {
                territoryCount++;
            }
        }

        return territoryCount;
    }
}
