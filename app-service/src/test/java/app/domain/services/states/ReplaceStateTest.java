package app.domain.services.states;

import app.domain.models.game.map.Territory;
import app.domain.services.PlayerService;
import app.domain.services.map.MapGraphService;
import app.domain.services.map.MapService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ReplaceStateTest {
    private MapService _mapService;
    private MapGraphService _mapGraphService;
    private PlayerService _playerService;

    @BeforeEach
    void setUp() {
        this._mapGraphService = new MapGraphService();
        this._mapService = MapService.getInstance();
        _mapService.setMapGraphService(_mapGraphService);
        this._playerService = PlayerService.getInstance();
        ArrayList<Territory> territories = new ArrayList<>();



    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void replaceUnits() {
    }
}