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
            Path tempPath = Files.createTempFile("tempJsonFile", ".json");
            File tempFile = tempPath.toFile();

            FileWriter writer = new FileWriter(tempFile);
            writer.write(jsonArray.toJSONString());
            writer.close();

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
        JSONArray emptyArray = new JSONArray();
        File tempFile = createTempJsonFile(emptyArray);
        tempFiles.add(tempFile);
        mapReadService = new MapReadService(tempFile.getAbsolutePath());

        mapReadService.buildGameMapData();

        assertTrue(mapReadService.getGameMapData().isEmpty());
    }

    @Test
    public void testBuildGameMapData_oneEntry() {
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

        mapReadService.buildGameMapData();

        assertEquals(1, mapReadService.getGameMapData().size());
    }

    @Test
    public void testBuildGameMapData_throwsIOException() {
        String invalidPath = "src/main/java/app/__resource__/non_existent.json";
        mapReadService = new MapReadService(invalidPath);

        mapReadService.buildGameMapData();

        assertTrue(mapReadService.getGameMapData().isEmpty());
    }

    @Test
    public void testBuildGameMapData_multipleEntries() {
        JSONArray modelList = new JSONArray();

        JSONObject continentObject1 = createContinentObject("TestContinent1", "TestTerritory1", "TestImageName1", 0, 0);
        JSONObject continentObject2 = createContinentObject("TestContinent2", "TestTerritory2", "TestImageName2", 1, 1);

        modelList.add(continentObject1);
        modelList.add(continentObject2);

        File tempFile = createTempJsonFile(modelList);
        tempFiles.add(tempFile);
        mapReadService = new MapReadService(tempFile.getAbsolutePath());

        mapReadService.buildGameMapData();

        assertEquals(2, mapReadService.getGameMapData().size());
    }

    @Test
    public void testBuildGameMapData_multipleContinents() throws IOException {
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

        mapReadService.buildGameMapData();

        assertEquals(2, mapReadService.getGameMapData().size());
    }


}
