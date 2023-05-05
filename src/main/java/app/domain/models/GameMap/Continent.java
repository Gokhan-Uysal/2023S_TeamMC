package app.domain.models.GameMap;

import java.util.ArrayList;

public class Continent {
    private static int classContinentId = 0;

    private int continentId;
    private String name;
    private ArrayList<Integer> territoryList = new ArrayList<>();

    public Continent(String name) {
        this.name = name;

        this.continentId = ++classContinentId;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Integer> getTerritoryList() {
        return territoryList;
    }

    public void addTerritory(int territoryId){
        this.territoryList.add(territoryId);
    }
}
