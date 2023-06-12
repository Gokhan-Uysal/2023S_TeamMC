package app.domain.services.states;

import app.domain.models.army.Army;
import app.domain.models.army.ArmyUnitType;
import app.domain.models.game.map.Territory;
import app.domain.services.PlayerService;
import app.domain.services.map.MapService;

public class ReplaceState {

    private PlayerService _playerService = PlayerService.getInstance();

    public void replaceUnits(int infantryAmount, int cavalryAmount, Territory territory) throws Error {
        replaceUnitsCondition(infantryAmount, cavalryAmount, territory);

        Army territoryArmy = territory.getTerritoryArmy();
        if (infantryAmount == 5 && cavalryAmount == 0){
            territoryArmy.tradeArmyUnits(ArmyUnitType.Infantry, infantryAmount, ArmyUnitType.Chivalry,
                                         cavalryAmount, ArmyUnitType.Chivalry);
        }
        else if (infantryAmount == 5 && cavalryAmount == 1){
            territoryArmy.tradeArmyUnits(ArmyUnitType.Infantry, infantryAmount, ArmyUnitType.Chivalry,
                    cavalryAmount, ArmyUnitType.Artillery);
        }
        else if (infantryAmount == 0 && cavalryAmount == 2){
            territoryArmy.tradeArmyUnits(ArmyUnitType.Infantry, infantryAmount, ArmyUnitType.Chivalry,
                    cavalryAmount, ArmyUnitType.Artillery);
        }
        else{
            throw new Error("Invalid trade amount configurations.");
        }

    }

    private void replaceUnitsCondition(int infantryAmount, int cavalryAmount, Territory territory) throws Error{

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
