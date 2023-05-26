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
        int armyUnitNumber = receivedUnitAmount();

        Territory placementTerritory = _mapService.findTerritory(territoryId);
        if (placementTerritory.getOwnerId() == _playerService.getCurrentPlayer().getId()){
            placementTerritory.getTerritoryArmy().addArmyUnits(ArmyUnitType.Infantry, armyUnitNumber);
        }
        else{
            throw new Error("Select one of your own territories.");
        }
    }

    public int receivedUnitAmount(){
        if (_playerService.numberOfTerritories(_playerService.getCurrentPlayer().getId()) <= 9){
            return 3;
        }
        else{
            return _playerService.numberOfTerritories(_playerService.getCurrentPlayer().getId()) / 3;
        }
    }
}
