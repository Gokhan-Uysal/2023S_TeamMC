package app.domain.services.states;

import app.domain.models.army.Army;
import app.domain.models.army.ArmyUnitType;
import app.domain.models.game.map.Territory;
import app.domain.services.PlayerService;
import app.domain.services.map.MapService;

public class ReplaceState {

    private MapService _mapService = MapService.getInstance();
    private PlayerService _playerService = PlayerService.getInstance();

    public void replaceUnits(int infantryAmount, int cavalryAmount, int territoryId){
        replaceUnitsCondition(infantryAmount, cavalryAmount, territoryId);

        Army territoryArmy = _mapService.findTerritory(territoryId).getTerritoryArmy();
        if (cavalryAmount > 0){
            territoryArmy.tradeArmyUnits(ArmyUnitType.Infantry, infantryAmount, ArmyUnitType.Chivalry,
                                         cavalryAmount, ArmyUnitType.Artillery);
        }
        else{
            territoryArmy.tradeArmyUnits(ArmyUnitType.Infantry, infantryAmount, ArmyUnitType.Chivalry,
                                         cavalryAmount, ArmyUnitType.Chivalry);
        }

    }

    private void replaceUnitsCondition(int infantryAmount, int cavalryAmount, int territoryId) throws Error{
        Territory territory = _mapService.findTerritory(territoryId);

        if (!(territory.getOwnerId() == _playerService.getCurrentPlayer().getId())) {
            throw new Error("Choose your own territory.");
        }
        else if (!(territory.getTerritoryArmy().getArmyAmount(ArmyUnitType.Infantry) >= infantryAmount &&
                territory.getTerritoryArmy().getArmyAmount(ArmyUnitType.Chivalry) >= cavalryAmount)) {
            throw new Error("You do not have enough army units.");
        }
        else{
            return;
        }
    }

}
