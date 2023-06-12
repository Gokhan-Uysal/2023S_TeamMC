package app.domain.models.game.map;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Continent {
    @JsonProperty("continent")
    private String name;
    @JsonProperty("countries")
    private ArrayList<Territory> territoryList = new ArrayList<>();

    public Continent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Territory> getTerritoryList() {
        return territoryList;
    }

    public void addTerritory(Territory territory) {
        this.territoryList.add(territory);
    }
}
