package app.domain.models.army;

public class Infantry extends ArmyUnit {
    protected Infantry(String description) {
        super(Infantry.class.getName(), description);
        this.value = 1;
    }
}
