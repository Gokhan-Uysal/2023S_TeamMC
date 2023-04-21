package app.domain.models.ArmyUnit;

import java.util.ArrayList;

public class Army {
    protected ArrayList<ArmyUnit> armyContainer = new ArrayList<>();
    protected ArmyUnitFactory armyUnitFactory = new ArmyUnitFactory();

    public void addArmyUnits(String type, int amount){
        for (int i = 0; i < amount; i++){
            this.armyContainer.add(armyUnitFactory.create(type));
        }
    }

    public ArrayList<ArmyUnit> getArmyUnit(int amount){
        ArrayList<ArmyUnit> aUnits = new ArrayList<>();
        for (int i = 0; i < amount; i++){
            aUnits.add(this.armyContainer.remove(0));
        }
        return aUnits;
    }
}
