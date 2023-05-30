package app.domain.services.states;

import app.domain.models.army.Army;
import app.domain.models.army.ArmyUnitType;
import app.domain.models.army.Infantry;
import app.domain.models.game.map.Territory;
import app.domain.services.PlayerService;
import app.domain.services.map.MapService;

import java.util.Random;

public class RecieveState {
    private MapService _mapService;
    private PlayerService _playerService;
    private ArmyUnitType Infantry;

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

    public void recieveChanceCard(){
        Random rand = new Random();
        int cardId = rand.nextInt(5);
        switch (cardId){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
        }
    }

    public void reinforcement(int territoryId){
        int armyCount = rollDice();
        Territory territoryToReinforcement = _mapService.findTerritory(territoryId);
        Army armyInTerritory = territoryToReinforcement.getTerritoryArmy();
        armyInTerritory.getArmyUnits(Infantry, armyCount);
    }

    public void sabotage(int opponentTerritoryId){
        int armyCount = rollDice();
        Territory territoryToDecrement = _mapService.findTerritory(opponentTerritoryId);
        Army armyInTerritory = territoryToDecrement.getTerritoryArmy();
        int value =  armyInTerritory.getArmyAmount(Infantry);
        armyInTerritory.getArmyUnits(Infantry, value-armyCount);
    }

    public void coup(int opponentTerritoryId, int playerId){
        Territory territoryToCoup = _mapService.findTerritory(opponentTerritoryId);
        territoryToCoup.setOwnerId(playerId);
    }

    public void revolution(int opponentTerritoryId, int playerId){
        Territory territoryToRevolution = _mapService.findTerritory(opponentTerritoryId);
        territoryToRevolution.setOwnerId(playerId);
    }

    public void rebellion(int opponentTerritoryId){
        int recievedArmyCount = 0;
        Territory territoryToRebellion = _mapService.findTerritory(opponentTerritoryId);
        Army armyInTerritory = territoryToRebellion.getTerritoryArmy();
        int value =  armyInTerritory.getArmyAmount(Infantry);
        if (value%2 == 0){
            recievedArmyCount+=value/2;
        }
        else{
            recievedArmyCount+=(value+1)/2;
        }
    }


    private static int rollDice() {
        Random rand = new Random();
        return rand.nextInt(6);
    }


}
