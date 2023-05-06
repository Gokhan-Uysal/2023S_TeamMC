package app.domain.services.states;

import app.domain.models.army.Army;
import app.domain.models.army.ArmyUnitType;
import app.domain.models.game.map.Territory;
import app.domain.services.map.MapService;

public class DistributeState {
    private MapService _mapService;
    private Army initialArmy;

    public DistributeState() {
        _mapService = new MapService();
        initialArmy = new Army();
    }

    public void fillArmy(int amount) {
        initialArmy.addArmyUnits(ArmyUnitType.Infantry, amount);
    }

    public boolean isInitialUnitFinished() {
        if (initialArmy.getArmyAmount(ArmyUnitType.Infantry) == 0) {
            return true;
        }
        return false;
    }

    private Territory getTerritoryById(int territoryId) throws IndexOutOfBoundsException {
        Territory territory = _mapService.findTerritory(territoryId);
        return territory;
    }

    public boolean isValidTerritorySelection(Territory territory) {
        return _mapService.unclaimedTerritorySubPhase(territory);
    }

    public boolean placeInfantryToTerritory(int terrtoryId, int playerId) {
        initialArmy.getArmyUnits(ArmyUnitType.Infantry, 1);
        Territory territory = getTerritoryById(terrtoryId);
        if (!isValidTerritorySelection(territory)) {
            throw new Error("Invalid territory placement");
        }
        _mapService.placeArmyUnit(territory, ArmyUnitType.Infantry, terrtoryId, playerId);
        return true;
    }
}
