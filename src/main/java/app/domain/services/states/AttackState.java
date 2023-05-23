package app.domain.services.states;

import app.common.errors.AttackError;
import app.domain.models.card.BaseCard;
import app.domain.models.card.DeckType;
import app.domain.models.card.army.ArmyCardType;
import app.domain.models.card.territory.TerritoryCard;
import app.domain.models.game.map.Territory;
import app.domain.models.player.Player;
import app.domain.models.army.Army;
import app.domain.models.army.ArmyUnitType;

import app.domain.services.GameManagerService;
import app.domain.services.map.MapService;
import app.domain.services.PlayerService;

import java.util.Random;

public class AttackState {

    private MapService _mapService = MapService.getInstance();
    private String _winningPlayer;

    public void attack(int attackingPlayerId, int attackerTerritoryId, int attackedTerritoryId) {

        validateAttack(attackerTerritoryId, attackedTerritoryId, attackingPlayerId);

        boolean playerCanDrawCard = this.attackOneTerritory(attackingPlayerId,
                attackerTerritoryId, attackedTerritoryId);

        if (playerCanDrawCard) {
            Player winningPlayer = PlayerService.getInstance().getCurrentPlayer();
            BaseCard drawnCard;

            Random rand = new Random();
            int armyOrTerritory = rand.nextInt(2);
            if (armyOrTerritory == 0){
                drawnCard = GameManagerService.getInstance().getCentralDeck().drawCard(DeckType.Army);
            }
            else {
                drawnCard = GameManagerService.getInstance().getCentralDeck().drawCard(DeckType.Territory);
            }

            switch (drawnCard.getName()) {
                case "InfantryCard" -> {
                    winningPlayer.getPlayerDecks().addArmyCard(ArmyCardType.Infantry);
                }
                case "CavalryCard" -> {
                    winningPlayer.getPlayerDecks().addArmyCard(ArmyCardType.Cavalry);
                }
                case "ArtilleryCard" -> {
                    winningPlayer.getPlayerDecks().addArmyCard(ArmyCardType.Artillery);
                }
                default -> {
                    TerritoryCard tDrawnCard = (TerritoryCard) drawnCard;
                    winningPlayer.getPlayerDecks().addTerritoryCards(tDrawnCard.getDescription(),
                            tDrawnCard.getImage(), tDrawnCard.getTerritoryId());
                }
            }
        }
    }

    public String getWinningPlayer(){
        return this._winningPlayer;
    }

    private boolean attackOneTerritory(int attackingPlayerId, int attackerTerritoryId, int attackedTerritoryId) {

        Army attackerTerritoryArmy = _mapService.findTerritory(attackerTerritoryId).getTerritoryArmy();
        Army attackedTerritoryArmy = _mapService.findTerritory(attackedTerritoryId).getTerritoryArmy();

        int attackerDiceRoll = rollDice();
        int attackedDiceRoll = rollDice();

        if (attackerDiceRoll > attackedDiceRoll) {
            dealArmyAttackerWin(attackedTerritoryArmy);
            this._winningPlayer = PlayerService.getInstance().getPlayerById(attackingPlayerId).getUsername();
        } else {
            dealArmyDefenderWin(attackerTerritoryArmy);
            this._winningPlayer = PlayerService.getInstance().getPlayerById(_mapService.findTerritory(attackedTerritoryId)
                    .getOwnerId()).getUsername();
        }

        if (attackedTerritoryArmy.getTotalArmyAmount() <= 0) {

            if (attackerTerritoryArmy.getArmyAmount(ArmyUnitType.Infantry) > 0) {
                attackerTerritoryArmy.transferArmyUnits(attackedTerritoryArmy,
                        ArmyUnitType.Infantry, 1);
            } else if (attackerTerritoryArmy.getArmyAmount(ArmyUnitType.Chivalry) > 0) {
                attackerTerritoryArmy.transferArmyUnits(attackedTerritoryArmy,
                        ArmyUnitType.Chivalry, 1);
            } else if (attackedTerritoryArmy.getArmyAmount(ArmyUnitType.Artillery) > 0) {
                attackerTerritoryArmy.transferArmyUnits(attackedTerritoryArmy,
                        ArmyUnitType.Artillery, 1);
            }

            _mapService.changeOwnerOfTerritory(attackingPlayerId, attackedTerritoryId);
            return true;
        }

        return false;
    }

