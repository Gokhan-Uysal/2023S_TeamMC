package app.domain.models.ArmyUnit;

import javax.swing.ImageIcon;

abstract class ArmyUnit {
    protected String name;
    protected String description;
    protected ImageIcon imageIcon;
    protected int value;

    protected ArmyUnit(String name, String description) {
        this.name = name;
        this.description = description;
    }

    protected String getName() {
        return name;
    }

    protected String getDescription() {
        return description;
    }

    protected ImageIcon getImageIcon() {
        return imageIcon;
    }
}
