package app.domain.models.modelViews;

import app.domain.models.entities.BaseEntity;

public class CountryArmyViewModel extends BaseEntity {
    public String country_name;
    public int infantry_count;
    public int cavalry_count;
    public int artillery_count;

    public CountryArmyViewModel(int id, String country_name, int infantry_count, int cavalry_count,
            int artillery_count) {
        super(id);
        this.country_name = country_name;
        this.infantry_count = infantry_count;
        this.cavalry_count = cavalry_count;
        this.artillery_count = artillery_count;
    }

    public static class Builder {
        private int id;
        private String country_name;
        private int infantry_count;
        private int cavalry_count;
        private int artillery_count;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setCountryName(String country_name) {
            this.country_name = country_name;
            return this;
        }

        public Builder setInfantryCount(int infantry_count) {
            this.infantry_count = infantry_count;
            return this;
        }

        public Builder setCavalryCount(int cavalry_count) {
            this.cavalry_count = cavalry_count;
            return this;
        }

        public Builder setArtilleryCount(int artillery_count) {
            this.artillery_count = artillery_count;
            return this;
        }

        public CountryArmyViewModel build() {
            return new CountryArmyViewModel(id, country_name, infantry_count, cavalry_count, artillery_count);
        }
    }
}
