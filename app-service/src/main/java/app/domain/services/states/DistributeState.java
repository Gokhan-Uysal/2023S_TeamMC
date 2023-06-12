package app.domain.services.states;

import app.domain.models.army.Army;
import app.domain.models.army.ArmyUnitType;
import app.domain.models.game.map.Territory;
import app.domain.services.GameManagerService;
import app.domain.services.map.MapService;

public class DistributeState {
    private MapService _mapService;
    private Army _initialArmy;

    public DistributeState() {
        _mapService = MapService.getInstance();
        _initialArmy = new Army();
    }

    public void fillArmy(int amount) {
        _initialArmy.addArmyUnits(ArmyUnitType.Infantry, amount);
    }

    public boolean isInitialUnitFinished() {
        if (_initialArmy.getArmyAmount(ArmyUnitType.Infantry) <= 0) {
            return true;
        }
        return false;
    }

    public void placeInfantryToTerritory(Territory territory, int playerId) {
        if (isInitialUnitFinished()) {
            GameManagerService.getInstance().handleNextState();
            throw new Error("Initial armies placed");
        }
        _initialArmy.getArmyUnits(ArmyUnitType.Infantry, 1);
        /*if (!_mapService.unclaimedTerritoryExist()) {
            if (territory.getOwnerId() != playerId) {
                throw new Error("Not your territory!");
            }
            _mapService.placeArmyUnit(territory, ArmyUnitType.Infantry, 1, playerId);
            return;
        }

        if (!_mapService.unclaimedTerritorySubPhase(territory)) {
            throw new Error("Invalid territory selection");
        }*/

        _mapService.placeArmyUnit(territory, ArmyUnitType.Infantry, 1, playerId);
    }
}
