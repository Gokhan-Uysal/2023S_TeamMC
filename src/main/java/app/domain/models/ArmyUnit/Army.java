package app.domain.models.ArmyUnit;

import java.util.ArrayList;
import java.util.HashMap;

public class Army {
    protected HashMap<ArmyUnitType, ArrayList<ArmyUnit>> armyContainer = new HashMap<>();
    protected ArmyUnitFactory armyUnitFactory = new ArmyUnitFactory();

    public Army(){
        this.armyContainer.put(ArmyUnitType.Artillery, new ArrayList<>());
        this.armyContainer.put(ArmyUnitType.Chivalry, new ArrayList<>());
        this.armyContainer.put(ArmyUnitType.Infantry, new ArrayList<>());
    }

    public void addArmyUnits(ArmyUnitType type, int amount){
        for (int i = 0; i < amount; i++){
            this.armyContainer.get(type).add(armyUnitFactory.create(type));
        }
    }
    public void addArmyUnits(ArmyUnitType type, ArrayList<ArmyUnit> armyUnitList){
        this.armyContainer.get(type).addAll(armyUnitList);
    }

    public ArrayList<ArmyUnit> getArmyUnits(ArmyUnitType type, int amount){
        ArrayList<ArmyUnit> aUnits = new ArrayList<>();
        for (int i = 0; i < amount; i++){
            aUnits.add(this.armyContainer.get(type).remove(0));
        }
        return aUnits;
    }

    public void transferArmyUnits(Army otherArmy, ArmyUnitType type, int amount){
        for (int i = 0; i < amount; i++){
            otherArmy.addArmyUnits(type, this.getArmyUnits(type, amount));
        }
    }

    public void tradeArmyUnits(){}
}
