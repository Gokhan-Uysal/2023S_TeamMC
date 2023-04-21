package app.domain.models.ArmyUnit;

import javax.swing.*;

public class Infantry extends ArmyUnit{
    protected Infantry(String description) {
        super(Infantry.class.getName(), description);
        this.value = 1;
    }
}
