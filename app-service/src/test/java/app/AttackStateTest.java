package app;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import app.common.Logger;
import app.domain.services.states.AttackState;

public class AttackStateTest {
    private AttackState _attackSatate;

    public AttackStateTest() {
        _attackSatate = new AttackState();
    }

    @Test
    public void AttackOneTerritoryUnitTest() {
        Logger.log("Attack one terriotry unit testing");
        int attackingPlayerId = 1;
        int attackerTerritoryId = 1;
        int attackedTerritoryId = 2;

        boolean result = _attackSatate.attackOneTerritory(attackingPlayerId, attackerTerritoryId, attackedTerritoryId);

        assertTrue("The attack was supposed to be successful but it was not.", result);
    }
}
