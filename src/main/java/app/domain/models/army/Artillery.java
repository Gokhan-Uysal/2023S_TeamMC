package app.domain.models.army;

public class Artillery extends ArmyUnit {
    protected Artillery(String description) {
        super(Artillery.class.getName(), description);
        this.value = 10;
    }
}
