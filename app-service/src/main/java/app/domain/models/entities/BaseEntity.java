package app.domain.models.entities;

public abstract class BaseEntity {
    public int id;

    protected BaseEntity(int id) {
        this.id = id;
    }
}
