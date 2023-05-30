package app;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import app.domain.services.map.MapReadService;
import app.domain.services.base.JsonService;
import app.domain.models.game.map.Continent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MapReadServiceTest {

    private MapReadService mapReadService;
    private JsonService _jsonService;
    private static final String filePath = "/main/java/app/__resource__/map.json";

    @Before
    public void setUp() {
        _jsonService = mock(JsonService.class);
        mapReadService = new MapReadService(filePath);
          // Manually inject the mock
    }

    @Test
    public void testBuildGameMapData_emptyData() throws IOException, ParseException {
        // Arrange
        when(_jsonService.readJSON()).thenReturn(new JSONArray());

        // Act
        mapReadService.buildGameMapData();

        // Assert
        assertTrue(mapReadService.getGameMapData().isEmpty());
    }

    @Test
    public void testBuildGameMapData_oneEntry() throws IOException, ParseException {
        // Arrange
        JSONObject continentObject = new JSONObject();
        continentObject.put("continent", "TestContinent");

        JSONObject territoryObject = new JSONObject();
        territoryObject.put("name", "TestTerritory");
        territoryObject.put("imageName", "TestImageName");
        territoryObject.put("neighbors", new JSONArray());

        JSONObject positionObject = new JSONObject();
        positionObject.put("x", 0);
        positionObject.put("y", 0);
        territoryObject.put("position", positionObject);

        JSONArray territoriesArray = new JSONArray();
        territoriesArray.add(territoryObject);

        continentObject.put("countries", territoriesArray);

        JSONArray modelList = new JSONArray();
        modelList.add(continentObject);

        when(_jsonService.readJSON()).thenReturn(modelList);

        // Act
        mapReadService.buildGameMapData();

        // Assert
        assertEquals(1, mapReadService.getGameMapData().size());
    }

    @Test
    public void testBuildGameMapData_throwsIOException() throws IOException, ParseException {
        // Arrange
        when(_jsonService.readJSON()).thenThrow(new IOException());

        // Act
        mapReadService.buildGameMapData();

        // Assert
        assertTrue(mapReadService.getGameMapData().isEmpty());
        // Consider using a mock Logger to verify logging behavior
    }

    @Test
    public void testBuildGameMapData_throwsParseException() throws IOException, ParseException {
        // Arrange
        when(_jsonService.readJSON()).thenThrow(new ParseException(ParseException.ERROR_UNEXPECTED_EXCEPTION));

        // Act
        mapReadService.buildGameMapData();

        // Assert
        assertTrue(mapReadService.getGameMapData().isEmpty());
        // Consider using a mock Logger to verify logging behavior
    }
    @Test
    public void testBuildGameMapData_multipleEntries() throws IOException, ParseException {
        // Arrange
        JSONObject continentObject1 = new JSONObject();
        continentObject1.put("continent", "TestContinent1");

        JSONObject territoryObject1 = new JSONObject();
        territoryObject1.put("name", "TestTerritory1");
        territoryObject1.put("imageName", "TestImageName1");
        territoryObject1.put("neighbors", new JSONArray());

        JSONObject positionObject1 = new JSONObject();
        positionObject1.put("x", 0);
        positionObject1.put("y", 0);
        territoryObject1.put("position", positionObject1);

        JSONArray territoriesArray1 = new JSONArray();
        territoriesArray1.add(territoryObject1);

        continentObject1.put("countries", territoriesArray1);

        JSONObject continentObject2 = new JSONObject();
        continentObject2.put("continent", "TestContinent2");

        JSONObject territoryObject2 = new JSONObject();
        territoryObject2.put("name", "TestTerritory2");
        territoryObject2.put("imageName", "TestImageName2");
        territoryObject2.put("neighbors", new JSONArray());

        JSONObject positionObject2 = new JSONObject();
        positionObject2.put("x", 1);
        positionObject2.put("y", 1);
        territoryObject2.put("position", positionObject2);

        JSONArray territoriesArray2 = new JSONArray();
        territoriesArray2.add(territoryObject2);

        continentObject2.put("countries", territoriesArray2);

        JSONArray modelList = new JSONArray();
        modelList.add(continentObject1);
        modelList.add(continentObject2);

        when(_jsonService.readJSON()).thenReturn(modelList);

        // Act
        mapReadService.buildGameMapData();

        // Assert
        assertEquals(2, mapReadService.getGameMapData().size());
    }

    @Test
    public void testBuildGameMapData_noTerritoriesInContinent() throws IOException, ParseException {
        // Arrange
        JSONObject continentObject = new JSONObject();
        continentObject.put("continent", "TestContinent");

        continentObject.put("countries", new JSONArray());

        JSONArray modelList = new JSONArray();
        modelList.add(continentObject);

        when(_jsonService.readJSON()).thenReturn(modelList);

        // Act
        mapReadService.buildGameMapData();

        // Assert
        assertEquals(1, mapReadService.getGameMapData().size());
        assertTrue(mapReadService.getGameMapData().get(new Continent("TestContinent")).isEmpty());
    }




}

