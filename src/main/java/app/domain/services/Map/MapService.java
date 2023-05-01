package app.domain.services.Map;

import java.util.List;

import app.common.AppConfig;
import app.domain.models.GameMap.Territory;

public class MapService {
	private MapReadService _mapReadService = new MapReadService(AppConfig.basePath + "/resource/map.json");
	private MapGraphService _mapGraphService = new MapGraphService();

	public MapService() {

	}

	public void loadGameMapDataToGraph() {
		_mapReadService.buildGameMapData();
		_mapGraphService.addVerticies(getTerritoryListFromReadService());
	}

	public List<Territory> getTerritoryListFromGraph() {
		return _mapGraphService.getVerticies();
	}

	private List<Territory> getTerritoryListFromReadService() {
		return _mapReadService.getGameMapTerritories();
	}
}
