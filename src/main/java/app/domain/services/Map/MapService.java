package app.domain.services.Map;

import java.util.ArrayList;
import java.util.List;

import app.common.AppConfig;
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

	private Territory findTerritory(String territoryName){
		Territory foundTerritory = null;

		for (Territory t: this.getTerritoryListFromReadService()){
			if (t.getName().equals(territoryName)){
				foundTerritory = t;
				break;
			}
		}

		return foundTerritory;
	}

	private List<Territory> getAttackableTerritories(String selectedTerritoryName){
		ArrayList<Territory> attackableTerritoryList = new ArrayList<>();
		Territory selectedTerritory = this.findTerritory(selectedTerritoryName);

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

	private List<Territory> playerCanAttackFrom(int playerId){
		ArrayList<Territory> attackableFrom = new ArrayList<>();

		for (Territory t: this.getTerritoryListFromReadService()){
			if (t.getOwnerId() == playerId && t.getArmyAmount() >= 2){
				attackableFrom.add(t);
			}
		}

		return attackableFrom;
	}

	private boolean territoryArmyCondition(Territory attackingTerritory, Territory attackedTerritory){
		if (attackedTerritory.getArtilleryAmount() > 0){
			return attackingTerritory.getArtilleryAmount() > 0 && attackingTerritory.getArmyValue() > attackedTerritory.getArmyValue();
		}
		else if (attackedTerritory.getCavalryAmount() > 0){
			return attackingTerritory.getCavalryAmount() > 0 && attackingTerritory.getArmyValue() > attackedTerritory.getArmyValue();
		}
		else if (attackedTerritory.getInfantryAmount() > 0){
			return attackingTerritory.getInfantryAmount() > 0 && attackingTerritory.getArmyValue() > attackedTerritory.getArmyValue();
		}
		return false;
	}


}
