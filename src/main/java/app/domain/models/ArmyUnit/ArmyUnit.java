package app.domain.models.ArmyUnit;

import javax.swing.ImageIcon;

abstract class ArmyUnit {
    protected String name;
    protected String description;
    protected ImageIcon imageIcon;

    protected ArmyUnit(String name, String description, ImageIcon imageIcon){
        this.name = name;
        this.description = description;
        this.imageIcon = imageIcon;
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
