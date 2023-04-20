package app.domain.models.ArmyUnit;

import javax.swing.*;

public class Artillery extends ArmyUnit{
    protected Artillery(String description, ImageIcon imageIcon) {
        super(Artillery.class.getName(), description, imageIcon);
    }
}
