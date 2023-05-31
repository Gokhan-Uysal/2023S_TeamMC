package app.domain.services.states;

import app.domain.models.army.ArmyUnitType;
import app.domain.models.game.map.Territory;
import app.domain.services.PlayerService;

public class ReceiveState {
    private PlayerService _playerService;

    public ReceiveState() {
        _playerService = PlayerService.getInstance();
    }

    public void placeReceivedUnits(Territory territory) throws Error{
        int armyUnitNumber = receivedUnitAmount();

        if (territory.getOwnerId() == _playerService.getCurrentPlayer().getId()){
            territory.getTerritoryArmy().addArmyUnits(ArmyUnitType.Infantry, armyUnitNumber);
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

    public void placeReceivedUnits(Territory territory, int receivedAmount) throws Error{

        if (territory.getOwnerId() == _playerService.getCurrentPlayer().getId()){
            territory.getTerritoryArmy().addArmyUnits(ArmyUnitType.Infantry, receivedAmount);
        }
        else{
            throw new Error("Select one of your own territories.");
        }
    }
}
