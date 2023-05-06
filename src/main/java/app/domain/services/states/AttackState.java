package app.domain.services.states;

import app.common.errors.AttackError;
import app.domain.models.Card.BaseCard;
import app.domain.models.Card.DeckType;
import app.domain.models.Card.MainDecks;
import app.domain.models.Card.army.ArmyCardType;
import app.domain.models.Card.army.ArtilleryCard;
import app.domain.models.Card.army.CavalryCard;
import app.domain.models.Card.army.InfantryCard;
import app.domain.models.Card.territory.TerritoryCard;
import app.domain.models.Player.Player;
import app.domain.models.army.Army;
import app.domain.models.army.ArmyUnitType;
import app.domain.models.game.map.Territory;

import app.domain.services.GameManagerService;
import app.domain.services.Map.MapService;
import app.domain.services.PlayerService;

import java.util.Random;

public class AttackState {

    private MapService _mapService = new MapService();

    public void attack(Army attacker, Army defender) {
        validateAttack(attacker, defender);


    }

    public void validateAttack(Army attacker, Army defender) throws AttackError {
        if (attacker.getTotalArmyAmount() < 2) {
            throw new AttackError("Insufficent army count");
        }
        if (attacker.getTotalArmyValue() <= defender.getTotalArmyValue()) {
            throw new AttackError("Weak army force");
        }
        if (!isValidAttack(attacker, defender)) {
            throw new AttackError("Not valid matchup");
        }
    }

    // private Set<Territory> playerCanAttackTo(Territory attacker) {
    // Set<Territory> adjSet = _mapGraphService.getEdges(attacker);
    // Set<Territory> validAdjSet = adjSet.stream()
    // .filter(defender -> isValidAttack(attacker, defender))
    // .collect(Collectors.toSet());

    // return validAdjSet;
    // }

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

    // public List<Territory> playerCanAttackFrom(Player player) {
    // ArrayList<Territory> attackableFrom = new ArrayList<>();

    // for (Territory t : this.getTerritoryListFromGraph()) {
    // if (t.getOwnerId() == playerId && t.getTerritoryArmy().getTotalArmyAmount()
    // >= 2) {
    // attackableFrom.add(t);
    // }
    // }
    // return attackableFrom;
    // }

    public void attack(int attackingPlayerId, int attackerTerritoryId, int attackedTerritoryId){

        boolean playerCanDrawCard = this.attackOneTerritory(attackingPlayerId,
                attackerTerritoryId, attackedTerritoryId);

        if (playerCanDrawCard){
            Player winningPlayer = PlayerService.getInstance().getCurrentPlayer();
            BaseCard drawnCard = GameManagerService.getInstance().getCentralDeck().drawCard(DeckType.Army);

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
                case "TerritoryCard" -> {
                    TerritoryCard tDrawnCard = (TerritoryCard) drawnCard;
                    winningPlayer.getPlayerDecks().addTerritoryCards(tDrawnCard.getDescription(),
                            tDrawnCard.getImage(), tDrawnCard.getTerritoryId());
                }
            }
        }
    }

    private boolean attackOneTerritory(int attackingPlayerId, int attackerTerritoryId, int attackedTerritoryId) {

        Army attackerTerritoryArmy = _mapService.findTerritory(attackerTerritoryId).getTerritoryArmy();
        Army attackedTerritoryArmy = _mapService.findTerritory(attackedTerritoryId).getTerritoryArmy();

        int attackerDiceRoll = rollDice();
        int attackedDiceRoll = rollDice();

        if (attackerDiceRoll > attackedDiceRoll) {
            dealArmyAttackerWin(attackedTerritoryArmy);
        } else {
            dealArmyDefenderWin(attackerTerritoryArmy);
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

}
