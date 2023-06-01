package app.domain.services.states;

import app.common.errors.AttackError;
import app.domain.models.army.Army;
import app.domain.models.army.ArmyUnitType;
import app.domain.models.game.map.Territory;
import app.domain.models.game.map.TerritoryPosition;
import app.domain.models.player.Player;
import app.domain.services.PlayerService;
import app.domain.services.map.MapGraphService;
import app.domain.services.map.MapService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.lang.model.util.AbstractTypeVisitor14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class AttackStateTest {
    private AttackState _attackState;
    private MapService _mapService;
    private MapGraphService _mapGraphService;

    private void resetArmies(Army playerArmy){
        playerArmy.getArmyUnits(ArmyUnitType.Infantry,
                playerArmy.getArmyAmount(ArmyUnitType.Infantry));
        playerArmy.getArmyUnits(ArmyUnitType.Chivalry,
                playerArmy.getArmyAmount(ArmyUnitType.Chivalry));
        playerArmy.getArmyUnits(ArmyUnitType.Artillery,
                playerArmy.getArmyAmount(ArmyUnitType.Artillery));
    }

    @BeforeEach
    void setUp() {
        this._attackState = new AttackState();
        this._mapService = MapService.getInstance();
        this._mapGraphService = new MapGraphService();

        _mapService.setMapGraphService(_mapGraphService);

        ArrayList<Territory> territories = new ArrayList<>();

        Territory attackerTerritory = new Territory(0,"t1", "t1",
                new TerritoryPosition(0,0), new HashSet<>(Arrays.asList("t2")));
        Territory defenderTerritory = new Territory(1, "t2", "t2",
                new TerritoryPosition(5,5), new HashSet<>(Arrays.asList("t1")));

        attackerTerritory.setOwnerId(0);
        defenderTerritory.setOwnerId(1);

        territories.add(attackerTerritory);
        territories.add(defenderTerritory);

        _mapGraphService.addVerticies(territories);
        _mapGraphService.addEdges(territories);
    }

    @AfterEach
    void tearDown() {
        _mapGraphService.clear();
    }

    @Test
    void validateAttackWhenAttackingFromAnotherPlayersTerritory() {
        this.setUp();
        assertThrows(AttackError.class, () -> _attackState.validateAttack(1,0,0),
                "Please choose one of your own territories.");
        this.tearDown();
    }

    @Test
    void validateAttackWhenTerritoriesNotAdjacent(){
        this.setUp();

        ArrayList<Territory> territories = new ArrayList<>();
        Territory testTerritory = new Territory(2, "t3", "t3", new TerritoryPosition(10,10),
                                    new HashSet<>(Arrays.asList("t2")));
        territories.add(testTerritory);
        testTerritory.setOwnerId(1);
        _mapGraphService.addVerticies(territories);
        _mapGraphService.addEdges(territories);

        assertThrows(AttackError.class, () -> _attackState.validateAttack(2,0,1),
                "Territory not adjacent or not enemy territory.");

        this.tearDown();
    }

    @Test
    void validateAttackWhenInsufficientArmyCount(){
        this.setUp();

        Territory attackingTerritory = _mapService.findTerritory(0);
        attackingTerritory.getTerritoryArmy().addArmyUnits(ArmyUnitType.Infantry, 1);
        assertThrows(AttackError.class, () -> _attackState.validateAttack(0,1, 0),
                "Insufficient army count");

        resetArmies(attackingTerritory.getTerritoryArmy());

        attackingTerritory.getTerritoryArmy().addArmyUnits(ArmyUnitType.Chivalry, 1);
        assertThrows(AttackError.class, () -> _attackState.validateAttack(0,1, 0),
                "Insufficient army count");

        resetArmies(attackingTerritory.getTerritoryArmy());

        attackingTerritory.getTerritoryArmy().addArmyUnits(ArmyUnitType.Artillery, 1);
        assertThrows(AttackError.class, () -> _attackState.validateAttack(0,1, 0),
                "Insufficient army count");

        this.tearDown();
    }

    @Test
    void validateAttackWhenArmyIsWeaker(){
        this.setUp();

        Territory attackerTerritory = _mapService.findTerritory(0);
        Territory defenderTerritory = _mapService.findTerritory(1);

        attackerTerritory.getTerritoryArmy().addArmyUnits(ArmyUnitType.Infantry,4);
        defenderTerritory.getTerritoryArmy().addArmyUnits(ArmyUnitType.Infantry, 5);

        assertThrows(AttackError.class, () -> _attackState.validateAttack(0,1,0),
                "Weak army force");

        this.tearDown();
    }

    @Test
    void validateAttackWhenNotValidMatchUp(){
        this.setUp();



        this.tearDown();
    }
}