package app;

import app.domain.models.army.ArmyUnitType;
import app.domain.models.game.map.Territory;
import app.domain.models.game.map.TerritoryPosition;
import app.domain.services.JsonSaveLoadService;
import app.domain.services.map.MapReadService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonSaveLoadServiceTest {
    private MapReadService _mapReadService;
    private List<Territory> initialList;
    private JsonSaveLoadService _jsonSaveLoadService;
    private List<Territory> newList;
    private String territoryRoot = "src/main/java/app/__resource__/territories.json";

    @BeforeEach
    void setUp() {
        //Arrange
        _mapReadService = new MapReadService("src/main/java/app/__resource__/map.json");
        _mapReadService.buildGameMapData();
        initialList = _mapReadService.getGameMapTerritories();
        _jsonSaveLoadService = new JsonSaveLoadService("src/main/java/app/__resource__/");
    }


    @AfterEach
    void tearDown() {

        //Arrange
        File territoryFile= new File(territoryRoot);
        //Act
        if (territoryFile.exists()) {
            System.out.println("territory.json deleted");
            territoryFile.delete();
        }

    }

    @Test
    void testMultipleSaving() {
        //Act
        _jsonSaveLoadService.saveMap(initialList);
        newList = _jsonSaveLoadService.loadMap();
        newList.get(0).setTerritoryPosition(new TerritoryPosition(999,999));
        _jsonSaveLoadService.saveMap(newList.subList(0,1));
        newList= _jsonSaveLoadService.loadMap();
        //Assert
        assertFalse(newList.get(0).getTerritoryPosition().getX() == initialList.get(0).getTerritoryPosition().getX(), "X values of territories are not same");
        assertFalse(newList.get(0).getTerritoryPosition().getY() == initialList.get(0).getTerritoryPosition().getY(), "Y values of territories are not same");

    }

    @Test
    void testSavingLoadingSimpleValues() {
        //Act
        _jsonSaveLoadService.saveMap(initialList);
        newList = _jsonSaveLoadService.loadMap();
        //Assert
        for (int i=0; i<initialList.size(); i++) {
            assertEquals(initialList.get(i).getName(),newList.get(i).getName());
            assertEquals(initialList.get(i).getImageName(),newList.get(i).getImageName());
            assertEquals(initialList.get(i).get_adjList(),newList.get(i).get_adjList());
            assertEquals(initialList.get(i).getArtilleryCount(),newList.get(i).getArtilleryCount());
            assertEquals(initialList.get(i).getChivalryCount(),newList.get(i).getChivalryCount());
            assertEquals(initialList.get(i).getInfantryCount(),newList.get(i).getInfantryCount());
            assertEquals(initialList.get(i).getIsOpen(),newList.get(i).getIsOpen());
        }
    }

    @Test
    void testSavingLoadingComplexValues() {
        //Act
        _jsonSaveLoadService.saveMap(initialList);
        newList = _jsonSaveLoadService.loadMap();
        //Assert
        for (int i=0; i<initialList.size(); i++) {
            assertEquals(initialList.get(i).getTerritoryPosition().getX(),newList.get(i).getTerritoryPosition().getX());
            assertEquals(initialList.get(i).getTerritoryPosition().getY(),newList.get(i).getTerritoryPosition().getY());
            assertEquals(initialList.get(i).getTerritoryArmy().getArmyAmount(ArmyUnitType.Artillery),
                    newList.get(i).getTerritoryArmy().getArmyAmount(ArmyUnitType.Artillery));
            assertEquals(initialList.get(i).getTerritoryArmy().getArmyAmount(ArmyUnitType.Infantry),
                    newList.get(i).getTerritoryArmy().getArmyAmount(ArmyUnitType.Infantry));
            assertEquals(initialList.get(i).getTerritoryArmy().getArmyAmount(ArmyUnitType.Chivalry),
                    newList.get(i).getTerritoryArmy().getArmyAmount(ArmyUnitType.Chivalry));
            assertEquals(initialList.get(i).getTerritoryArmy().getTotalArmyValue(),
                    newList.get(i).getTerritoryArmy().getTotalArmyValue());
            assertEquals(initialList.get(i).getTerritoryArmy().getTotalArmyAmount(),
                    newList.get(i).getTerritoryArmy().getTotalArmyAmount());
        }
    }

    @Test
    void testSingleElementSave() {
        //Act
        _jsonSaveLoadService.saveMap(initialList.subList(0,1));
        newList = _jsonSaveLoadService.loadMap();
        //Assert
        assertEquals(initialList.get(0).getName(),newList.get(0).getName());
        assertEquals(initialList.get(0).getImageName(),newList.get(0).getImageName());
    }
    @Test
    void testNoElementSaveLoad() {
        //Arrange
        List<Territory> emptyList = Collections.<Territory>emptyList();
        //Act
        _jsonSaveLoadService.saveMap(emptyList);
        newList = _jsonSaveLoadService.loadMap();
        //Assert
        assertTrue(newList.isEmpty());
    }


}