package app.domain.services.map;

import java.util.ArrayList;
import java.util.List;

import app.common.AppConfig;
import app.domain.models.army.Army;
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

	public Territory findTerritory(int territoryId) {

		for (Territory t : this.getTerritoryListFromGraph()) {
			if (t.getTerritoryId() == territoryId) {
				return t;
			}
		}
		return null;
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

	private List<Territory> getAttackableTerritories(int selectedTerritoryId) {
		ArrayList<Territory> attackableTerritoryList = new ArrayList<>();
		Territory selectedTerritory = this.findTerritory(selectedTerritoryId);

		for (String s : selectedTerritory.getAdjList()) {

			Territory adjacentTerritory = this.findTerritory(s);
			if (adjacentTerritory.getOwnerId() != selectedTerritory.getOwnerId()) {
				if (this.territoryArmyCondition(selectedTerritory, adjacentTerritory)) {
					attackableTerritoryList.add(adjacentTerritory);
				}
			}
		}

		return attackableTerritoryList;
	}

	public List<Territory> playerCanAttackFrom(int playerId) {
		ArrayList<Territory> attackableFrom = new ArrayList<>();

		for (Territory t : this.getTerritoryListFromGraph()) {
			if (t.getOwnerId() == playerId && t.getTerritoryArmy().getTotalArmyAmount() >= 2) {
				attackableFrom.add(t);
			}
		}

		return attackableFrom;
	}

	public void openAllTerritories() {
		_mapGraphService.openAllTerritories();
	}

	public void removeClosedTerritories() {
		_mapGraphService.removeClosedTerritories();
	}

	private boolean territoryArmyCondition(Territory attackingTerritory, Territory attackedTerritory) {
		if (attackedTerritory.getTerritoryArmy().getArmyAmount(ArmyUnitType.Artillery) > 0) {
			return attackingTerritory.getTerritoryArmy().getArmyAmount(ArmyUnitType.Artillery) > 0 &&
					attackingTerritory.getTerritoryArmy().getTotalArmyValue() > attackedTerritory.getTerritoryArmy()
							.getTotalArmyValue();
		} else if (attackedTerritory.getTerritoryArmy().getArmyAmount(ArmyUnitType.Chivalry) > 0) {
			return attackingTerritory.getTerritoryArmy().getArmyAmount(ArmyUnitType.Chivalry) > 0 &&
					attackingTerritory.getTerritoryArmy().getTotalArmyValue() > attackedTerritory.getTerritoryArmy()
							.getTotalArmyValue();
		} else if (attackedTerritory.getTerritoryArmy().getArmyAmount(ArmyUnitType.Infantry) > 0) {
			return attackingTerritory.getTerritoryArmy().getArmyAmount(ArmyUnitType.Infantry) > 0 &&
					attackingTerritory.getTerritoryArmy().getTotalArmyValue() > attackedTerritory.getTerritoryArmy()
							.getTotalArmyValue();
		}
		return false;
	}

	public boolean territoryFortifyCondition(int infantryAmount, int cavalryAmount, int artilleryAmount,
			int territoryId) {
		Army foundTerritoryArmy = this.findTerritory(territoryId).getTerritoryArmy();
		return ((infantryAmount + cavalryAmount + artilleryAmount) < foundTerritoryArmy.getTotalArmyAmount() &&
				(infantryAmount < foundTerritoryArmy.getArmyAmount(ArmyUnitType.Infantry) &&
						cavalryAmount < foundTerritoryArmy.getArmyAmount(ArmyUnitType.Chivalry) &&
						artilleryAmount < foundTerritoryArmy.getArmyAmount(ArmyUnitType.Artillery)));
	}

	public void placeArmyUnit(int territoryId, ArmyUnitType type, int amount) {
		Territory t = this.findTerritory(territoryId);
		t.getTerritoryArmy().addArmyUnits(type, amount);
	}

	public boolean unclaimedTerritoryExist() {
		for (Territory t : this.getTerritoryListFromGraph()) {
			if (t.getOwnerId() == -1) {
				return true;
			}
		}
		return false;
	}

	public boolean unclaimedTerritorySubPhase(int territoryId) {
		Territory t = this.findTerritory(territoryId);
		return this.unclaimedTerritoryExist() && t.getOwnerId() == -1;
	}

}
