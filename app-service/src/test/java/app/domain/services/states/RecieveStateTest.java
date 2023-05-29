package app.domain.services.states;

import app.domain.models.game.map.Territory;
import app.domain.models.game.map.TerritoryPosition;
import app.domain.models.player.Player;
import app.domain.services.PlayerService;
import app.domain.services.map.MapService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class RecieveStateTest {
    private MapService _mapService;
    private PlayerService _playerService;

    @BeforeEach
    void setUp() {
        ArrayList<Player> players = new ArrayList<>();
        _playerService = PlayerService.getInstance();
        Player testPlayer = new Player(0, "testPlayer");

        players.add(testPlayer);
        _playerService.setPlayers(players);
        _playerService.setCurrentPlayerIndex(0);

        Territory testTerritory1 = new Territory(1, "test1", "image", new TerritoryPosition(0,0), new HashSet<>());
        Territory testTerritory2 = new Territory(2, "test2", "image", new TerritoryPosition(10,10), new HashSet<>());

        testTerritory1.setOwnerId(0);
        testTerritory2.setOwnerId(1);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void placeReceivedUnitsTest() {



    }

    @Test
    void receivedUnitAmountTest() {
    }
}