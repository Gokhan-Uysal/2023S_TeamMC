package app.domain.services;

import app.domain.models.ArmyUnit.ArmyUnitType;
import app.domain.models.GameMap.Territory;
import app.domain.models.Player.Player;
import app.domain.models.ArmyUnit.Army;
import java.util.ArrayList;
import java.util.Random;

public class PlayerService {

    static ArrayList<Player> players = new ArrayList<>();
    private static final int UPPER_BOUND = 8;

    public static void createPlayer(ArrayList<String> names) {
        for (int i = 0; i < names.size(); i++) {
            Player newPlayer = new Player(names.get(i), i + 1);
            players.add(newPlayer);
            System.out.println(
                    "New Player is created successfully with id: " + newPlayer.getId() + " and name: " + newPlayer.getUsername());

        }
    }

    public static Player getPlayer(int playerId){
        for (Player p: players){
            if (p.getId().equals(playerId)){
                return p;
            }
        }
        return null;
    }

    public void attack(int attackingPlayerId, int attackedPlayerId, String attackerTerritoryName, String attackedTerritoryName){

        Player attackingPlayer = getPlayer(attackingPlayerId);
        Player attackedPlayer = getPlayer(attackedPlayerId);
        Territory attackerTerritory = attackingPlayer.getTerritory(attackerTerritoryName);
        Territory attackedTerritory = attackedPlayer.getTerritory(attackedTerritoryName);

        int attackerDiceRoll = rollDice();
        int attackedDiceRoll = rollDice();

        if (attackerDiceRoll > attackedDiceRoll){
            dealArmyAttackerWin(attackedPlayer.getPlayerArmy(), attackedTerritory);
        }
        else{
            dealArmyDefenderWin(attackingPlayer.getPlayerArmy(), attackerTerritory);
        }
    }

    public static int rollDice(){
        Random rand = new Random();
        return rand.nextInt(UPPER_BOUND);
    }

    public void dealArmyAttackerWin(Army loserArmy, Territory loserTerritory){
        if (loserTerritory.getArtilleryAmount() > 0){
            loserTerritory.decreaseArtillery(1);
            loserArmy.getArmyUnits(ArmyUnitType.Artillery, 1);
        }
        else if (loserTerritory.getCavalryAmount() > 0){
            loserTerritory.decreaseCavalry(1);
            loserArmy.getArmyUnits(ArmyUnitType.Chivalry, 1);
        }
        else{
            loserTerritory.decreaseInfantry(1);
            loserArmy.getArmyUnits(ArmyUnitType.Infantry, 1);
        }
    }

    public void dealArmyDefenderWin(Army loserArmy, Territory loserTerritory){
        if (loserTerritory.getArtilleryAmount() > 1){
            loserTerritory.decreaseArtillery(2);
            loserArmy.getArmyUnits(ArmyUnitType.Artillery, 2);
        }
        else if (loserTerritory.getArtilleryAmount() > 0){
            loserTerritory.decreaseArtillery(1);
            loserTerritory.decreaseCavalry(1);
            loserArmy.getArmyUnits(ArmyUnitType.Artillery, 1);
            loserArmy.getArmyUnits(ArmyUnitType.Chivalry, 1);
        }
        else if (loserTerritory.getCavalryAmount() > 1){
            loserTerritory.decreaseCavalry(2);
            loserArmy.getArmyUnits(ArmyUnitType.Chivalry, 2);
        }
        else if (loserTerritory.getCavalryAmount() > 0){
            loserTerritory.decreaseCavalry(1);
            loserTerritory.decreaseInfantry(1);
            loserArmy.getArmyUnits(ArmyUnitType.Chivalry, 1);
            loserArmy.getArmyUnits(ArmyUnitType.Infantry, 1);
        }
        else if (loserTerritory.getInfantryAmount() > 0){
            loserTerritory.decreaseInfantry(2);
            loserArmy.getArmyUnits(ArmyUnitType.Infantry, 2);
        }
    }
}
