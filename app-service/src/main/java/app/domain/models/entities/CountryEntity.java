package app.domain.models.entities;

public class CountryEntity extends BaseEntity {
    public String name;
    public int position_x;
    public int position_y;
    public int continent_id;

    public CountryEntity(int id, String name, int position_x, int position_y, int continent_id) {
        super(id);
        this.name = name;
        this.position_x = position_x;
        this.position_y = position_y;
        this.continent_id = continent_id;
    }

    public static class Builder {
        private int id;
        private String name;
        private int position_x;
        private int position_y;
        private int continent_id;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setPositionX(int position_x) {
            this.position_x = position_x;
            return this;
        }

        public Builder setPositionY(int position_y) {
            this.position_y = position_y;
            return this;
        }

        public Builder setContinentId(int continent_id) {
            this.continent_id = continent_id;
            return this;
        }

        public CountryEntity build() {
            return new CountryEntity(id, name, position_x, position_y, continent_id);
        }
    }
}
