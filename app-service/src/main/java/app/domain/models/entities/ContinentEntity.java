package app.domain.models.entities;

public class ContinentEntity extends BaseEntity {
    public String name;

    public ContinentEntity(int id, String name) {
        super(id);
        this.name = name;
    }

    public static class Builder {
        private int id;
        private String name;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public ContinentEntity build() {
            return new ContinentEntity(id, name);
        }
    }
}
