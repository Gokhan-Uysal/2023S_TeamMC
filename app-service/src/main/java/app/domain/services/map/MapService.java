package app.domain.services.map;

import java.util.*;
import java.util.stream.Collectors;

import app.common.AppConfig;
import app.common.errors.DbException;
import app.domain.models.army.ArmyUnitType;
import app.domain.models.game.map.Continent;
import app.domain.models.game.map.Territory;
import app.domain.repositories.MapRepository;

public class MapService {
	private static MapService _instance;
	private MapRepository _mapRepository;
	private MapReadService _mapReadService;
	private MapGraphService _mapGraphService;

	private MapService() {
		this._mapRepository = new MapRepository();
		this._mapReadService = new MapReadService(AppConfig.basePath + "/__resource__/map.json");
		this._mapGraphService = new MapGraphService();
	}

	public static MapService getInstance() {
		if (_instance == null) {
			_instance = new MapService();
		}
		return _instance;
	}

	public void setMapGraphService(MapGraphService mapGraphService){
		this._mapGraphService = mapGraphService;
	}

	public void loadGameMapDataToGraph() throws DbException {
		List<Territory> territoryList = _mapRepository.buildGameMapData();
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

	public Set<Continent> getContinents() {
		return _mapReadService.getGameMapContinents();
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

	public Territory findTerritory(int territoryId) {
		for (Territory territory : this.getTerritoryListFromGraph()) {
			if (territory.get_territoryId() == territoryId) {
				return territory;
			}
		}
		return null;
	}

	public ArrayList<Territory> findTerritories(ArrayList<Integer> territoryIds) {

		ArrayList<Territory> territories = new ArrayList<>();
		for (Integer id : territoryIds) {
			territories.add(findTerritory(id));
		}

		return territories;
	}

	public void changeOwnerOfTerritory(int playerId, int territoryId) {
		Territory t = findTerritory(territoryId);
		t.setOwnerId(playerId);
	}

	public Set<Territory> getAdjacentTerritories(Territory territory) {
		return _mapGraphService.getEdges(territory);
	}
}
