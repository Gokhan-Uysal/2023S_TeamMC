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
import app.domain.services.base.JsonService;

public class MapReadService extends JsonService {
    private Map<Continent, List<Territory>> _gameMapData;

    public MapReadService(String filePath) {
        super(filePath);
        this._gameMapData = new HashMap<>(7);
    }

    public void buildGameMapData() throws IOException, ParseException {
        JSONArray modelList = super.readJSON();
        for (Object model : modelList) {
            Continent continent = parseContinentObject((JSONObject) model);
            List<Territory> terriotiList = parseTerritoriesObject((JSONObject) model);

            _gameMapData.put(continent, terriotiList);
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

    private List<Territory> parseTerritoriesObject(JSONObject jsonObject) {
        List<String> territories = (List<String>) jsonObject.get("countries");
        List<Territory> territoryList = new ArrayList<>();
        territories.forEach((territory) -> territoryList.add(new Territory(territory)));

        return territoryList;
    }
}
