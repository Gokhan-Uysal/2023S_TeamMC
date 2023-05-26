package app.domain.models.entities;

public class ContinentEnity extends BaseEntity {
    public String name;

    public ContinentEnity(int id, String name) {
        super(id);
        this.name = name;
    }
}
