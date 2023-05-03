package app.domain.services.Map;

import java.util.ArrayList;
import java.util.List;

import app.common.AppConfig;
import app.domain.models.ArmyUnit.ArmyUnitType;
import app.domain.models.GameMap.Continent;
import app.domain.models.GameMap.Territory;

public class MapService {
	private MapReadService _mapReadService = new MapReadService(AppConfig.basePath + "/resource/map.json");
	private MapGraphService _mapGraphService = new MapGraphService();

	public MapService() {}

	public void loadGameMapDataToGraph() {
		_mapReadService.buildGameMapData();
		_mapGraphService.addVerticies(getTerritoryListFromReadService());
		_mapGraphService.addEdges(getTerritoryListFromReadService());
	}

	public List<Territory> getTerritoryListFromGraph() {
		return _mapGraphService.getVerticies();
	}

	private List<Territory> getTerritoryListFromReadService() {
		return _mapReadService.getGameMapTerritories();
	}

	public Territory findTerritory(int territoryId){

		for (Territory t: this.getTerritoryListFromReadService()){
			if (t.getTerritoryId() == territoryId){
				return t;
			}
		}
		return null;
	}

	public Territory findTerritory(String territoryName){

		for (Territory t: this.getTerritoryListFromReadService()){
			if (t.getName().equals(territoryName)){
				return t;
			}
		}
		return null;
	}

	public Continent findContinent(String continentName){
		for (Continent c: _mapReadService.getGameMapData().keySet()){
			if (c.getName().equals(continentName)){
				return c;
			}
		}
		return null;
	}

	public List<Territory> getTerritoriesOfContinent(String continentName){
		Continent foundContinent = this.findContinent(continentName);
		return this._mapReadService.getGameMapData().get(foundContinent);
	}

	public List<Territory> getAttackableTerritories(int selectedTerritoryId){
		ArrayList<Territory> attackableTerritoryList = new ArrayList<>();
		Territory selectedTerritory = this.findTerritory(selectedTerritoryId);

		for (String s: selectedTerritory.getAdjList()){

			Territory adjacentTerritory = this.findTerritory(s);
			if (adjacentTerritory.getOwnerId() != selectedTerritory.getOwnerId()){
				if (this.territoryArmyCondition(selectedTerritory, adjacentTerritory)){
					attackableTerritoryList.add(adjacentTerritory);
				}
			}
		}

		return attackableTerritoryList;
	}

	public List<Territory> playerCanAttackFrom(int playerId){
		ArrayList<Territory> attackableFrom = new ArrayList<>();

		for (Territory t: this.getTerritoryListFromReadService()){
			if (t.getOwnerId() == playerId && t.getTerritoryArmy().getTotalArmyAmount() >= 2){
				attackableFrom.add(t);
			}
		}

		return attackableFrom;
	}

	private boolean territoryArmyCondition(Territory attackingTerritory, Territory attackedTerritory){
		if (attackedTerritory.getTerritoryArmy().getArmyAmount(ArmyUnitType.Artillery) > 0){
			return attackingTerritory.getTerritoryArmy().getArmyAmount(ArmyUnitType.Artillery) > 0 &&
					attackingTerritory.getTerritoryArmy().getTotalArmyValue() > attackedTerritory.getTerritoryArmy().getTotalArmyValue();
		}
		else if (attackedTerritory.getTerritoryArmy().getArmyAmount(ArmyUnitType.Chivalry) > 0){
			return attackingTerritory.getTerritoryArmy().getArmyAmount(ArmyUnitType.Chivalry) > 0 &&
					attackingTerritory.getTerritoryArmy().getTotalArmyValue() > attackedTerritory.getTerritoryArmy().getTotalArmyValue();
		}
		else if (attackedTerritory.getTerritoryArmy().getArmyAmount(ArmyUnitType.Infantry) > 0){
			return attackingTerritory.getTerritoryArmy().getArmyAmount(ArmyUnitType.Infantry) > 0 &&
					attackingTerritory.getTerritoryArmy().getTotalArmyValue() > attackedTerritory.getTerritoryArmy().getTotalArmyValue();
		}
		return false;
	}


}
