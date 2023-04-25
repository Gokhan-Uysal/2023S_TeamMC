package app.domain.services;

import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;

import app.domain.models.GameMap.Territory;

public class MapFactory {
    private List<Territory> _territoryList = new ArrayList<>();

    public List<Territory> getTerritoryList() {
        return this._territoryList;
    }

    public MapFactory() {
        // Australia
        _territoryList.add(new Territory("Eastern Australia"));
        _territoryList.add(new Territory("Indonesia"));
        _territoryList.add(new Territory("New Guinea"));
        _territoryList.add(new Territory("Western Australia"));

        // Asia
        _territoryList.add(new Territory("Afghanistan"));
        _territoryList.add(new Territory("China"));
        _territoryList.add(new Territory("India"));
        _territoryList.add(new Territory("Irkutsk"));
        _territoryList.add(new Territory("Japan"));
        _territoryList.add(new Territory("Kamchatka"));
        _territoryList.add(new Territory("Middle East"));
        _territoryList.add(new Territory("Mongolia"));
        _territoryList.add(new Territory("Siam (Southeast Asia)"));
        _territoryList.add(new Territory("Siberia"));
        _territoryList.add(new Territory("Ural"));
        _territoryList.add(new Territory("Yakutsk"));

        // Africa
        _territoryList.add(new Territory("Congo (Central Africa)"));
        _territoryList.add(new Territory("East Africa"));
        _territoryList.add(new Territory("Egypt"));
        _territoryList.add(new Territory("Madagascar"));
        _territoryList.add(new Territory("North Africa"));
        _territoryList.add(new Territory("South Africa"));

        // Europe
        _territoryList.add(new Territory("Great Britain (Great Britain & Ireland)"));
        _territoryList.add(new Territory("Iceland"));
        _territoryList.add(new Territory("Northern Europe"));
        _territoryList.add(new Territory("Scandinavia"));
        _territoryList.add(new Territory("Southern Europe"));
        _territoryList.add(new Territory("Ukraine (Eastern Europe, Russia)"));
        _territoryList.add(new Territory("Western Europe"));

        // North America
        _territoryList.add(new Territory("Alaska"));
        _territoryList.add(new Territory("Alberta (Western Canada)"));
        _territoryList.add(new Territory("Central America"));
        _territoryList.add(new Territory("Eastern United States"));
        _territoryList.add(new Territory("Greenland"));
        _territoryList.add(new Territory("Northwest Territory"));
        _territoryList.add(new Territory("Ontario (Central Canada)"));
        _territoryList.add(new Territory("Quebec (Eastern Canada)"));
        _territoryList.add(new Territory("Western United States"));

        // South America
        _territoryList.add(new Territory("Argentina"));
        _territoryList.add(new Territory("Brazil"));
        _territoryList.add(new Territory("Peru"));
        _territoryList.add(new Territory("Venezuela"));
    }

    public Territory initilizeTerritory(String territoryName) {
        return new Territory(territoryName);
    }

    public Territory initilizeTerritory(String name, Shape shape) {
        return new Territory(name, shape);
    }
}
