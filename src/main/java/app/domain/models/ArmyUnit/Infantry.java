package app.domain.models.ArmyUnit;

import javax.swing.*;

public class Infantry extends ArmyUnit{
    protected Infantry(String description, ImageIcon imageIcon) {
        super(Infantry.class.getName(), description, imageIcon);
    }
}
