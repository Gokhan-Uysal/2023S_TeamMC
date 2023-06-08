package app.domain.models.entities;

public class TerritoryCardEntity {
    public int id;
    public String description;
    public String image;
    public int country_id;

    private TerritoryCardEntity(int id, String description, String image, int country_id) {
        this.id = id;
        this.description = description;
        this.image = image;
        this.country_id = country_id;
    }

    public static class Builder {
        private int id;
        private String description;
        private String image;
        private int country_id;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setImage(String image) {
            this.image = image;
            return this;
        }

        public Builder setCountryId(int countryId) {
            this.country_id = countryId;
            return this;
        }

        public TerritoryCardEntity build() {
            return new TerritoryCardEntity(id, description, image, country_id);
        }
    }
}
