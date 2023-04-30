package app.domain.models.ArmyUnit;

import javax.swing.ImageIcon;

public class Artillery extends ArmyUnit{
    protected Artillery(String description) {
        super(Artillery.class.getName(), description);
        this.value = 10;
    }
}
