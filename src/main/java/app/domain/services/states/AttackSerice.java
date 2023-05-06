package app.domain.services.states;

import app.common.errors.AttackError;
import app.domain.models.army.Army;
import app.domain.models.army.ArmyUnitType;

public class AttackSerice {
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
}
