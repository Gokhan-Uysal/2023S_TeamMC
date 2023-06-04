package app.domain.services.states;

import app.domain.models.army.ArmyUnitType;
import app.domain.models.game.map.Territory;
import app.domain.models.game.map.TerritoryPosition;
import app.domain.models.player.Player;
import app.domain.services.PlayerService;
import app.domain.services.map.MapGraphService;
import app.domain.services.map.MapService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class ReceiveStateTest {
    private ReceiveState _receiveState;
    private MapService _mapService;
    private PlayerService _playerService;
    private MapGraphService _mapGraphService;

    void addTerritoryToGraph(){
        Territory testTerritory = new Territory(12,"t9", "t9", new TerritoryPosition(40,40),
                new HashSet<String>());
        testTerritory.setOwnerId(0);
        _mapGraphService.addVertex(testTerritory);
    }

    @BeforeEach
    void setUp() {
        _receiveState = new ReceiveState();

        ArrayList<Player> players = new ArrayList<>();
        _playerService = PlayerService.getInstance();

        _mapService = MapService.getInstance();
        _mapGraphService = new MapGraphService();
        _mapService.setMapGraphService(_mapGraphService);

        ArrayList<Territory> territories = new ArrayList<>();

        Territory territory1 = new Territory(5,"t1", "t1", new TerritoryPosition(0,0),
                new HashSet<String>());
        Territory territory2 = new Territory(6,"t2", "t2", new TerritoryPosition(5,5),
                new HashSet<String>());
        Territory territory3 = new Territory(7,"t3", "t3", new TerritoryPosition(10,10),
                new HashSet<String>());
        Territory territory4 = new Territory(8,"t4", "t4", new TerritoryPosition(15,15),
                new HashSet<String>());
        Territory territory5 = new Territory(9,"t5", "t5", new TerritoryPosition(20,20),
                new HashSet<String>());
        Territory territory6 = new Territory(10,"t6", "t6", new TerritoryPosition(25,25),
                new HashSet<String>());
        Territory territory7 = new Territory(11,"t7", "t7", new TerritoryPosition(30,30),
                new HashSet<String>());
        Territory territory8 = new Territory(12,"t8", "t8", new TerritoryPosition(35,35),
                new HashSet<String>());

        territory1.setOwnerId(0);
        territory2.setOwnerId(0);
        territory3.setOwnerId(0);
        territory4.setOwnerId(0);
        territory5.setOwnerId(0);
        territory6.setOwnerId(0);
        territory7.setOwnerId(0);
        territory8.setOwnerId(0);

        territories.add(territory1);
        territories.add(territory2);
        territories.add(territory3);
        territories.add(territory4);
        territories.add(territory5);
        territories.add(territory6);
        territories.add(territory7);
        territories.add(territory8);

        _mapGraphService.addVerticies(territories);

        Player testPlayer = new Player(0, "testPlayer");
        players.add(testPlayer);
        _playerService.setPlayers(players);
        _playerService.setCurrentPlayerIndex(0);
    }

    @AfterEach
    void tearDown() {
        _mapGraphService.clear();
        _playerService.getPlayers().clear();
    }

    @Test
    void placeNegativeAmountOfUnits(){
        this.setUp();

        Territory testTerritory = new Territory(1, "test", "image",
                new TerritoryPosition(0,0), new HashSet<>());
        testTerritory.setOwnerId(0);

        _receiveState.placeReceivedUnits(testTerritory, -1);
        assertEquals(testTerritory.getTerritoryArmy().getArmyAmount(ArmyUnitType.Infantry), 0);

        this.tearDown();
    }

    @Test
    void placeZeroUnits(){
        this.setUp();

        Territory testTerritory = new Territory(1, "test", "image",
                new TerritoryPosition(0,0), new HashSet<>());
        testTerritory.setOwnerId(0);

        _receiveState.placeReceivedUnits(testTerritory, 0);
        assertEquals(testTerritory.getTerritoryArmy().getArmyAmount(ArmyUnitType.Infantry), 0);

        this.tearDown();
    }

    @Test
    void placeOneUnit(){
        this.setUp();

        Territory testTerritory = new Territory(1, "test", "image",
                new TerritoryPosition(0,0), new HashSet<>());
        testTerritory.setOwnerId(0);

        _receiveState.placeReceivedUnits(testTerritory, 1);
        assertEquals(testTerritory.getTerritoryArmy().getArmyAmount(ArmyUnitType.Infantry), 1);

        this.tearDown();
    }

    @Test
    void placeMoreThanOneUnit(){
        this.setUp();

        Territory testTerritory = new Territory(1, "test", "image",
                new TerritoryPosition(0,0), new HashSet<>());
        testTerritory.setOwnerId(0);

        _receiveState.placeReceivedUnits(testTerritory, 2);
        assertEquals(testTerritory.getTerritoryArmy().getArmyAmount(ArmyUnitType.Infantry), 2);

        _receiveState.placeReceivedUnits(testTerritory, 3);
        assertEquals(testTerritory.getTerritoryArmy().getArmyAmount(ArmyUnitType.Infantry), 5);

        _receiveState.placeReceivedUnits(testTerritory, 4);
        assertEquals(testTerritory.getTerritoryArmy().getArmyAmount(ArmyUnitType.Infantry), 9);

        _receiveState.placeReceivedUnits(testTerritory, 5);
        assertEquals(testTerritory.getTerritoryArmy().getArmyAmount(ArmyUnitType.Infantry), 14);

        this.tearDown();
    }

    @Test
    void placeArmyUnitsOnAnotherPlayersTerritory(){
        this.setUp();

        Territory testTerritory = new Territory(1, "test", "image",
                new TerritoryPosition(10,10), new HashSet<>());
        testTerritory.setOwnerId(1);

        assertThrows(Error.class, () -> _receiveState.placeReceivedUnits(testTerritory, 1));

        this.tearDown();
    }

    @Test
    void receivedUnitAmountTerritoryAmountLesserThanNine(){
        this.setUp();

        int receivedAmount = _receiveState.receivedUnitAmount();
        assertEquals(3, receivedAmount);

        this.tearDown();
    }

    @Test
    void receivedUnitAmountTerritoryAmountIsNine(){
        this.setUp();
        this.addTerritoryToGraph();

        int receivedAmount = _receiveState.receivedUnitAmount();
        assertEquals(3, receivedAmount);

        this.tearDown();
    }

    @Test
    void receivedUnitAmountTerritoryAmountBetweenNineAndTwelve(){
        this.setUp();

        //The player owns 10 territories
        this.addTerritoryToGraph();
        this.addTerritoryToGraph();

        int receivedAmount = _receiveState.receivedUnitAmount();
        assertEquals(3, receivedAmount);

        //The player owns 11 territories
        this.addTerritoryToGraph();

        receivedAmount = _receiveState.receivedUnitAmount();
        assertEquals(3, receivedAmount);

        this.tearDown();
    }

    @Test
    void receivedUnitAmountTerritoryAmountIsTwelve(){
        this.setUp();

        //The player has 12 units.
        for (int i = 0; i < 4; i++){
            this.addTerritoryToGraph();
        }

        int receivedAmount = _receiveState.receivedUnitAmount();
        assertEquals(4, receivedAmount);

        this.tearDown();
    }

    @Test
    void receivedUnitAmountTerritoryAmountBetweenTwelveAndFifteen(){
        this.setUp();

        //The player has 13 units.
        for (int i = 0; i < 5; i++){
            this.addTerritoryToGraph();
        }

        int receivedAmount = _receiveState.receivedUnitAmount();
        assertEquals(4, receivedAmount);

        //The player has 14 units.
        this.addTerritoryToGraph();

        receivedAmount = _receiveState.receivedUnitAmount();
        assertEquals(4, receivedAmount);

        this.tearDown();
    }

    @Test
    void receivedUnitAmountTerritoryAmountIsFifteen(){
        this.setUp();

        //The player has 15 units.
        for (int i = 0; i < 7; i++){
            this.addTerritoryToGraph();
        }

        int receivedAmount = _receiveState.receivedUnitAmount();
        assertEquals(5, receivedAmount);

        this.tearDown();
    }

    //Should not happen under normal circumstances, but checking anyway for the sake of completion.
    @Test
    void receiveUnitAmountTerritoryAmountIsZero(){
        this.setUp();
        _mapGraphService.clear();

        int receivedAmount = _receiveState.receivedUnitAmount();
        assertEquals(3, receivedAmount);

        this.tearDown();
    }

}