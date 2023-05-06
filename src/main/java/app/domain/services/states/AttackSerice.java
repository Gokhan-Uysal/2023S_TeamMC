package app.domain.services.states;

import app.common.errors.AttackError;
import app.domain.models.army.Army;

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
    }
}
