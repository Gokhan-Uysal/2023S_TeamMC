package app.domain.models.ArmyUnit;

import javax.swing.*;

public class Chivalry extends ArmyUnit{
    protected Chivalry(String description) {
        super(Chivalry.class.getName(), description);
        this.value = 5;
    }
}
