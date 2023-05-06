package app.domain.models.ArmyUnit;

public class ArmyUnitFactory {

    protected ArmyUnit create(ArmyUnitType type){

        ArmyUnit aUnit;

        if (type.equals(ArmyUnitType.Infantry)){
            aUnit = new Infantry("An infantry unit.");
        } else if (type.equals(ArmyUnitType.Chivalry)){
            aUnit = new Chivalry("A chivalry unit.");
        } else if (type.equals(ArmyUnitType.Artillery)){
            aUnit = new Artillery("An artillery unit.");
        } else{
            aUnit = null;
        }

        return aUnit;
    }
}
