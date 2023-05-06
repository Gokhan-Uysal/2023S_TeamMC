package app.domain.models.game.map;

import java.util.ArrayList;

public class Continent {
    private String name;
    private ArrayList<Integer> territoryList = new ArrayList<>();

    public Continent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Integer> getTerritoryList() {
        return territoryList;
    }

    public void addTerritory(int territoryId) {
        this.territoryList.add(territoryId);
    }
}
