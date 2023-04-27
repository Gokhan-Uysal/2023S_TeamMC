package app.domain.services.Map;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import app.domain.models.GameMap.Continent;
import app.domain.models.GameMap.Territory;
import app.domain.models.GameMap.TerritoryPosition;
import app.domain.services.base.JsonService;

public class MapReadService extends JsonService {
    private Map<Continent, List<Territory>> _gameMapData;

    public MapReadService(String filePath) {
        super(filePath);
        this._gameMapData = new HashMap<>(7);
    }

    public void buildGameMapData() {
        try {
            JSONArray modelList = super.readJSON();
            for (Object model : modelList) {
                Continent continent = parseContinentObject((JSONObject) model);

                List<Territory> terriotiList = parseTerritoriesObject((JSONObject) model);

                _gameMapData.put(continent, terriotiList);
            }
        } catch (IOException e) {
            System.err.println("Cannot load from json file");
        } catch (ParseException e) {
            System.err.println("Cannot parse json to custom object");
        }
        System.out.println(_gameMapData);
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

        return new Territory(territoryName, imageName, territoryPosition);
    }

    private TerritoryPosition parseTerritoryPositionObject(JSONObject jsonObject) {
        JSONObject positionObject = (JSONObject) jsonObject.get("position");
        Number xPos = (Number) positionObject.get("x");
        Number yPos = (Number) positionObject.get("y");
        return new TerritoryPosition(xPos.intValue(), yPos.intValue());
    }
}
