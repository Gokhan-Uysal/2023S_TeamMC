package app.domain.services.map;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import app.common.AppConfig;
import app.domain.models.army.ArmyUnitType;
import app.domain.models.game.map.Continent;
import app.domain.models.game.map.Territory;

public class MapService {
	private MapReadService _mapReadService;
	private MapGraphService _mapGraphService;

	public MapService() {
		this._mapReadService = new MapReadService(AppConfig.basePath + "/__resource__/map.json");
		this._mapGraphService = new MapGraphService();
	}

	public void loadGameMapDataToGraph() {
		_mapReadService.buildGameMapData();
		List<Territory> territoryList = getTerritoryListFromReadService();
		_mapGraphService.addVerticies(territoryList);
		_mapGraphService.addEdges(territoryList);
	}

	public List<Territory> getTerritoryListFromGraph() {
		return _mapGraphService.getVerticies();
	}

	public List<Territory> getShortestPath(Territory source, Territory destination) {
		return _mapGraphService.shortestPath(source, destination);
	}

	public boolean isValidBuildSelection() {
		return _mapGraphService.validateMap();
	}

	private List<Territory> getTerritoryListFromReadService() {
		return _mapReadService.getGameMapTerritories();
	}

	public Territory findTerritory(String territoryName) {

		for (Territory t : this.getTerritoryListFromGraph()) {
			if (t.getName().equals(territoryName)) {
				return t;
			}
		}
		return null;
	}

	public Continent findContinent(String continentName) {
		for (Continent c : _mapReadService.getGameMapData().keySet()) {
			if (c.getName().equals(continentName)) {
				return c;
			}
		}
		return null;
	}

	public List<Territory> getTerritoriesOfContinent(String continentName) {
		Continent foundContinent = this.findContinent(continentName);
		return this._mapReadService.getGameMapData().get(foundContinent);
	}

	public void openAllTerritories() {
		_mapGraphService.openAllTerritories();
	}

	public void removeClosedTerritories() {
		_mapGraphService.removeClosedTerritories();
	}

	public List<Territory> getTerriotryListOfPlayer(int playerId) {
		List<Territory> territories = getTerritoryListFromGraph();
		return territories.stream()
				.filter((territory) -> territory.getOwnerId() == playerId)
				.collect(Collectors.toList());
	}

	// public boolean isValidFortify(int infantryAmount, int cavalryAmount, int
	// artilleryAmount,
	// int territoryId) {
	// Army foundTerritoryArmy = this.findTerritory(territoryId).getTerritoryArmy();
	// return ((infantryAmount + cavalryAmount + artilleryAmount) <
	// foundTerritoryArmy.getTotalArmyAmount() &&
	// (infantryAmount < foundTerritoryArmy.getArmyAmount(ArmyUnitType.Infantry) &&
	// cavalryAmount < foundTerritoryArmy.getArmyAmount(ArmyUnitType.Chivalry) &&
	// artilleryAmount < foundTerritoryArmy.getArmyAmount(ArmyUnitType.Artillery)));
	// }

	public void placeArmyUnit(Territory territory, ArmyUnitType type, int amount, int playerId) {
		territory.getTerritoryArmy().addArmyUnits(type, amount);
		territory.setOwnerId(playerId);
	}

	public boolean unclaimedTerritoryExist() {
		for (Territory territory : this.getTerritoryListFromGraph()) {
			if (territory.getOwnerId() == -1) {
				return true;
			}
		}
		return false;
	}

	public boolean unclaimedTerritorySubPhase(Territory territory) {
		return this.unclaimedTerritoryExist() && territory.getOwnerId() == -1;
	}
}
