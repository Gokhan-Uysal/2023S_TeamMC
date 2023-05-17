package app.domain.services.states;

import app.domain.models.army.ArmyUnitType;
import app.domain.models.game.map.Territory;
import app.domain.services.PlayerService;
import app.domain.services.map.MapService;

public class RecieveState {
    private MapService _mapService;
    private PlayerService _playerService;

    public RecieveState() {
        _mapService = MapService.getInstance();
        _playerService = PlayerService.getInstance();
    }

    public void placeRecievedUnits(int territoryId){
        int armyUnitNumber = 0;
        int territoryNumber = _playerService.numberOfTerritories(_playerService.getCurrentPlayer().getId());

        if (territoryNumber <= 9){
            armyUnitNumber = 3;
        }
        else{
            armyUnitNumber = territoryNumber / 3;
        }

        Territory placementTerritory = _mapService.findTerritory(territoryId);
        if (placementTerritory.getOwnerId() == _playerService.getCurrentPlayer().getId()){
            placementTerritory.getTerritoryArmy().addArmyUnits(ArmyUnitType.Infantry, armyUnitNumber);
        }
        else{
            throw new Error("Select one of your own territories.");
        }
    }
}