    private static int rollDice() {
        Random rand = new Random();
        return rand.nextInt(6);
    }

    private void dealArmyAttackerWin(Army loserArmy) {

        if (loserArmy.getArmyAmount(ArmyUnitType.Artillery) > 0) {
            loserArmy.getArmyUnits(ArmyUnitType.Artillery, 1);
        } else if (loserArmy.getArmyAmount(ArmyUnitType.Chivalry) > 0) {
            loserArmy.getArmyUnits(ArmyUnitType.Chivalry, 1);
        } else if (loserArmy.getArmyAmount(ArmyUnitType.Infantry) > 0) {
            loserArmy.getArmyUnits(ArmyUnitType.Infantry, 1);
        }
    }

    private void dealArmyDefenderWin(Army loserArmy) {

        if (loserArmy.getArmyAmount(ArmyUnitType.Artillery) > 1) {
            loserArmy.getArmyUnits(ArmyUnitType.Artillery, 2);
        } else if (loserArmy.getArmyAmount(ArmyUnitType.Artillery) > 0) {
            loserArmy.getArmyUnits(ArmyUnitType.Artillery, 1);
            loserArmy.getArmyUnits(ArmyUnitType.Chivalry, 1);
        } else if (loserArmy.getArmyAmount(ArmyUnitType.Chivalry) > 1) {
            loserArmy.getArmyUnits(ArmyUnitType.Chivalry, 2);
        } else if (loserArmy.getArmyAmount(ArmyUnitType.Chivalry) > 0) {
            loserArmy.getArmyUnits(ArmyUnitType.Chivalry, 1);
            loserArmy.getArmyUnits(ArmyUnitType.Infantry, 1);
        } else if (loserArmy.getArmyAmount(ArmyUnitType.Infantry) > 1) {
            loserArmy.getArmyUnits(ArmyUnitType.Infantry, 2);
        } else if (loserArmy.getArmyAmount(ArmyUnitType.Infantry) > 0) {
            loserArmy.getArmyUnits(ArmyUnitType.Infantry, 1);
        }
    }


    private void validateAttack(int attackerTerritoryId, int defenderTerritoryId, int playerId) throws AttackError {

        Army attacker = _mapService.findTerritory(attackerTerritoryId).getTerritoryArmy();
        Army defender = _mapService.findTerritory(defenderTerritoryId).getTerritoryArmy();

        if (!PlayerService.getInstance().checkIfPlayerOwnsTerritory(playerId, attackerTerritoryId)){
            throw new AttackError("Please choose one of your own territories.");
        }
        if (attacker.getTotalArmyAmount() <= 2) {
            throw new AttackError("Insufficient army count");
        }
        if (attacker.getTotalArmyValue() <= defender.getTotalArmyValue()) {
            throw new AttackError("Weak army force");
        }
        if (!isValidAttack(attacker, defender)) {
            throw new AttackError("Not valid match-up");
        }
        if (!checkIfAdjacentAndAttackable(attackerTerritoryId, defenderTerritoryId)) {
            throw new AttackError("Territory not adjacent or not enemy territory.");
        }
    }

    private boolean isValidAttack(Army attacker, Army defender) {
        if (defender.getArmyAmount(ArmyUnitType.Artillery) > 0) {
            return attacker.getArmyAmount(ArmyUnitType.Artillery) > 0 &&
                    attacker.getTotalArmyValue() > defender
                            .getTotalArmyValue();
        } else if (defender.getArmyAmount(ArmyUnitType.Chivalry) > 0) {
            return attacker.getArmyAmount(ArmyUnitType.Chivalry) > 0 &&
                    attacker.getTotalArmyValue() > defender
                            .getTotalArmyValue();
        } else if (defender.getArmyAmount(ArmyUnitType.Infantry) > 0) {
            return attacker.getArmyAmount(ArmyUnitType.Infantry) > 0 &&
                    attacker.getTotalArmyValue() > defender
                            .getTotalArmyValue();
        }
        return false;
    }


    private boolean checkIfAdjacentAndAttackable(int attackerTerritoryId, int defenderTerritoryId) {

        Territory attackerTerritory = _mapService.findTerritory(attackerTerritoryId);
        Territory defenderTerritory = _mapService.findTerritory(defenderTerritoryId);

        return defenderTerritory.getOwnerId() != attackerTerritory.getOwnerId() &&
                _mapService.getAdjacentTerritories(attackerTerritory).contains(defenderTerritory);
    }

}
