package app.domain.services.states;

import app.domain.models.army.Army;
import app.domain.models.army.ArmyUnitType;
import app.domain.services.PlayerService;
import app.domain.services.map.*;

public class FortifyState {

    private MapService _mapService = MapService.getInstance();

    public void fortify(int infantryAmount, int cavalryAmount, int artilleryAmount, int startTerritoryId,
                        int destinationTerritoryId, int playerId) throws Error{
        if (!PlayerService.getInstance().checkIfPlayerOwnsTerritory(playerId, startTerritoryId) &&
                !PlayerService.getInstance().checkIfPlayerOwnsTerritory(playerId, destinationTerritoryId) &&
                territoryFortifyCondition(infantryAmount, cavalryAmount, artilleryAmount, startTerritoryId)){

            Army startTerritoryArmy = _mapService.findTerritory(startTerritoryId).getTerritoryArmy();
            Army destinationTerritoryArmy = _mapService.findTerritory(destinationTerritoryId).getTerritoryArmy();

            startTerritoryArmy.transferArmyUnits(destinationTerritoryArmy, ArmyUnitType.Infantry, infantryAmount);
            startTerritoryArmy.transferArmyUnits(destinationTerritoryArmy, ArmyUnitType.Chivalry, cavalryAmount);
            startTerritoryArmy.transferArmyUnits(destinationTerritoryArmy, ArmyUnitType.Artillery, artilleryAmount);
        }
        else{
            throw new Error("There has been a fortify error, please check the territories and army unit number.");
        }
    }

    private boolean territoryFortifyCondition(int infantryAmount, int cavalryAmount, int artilleryAmount,
                                             int territoryId){
        Army foundTerritoryArmy = _mapService.findTerritory(territoryId).getTerritoryArmy();
        return ((infantryAmount+cavalryAmount+artilleryAmount) < foundTerritoryArmy.getTotalArmyAmount() &&
                (infantryAmount <= foundTerritoryArmy.getArmyAmount(ArmyUnitType.Infantry) &&
                        cavalryAmount <= foundTerritoryArmy.getArmyAmount(ArmyUnitType.Chivalry) &&
                        artilleryAmount <= foundTerritoryArmy.getArmyAmount(ArmyUnitType.Artillery)));
    }

}
