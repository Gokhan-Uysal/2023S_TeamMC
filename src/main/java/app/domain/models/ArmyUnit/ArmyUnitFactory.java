package app.domain.models.ArmyUnit;

public class ArmyUnitFactory {

    protected String type;

    protected ArmyUnit create(String type){

        ArmyUnit aUnit;

        if (type.equals("Infantry")){
            aUnit = new Infantry("An infantry unit.");
        } else if (type.equals("Chivalry")){
            aUnit = new Chivalry("A chivalry unit.");
        } else if (type.equals("Artillery")){
            aUnit = new Artillery("An artillery unit.");
        } else{
            aUnit = null;
        }

        return aUnit;
    }
}
