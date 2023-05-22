package app.domain.models.army;

import java.util.ArrayList;
import java.util.HashMap;

public class Army {
    protected HashMap<ArmyUnitType, ArrayList<ArmyUnit>> armyContainer = new HashMap<>();
    protected ArmyUnitFactory armyUnitFactory = new ArmyUnitFactory();

    public Army() {
        this.armyContainer.put(ArmyUnitType.Artillery, new ArrayList<>());
        this.armyContainer.put(ArmyUnitType.Chivalry, new ArrayList<>());
        this.armyContainer.put(ArmyUnitType.Infantry, new ArrayList<>());
    }

    public void addArmyUnits(ArmyUnitType type, int amount) {
        for (int i = 0; i < amount; i++) {
            this.armyContainer.get(type).add(armyUnitFactory.create(type));
        }

    }

    public void addArmyUnits(ArmyUnitType type, ArrayList<ArmyUnit> armyUnitList) {
        this.armyContainer.get(type).addAll(armyUnitList);
    }

    public ArrayList<ArmyUnit> getArmyUnits(ArmyUnitType type, int amount) {
        ArrayList<ArmyUnit> aUnits = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            aUnits.add(this.armyContainer.get(type).remove(0));
        }
        return aUnits;
    }

    public void transferArmyUnits(Army otherArmy, ArmyUnitType type, int amount) {
        this.getArmyUnits(type, amount);
        otherArmy.addArmyUnits(type, amount);
    }

    public void tradeArmyUnits(ArmyUnitType tradedUnit, int tradedAmount, ArmyUnitType addedUnit, int addedAmount) {
        this.getArmyUnits(tradedUnit, tradedAmount);
        this.addArmyUnits(addedUnit, addedAmount);
    }

    public void tradeArmyUnits(ArmyUnitType tradedUnit1, int tradedAmount1, ArmyUnitType tradedUnit2, int tradedAmount2,
            ArmyUnitType addedUnit, int addedAmount) {
        this.getArmyUnits(tradedUnit1, tradedAmount1);
        this.getArmyUnits(tradedUnit2, tradedAmount2);
        this.addArmyUnits(addedUnit, addedAmount);
    }

    public int getArmyAmount(ArmyUnitType type) {
        return this.armyContainer.get(type).size();
    }

    public int getArmyValue(ArmyUnitType type) {
        if (type.equals(ArmyUnitType.Infantry)) {
            return this.getArmyAmount(ArmyUnitType.Infantry);
        } else if (type.equals(ArmyUnitType.Chivalry)) {
            return this.getArmyAmount(ArmyUnitType.Chivalry) * 5;
        } else if (type.equals(ArmyUnitType.Artillery)) {
            return this.getArmyAmount(ArmyUnitType.Artillery) + 10;
        } else {
            return 0;
        }
    }

    public int getTotalArmyAmount() {
        return this.getArmyAmount(ArmyUnitType.Infantry) + this.getArmyAmount(ArmyUnitType.Chivalry) +
                this.getArmyAmount(ArmyUnitType.Artillery);
    }

    public int getTotalArmyValue() {
        return this.getArmyValue(ArmyUnitType.Infantry) + this.getArmyValue(ArmyUnitType.Chivalry) +
                this.getArmyValue(ArmyUnitType.Artillery);
    }

    @Override
    public String toString() {
        String info = "";
        info += String.format("Infantry: %s\n", getArmyAmount(ArmyUnitType.Infantry));
        info += String.format("Chivalry: %s\n", getArmyAmount(ArmyUnitType.Chivalry));
        info += String.format("Artillery: %s", getArmyAmount(ArmyUnitType.Artillery));
        return info;
    }
}
