package app.domain.services.states;

import app.common.errors.AttackError;
import app.domain.models.card.BaseCard;
import app.domain.models.card.DeckType;
import app.domain.models.card.MainDecks;
import app.domain.models.card.army.ArmyCardType;
import app.domain.models.card.army.ArtilleryCard;
import app.domain.models.card.army.CavalryCard;
import app.domain.models.card.army.InfantryCard;
import app.domain.models.card.territory.TerritoryCard;
import app.domain.models.game.map.Territory;
import app.domain.models.game.map.TerritoryPosition;
import app.domain.models.player.Player;
import app.domain.models.army.Army;
import app.domain.models.army.ArmyUnitType;
import app.domain.services.GameManagerService;
import app.domain.services.map.MapService;
import app.domain.services.PlayerService;
import app.ui.views.game.map.TerritoryComponent;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicMarkableReference;

public class AttackState {

    private MapService _mapService = MapService.getInstance();
    private PlayerService _playerService = PlayerService.getInstance();
    private String _winningPlayer;
    public static int _attackerDiceRoll = 0;
    public static int _defenderDiceRoll = 0;
    public static boolean playerCanDrawCard = false;
    public static int changedUnitNumber = 0;
    public static TerritoryPosition lostTerritoryPosition = null;

    public void attack(int attackerTerritoryId, int attackedTerritoryId) {
        validateAttack(attackerTerritoryId, attackedTerritoryId);
        if (!playerCanDrawCard){
            playerCanDrawCard = this.attackOneTerritory(attackerTerritoryId, attackedTerritoryId);
        }

    }

    public static void drawCardIfAbleTo(){
        if (playerCanDrawCard) {
            Player winningPlayer = PlayerService.getInstance().getCurrentPlayer();
            MainDecks centralDeck = GameManagerService.getInstance().getCentralDeck();
            BaseCard drawnCard;

            if (centralDeck.isEmpty(DeckType.Army)){
                drawnCard = centralDeck.drawCard(DeckType.Territory);
            }
            else if (centralDeck.isEmpty(DeckType.Territory)){
                drawnCard = centralDeck.drawCard(DeckType.Army);
            }
            else{
                Random rand = new Random();
                int armyOrTerritory = rand.nextInt(2);

                if (armyOrTerritory == 0){
                    drawnCard = centralDeck.drawCard(DeckType.Army);
                }
                else {
                    drawnCard = centralDeck.drawCard(DeckType.Territory);
                }
            }

            if (drawnCard instanceof InfantryCard){
                winningPlayer.getPlayerDecks().addArmyCard(ArmyCardType.Infantry);
                System.out.println("Player has drawn infantry card.");
            }
            else if (drawnCard instanceof CavalryCard){
                winningPlayer.getPlayerDecks().addArmyCard(ArmyCardType.Cavalry);
                System.out.println("Player has drawn cavalry card.");
            }
            else if (drawnCard instanceof ArtilleryCard){
                winningPlayer.getPlayerDecks().addArmyCard(ArmyCardType.Artillery);
                System.out.println("Player has drawn artillery card.");
            }
            else{
                TerritoryCard tDrawnCard = (TerritoryCard) drawnCard;
                winningPlayer.getPlayerDecks().addTerritoryCards(tDrawnCard.getDescription(),
                        tDrawnCard.getImage(), tDrawnCard.getTerritoryId());
                System.out.println("Player has drawn territory card.");
            }
        }
    }

    public String getWinningPlayer() {
        return this._winningPlayer;
    }

    public boolean attackOneTerritory(int attackerTerritoryId, int defenderTerritoryId) {

        Player currentPlayer = _playerService.getCurrentPlayer();

        Army attackerTerritoryArmy = _mapService.findTerritory(attackerTerritoryId).getTerritoryArmy();
        Army attackedTerritoryArmy = _mapService.findTerritory(defenderTerritoryId).getTerritoryArmy();

        int attackerDiceRoll = rollDice();
        int defenderDiceRoll = rollDice();


        _attackerDiceRoll = attackerDiceRoll;
        _defenderDiceRoll = defenderDiceRoll;

        if (attackerDiceRoll > defenderDiceRoll) {
            dealArmyAttackerWin(attackedTerritoryArmy);
            lostTerritoryPosition = _mapService.findTerritory(defenderTerritoryId).getTerritoryPosition();
            _winningPlayer = currentPlayer.getUsername();
        } else {
            dealArmyDefenderWin(attackerTerritoryArmy);
            lostTerritoryPosition = _mapService.findTerritory(attackerTerritoryId).getTerritoryPosition();
            _winningPlayer = _playerService.getPlayerById(_mapService.findTerritory(defenderTerritoryId).getOwnerId())
                    .getUsername();
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

            _mapService.changeOwnerOfTerritory(currentPlayer.getId(), defenderTerritoryId);
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

        changedUnitNumber = 1;
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
            changedUnitNumber = 1;
            return;
        }

        changedUnitNumber = 2;
    }

    /*
     * validateAttack: Check the validity of the attack that the player wants to perform.
     *
     * @requires
     *           attackerTerritoryId corresponds to the ID of a valid territory in the game. This means that
     *                             the attackerTerritoryId should be positive and correspond to the ID of a
     *                             territory that is not closed.
     *           defenderTerritoryId corresponds to the ID of a valid territory in the game. This means that
     *                             the defenderTerritoryId should be positive and correspond to the ID of a
     *                             territory that is not closed.
     *           playerId should correspond to the ID of a valid player in the game.
     * @modifies
     *          The method does not modify any parameters.
     * @effect
     *          Returns nothing if the attack is valid. This means that the attacking player is attacking
     *              from one of their own territories to another player's territory, the attacker army has at least
     *              2 units and the attacker army is stronger than the defender army.
     *          Throws an error if the previously mentioned conditions are not satisfied.
     *
     *
     */
    public void validateAttack(int attackerTerritoryId, int defenderTerritoryId) throws AttackError {
        Player currentPlayer = _playerService.getCurrentPlayer();

        Army attacker = _mapService.findTerritory(attackerTerritoryId).getTerritoryArmy();
        Army defender = _mapService.findTerritory(defenderTerritoryId).getTerritoryArmy();

        if (!_playerService.checkIfPlayerOwnsTerritory(currentPlayer.getId(), attackerTerritoryId)) {
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
            return attacker.getArmyAmount(ArmyUnitType.Artillery) >
                    defender.getArmyAmount(ArmyUnitType.Artillery) &&
                    attacker.getTotalArmyValue() >
                            defender.getTotalArmyValue();
        } else if (defender.getArmyAmount(ArmyUnitType.Chivalry) > 0) {
            return attacker.getArmyAmount(ArmyUnitType.Chivalry) >
                    defender.getArmyAmount(ArmyUnitType.Chivalry) &&
                    attacker.getTotalArmyValue() >
                            defender.getTotalArmyValue();
        } else if (defender.getArmyAmount(ArmyUnitType.Infantry) > 0) {
            return attacker.getArmyAmount(ArmyUnitType.Infantry) >
                    defender.getArmyAmount(ArmyUnitType.Infantry) &&
                    attacker.getTotalArmyValue() >
                            defender.getTotalArmyValue();
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
