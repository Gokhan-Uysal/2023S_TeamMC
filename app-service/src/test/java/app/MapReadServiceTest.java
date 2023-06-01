package app;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import app.domain.services.map.MapReadService;
import app.domain.models.game.map.Continent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MapReadServiceTest {

    private MapReadService mapReadService;
    private List<File> tempFiles;

    @Before
    public void setUp() {
        tempFiles = new ArrayList<>();
    }

    @After
    public void tearDown() {
        for (File file : tempFiles) {
            file.delete();
        }
    }

    private File createTempJsonFile(JSONArray jsonArray) {
        try {
            // Create a temporary file
            Path tempPath = Files.createTempFile("tempJsonFile", ".json");
            File tempFile = tempPath.toFile();

            // Write the JSONArray to the file
            FileWriter writer = new FileWriter(tempFile);
            writer.write(jsonArray.toJSONString());
            writer.close();

            // Return the temporary file
            return tempFile;
        } catch (IOException e) {
            throw new RuntimeException("Unable to create temp file for testing", e);
        }
    }

    private JSONObject createContinentObject(String continentName, String territoryName, String imageName, int x, int y) {
        JSONObject continentObject = new JSONObject();
        continentObject.put("continent", continentName);

        JSONObject territoryObject = new JSONObject();
        territoryObject.put("name", territoryName);
        territoryObject.put("imageName", imageName);
        territoryObject.put("neighbors", new JSONArray());

        JSONObject positionObject = new JSONObject();
        positionObject.put("x", x);
        positionObject.put("y", y);
        territoryObject.put("position", positionObject);

        JSONArray territoriesArray = new JSONArray();
        territoriesArray.add(territoryObject);

        continentObject.put("countries", territoriesArray);

        return continentObject;
    }
    @Test
    public void testBuildGameMapData_emptyData() {
        // Arrange
        JSONArray emptyArray = new JSONArray();
        File tempFile = createTempJsonFile(emptyArray);
        tempFiles.add(tempFile);
        mapReadService = new MapReadService(tempFile.getAbsolutePath());

        // Act
        mapReadService.buildGameMapData();

        // Assert
        assertTrue(mapReadService.getGameMapData().isEmpty());
    }

    @Test
    public void testBuildGameMapData_oneEntry() {
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

        File tempFile = createTempJsonFile(modelList);
        tempFiles.add(tempFile);
        mapReadService = new MapReadService(tempFile.getAbsolutePath());

        // Act
        mapReadService.buildGameMapData();

        // Assert
        assertEquals(1, mapReadService.getGameMapData().size());
    }

    // Include the rest of your tests here, following the same pattern
    @Test
    public void testBuildGameMapData_throwsIOException() {
        // Arrange
        String invalidPath = "src/main/java/app/__resource__/non_existent.json";
        mapReadService = new MapReadService(invalidPath);

        // Act
        mapReadService.buildGameMapData();

        // Assert
        assertTrue(mapReadService.getGameMapData().isEmpty());
    }

    @Test
    public void testBuildGameMapData_multipleEntries() {
        // Arrange
        JSONArray modelList = new JSONArray();

        JSONObject continentObject1 = createContinentObject("TestContinent1", "TestTerritory1", "TestImageName1", 0, 0);
        JSONObject continentObject2 = createContinentObject("TestContinent2", "TestTerritory2", "TestImageName2", 1, 1);

        modelList.add(continentObject1);
        modelList.add(continentObject2);

        File tempFile = createTempJsonFile(modelList);
        tempFiles.add(tempFile);
        mapReadService = new MapReadService(tempFile.getAbsolutePath());

        // Act
        mapReadService.buildGameMapData();

        // Assert
        assertEquals(2, mapReadService.getGameMapData().size());
    }

    @Test
    public void testBuildGameMapData_noTerritoriesInContinent() {
        // Arrange
        JSONObject continentObject = new JSONObject();
        continentObject.put("continent", "TestContinent");
        continentObject.put("countries", new JSONArray());

        JSONArray modelList = new JSONArray();
        modelList.add(continentObject);

        File tempFile = createTempJsonFile(modelList);
        tempFiles.add(tempFile);
        mapReadService = new MapReadService(tempFile.getAbsolutePath());

        // Act
        mapReadService.buildGameMapData();

        // Assert
        assertEquals(1, mapReadService.getGameMapData().size());
        assertTrue(mapReadService.getGameMapData().get(new Continent("TestContinent")).isEmpty());
    }



    @Test
    public void testBuildGameMapData_multipleContinents() throws IOException {
        // Arrange
        JSONObject firstContinentObject = new JSONObject();
        firstContinentObject.put("continent", "TestContinent1");
        firstContinentObject.put("countries", new JSONArray());

        JSONObject secondContinentObject = new JSONObject();
        secondContinentObject.put("continent", "TestContinent2");
        secondContinentObject.put("countries", new JSONArray());

        JSONArray modelList = new JSONArray();
        modelList.add(firstContinentObject);
        modelList.add(secondContinentObject);

        File tempFile = createTempJsonFile(modelList);
        mapReadService = new MapReadService(tempFile.getPath());

        // Act
        mapReadService.buildGameMapData();

        // Assert
        assertEquals(2, mapReadService.getGameMapData().size());
    }


}
