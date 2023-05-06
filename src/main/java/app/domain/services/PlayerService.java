package app.domain.services;

import java.util.*;

import app.domain.models.player.Player;

public class PlayerService {
    private static PlayerService _playerService;

    private final int UPPER_BOUND = 6;
    private int _currentPlayerCount;

    private List<Player> _players;
    private int _currentPlayerId;

    public Player getCurrentPlayer() throws IndexOutOfBoundsException {
        return _players.get(_currentPlayerId);
    }

    private PlayerService() {
        _players = new ArrayList<>();
        _currentPlayerCount = 0;
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

    public void turnChange() {
        if (_currentPlayerId == _players.size() - 1) {
            _currentPlayerId = 0;
            return;
        }
        _currentPlayerId += 1;
    }
}
