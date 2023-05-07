package app.domain.services.states;

import app.domain.models.army.Army;
import app.domain.models.army.ArmyUnitType;
import app.domain.models.game.map.Territory;
import app.domain.services.map.MapService;

public class DistributeState {
    private MapService _mapService;
    private Army initialArmy;

    public DistributeState() {
        _mapService = MapService.getInstance();
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

    public boolean isValidTerritorySelection(Territory territory) {
        return _mapService.unclaimedTerritorySubPhase(territory);
    }

    public boolean placeInfantryToTerritory(Territory territory, int playerId) {
        initialArmy.getArmyUnits(ArmyUnitType.Infantry, 1);
        if (!isValidTerritorySelection(territory)) {
            throw new Error("Invalid territory placement");
        }
        _mapService.placeArmyUnit(territory, ArmyUnitType.Infantry, playerId, playerId);
        return true;
    }
}
