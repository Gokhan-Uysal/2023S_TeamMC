package app.domain.models.entities;

public abstract class BaseEntity {
    int id;

    protected BaseEntity(int id) {
        this.id = id;
    }
}
