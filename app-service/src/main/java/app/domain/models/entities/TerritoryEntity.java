package app.domain.models.entities;

public class TerritoryEntity extends BaseEntity {
    public String name;
    public int continent_id;

    public TerritoryEntity(int id, String name, int continent_id) {
        super(id);
        this.name = name;
        this.continent_id = continent_id;
    }
}
