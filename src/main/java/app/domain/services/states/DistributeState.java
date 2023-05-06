package app.domain.services.states;

import app.domain.models.army.ArmyUnitType;
import app.domain.models.game.map.Territory;
import app.domain.services.map.MapService;

public class DistributeState {
    private MapService _mapService;

    public DistributeState() {
        _mapService = new MapService();
    }

    private Territory getTerritoryById(int territoryId) throws IndexOutOfBoundsException {
        Territory territory = _mapService.findTerritory(territoryId);
        return territory;
    }

    public boolean isValidTerritorySelection(Territory territory) {
        return _mapService.unclaimedTerritorySubPhase(territory);
    }

    public void placeInfantryToTerritory(int terrtoryId, int playerId) {
        Territory territory = getTerritoryById(terrtoryId);
        if (!isValidTerritorySelection(territory)) {
            throw new Error("Invalid territory placement");
        }
        _mapService.placeArmyUnit(territory, ArmyUnitType.Infantry, terrtoryId, playerId);
    }
}
