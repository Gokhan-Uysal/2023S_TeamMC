package app.domain.services.Map;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import app.common.Logger;
import app.domain.models.game.map.*;
import app.domain.services.base.JsonService;

public class MapReadService {
    private JsonService _jsonService;
    private Map<Continent, List<Territory>> _gameMapData;

    public MapReadService(String filePath) {
        this._jsonService = new JsonService(filePath);
        this._gameMapData = new HashMap<>(7);
    }

    public void buildGameMapData() {
        try {
            JSONArray modelList = _jsonService.readJSON();
            for (Object model : modelList) {
                Continent continent = parseContinentObject((JSONObject) model);

                List<Territory> terriotiList = parseTerritoriesObject((JSONObject) model);

                _gameMapData.put(continent, terriotiList);
            }
        } catch (IOException e) {
            Logger.error(e);
        } catch (ParseException e) {
            Logger.error(e);
        }
    }

    public Map<Continent, List<Territory>> getGameMapData() {
        return _gameMapData;
    }

    public Set<Continent> getGameMapContinents() {
        return _gameMapData.keySet();
    }

    public List<Territory> getGameMapTerritories() {
        List<Territory> territoryList = new ArrayList<>();
        _gameMapData.values().forEach((territories) -> territoryList.addAll(territories));

        return territoryList;
    }

    private Continent parseContinentObject(JSONObject jsonObject) {
        String continentName = (String) jsonObject.get("continent");
        return new Continent(continentName);
    }

    private List<Territory> parseTerritoriesObject(JSONObject jsonObject) throws IOException {
        JSONArray territoryModels = (JSONArray) jsonObject.get("countries");
        List<Territory> territoryList = new ArrayList<>();

        for (Object model : territoryModels) {
            Territory territory = parseTerritoryObject((JSONObject) model);
            territoryList.add(territory);
        }
        return territoryList;
    }

    private Territory parseTerritoryObject(JSONObject jsonObject) throws IOException {
        String territoryName = (String) jsonObject.get("name");
        String imageName = (String) jsonObject.get("imageName");
        TerritoryPosition territoryPosition = parseTerritoryPositionObject(jsonObject);
        Set<String> adjList = parseAdjTerritoryList(jsonObject);
        return new Territory(territoryName, imageName, territoryPosition, adjList);
    }

    private TerritoryPosition parseTerritoryPositionObject(JSONObject jsonObject) {
        JSONObject positionObject = (JSONObject) jsonObject.get("position");
        Number xPos = (Number) positionObject.get("x");
        Number yPos = (Number) positionObject.get("y");
        return new TerritoryPosition(xPos.intValue(), yPos.intValue());
    }

    private Set<String> parseAdjTerritoryList(JSONObject jsonObject) {
        Set<String> adjList = new HashSet<String>();
        JSONArray territoryList = (JSONArray) jsonObject.get("neighbors");
        for (Object territory : territoryList) {
            String territoryName = (String) territory;
            adjList.add(territoryName);
        }

        return adjList;
    }
}
