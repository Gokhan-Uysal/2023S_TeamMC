package app.domain.models.modelViews;

public class AdjacentCountryViewModel {
    public String country_name;
    public String adjacent_country_name;

    public AdjacentCountryViewModel(String country_name, String adjacent_country_name) {
        this.country_name = country_name;
        this.adjacent_country_name = adjacent_country_name;
    }

    public static class Builder {
        private String country_name;
        private String adjacent_country_name;

        public Builder setCountryName(String country_name) {
            this.country_name = country_name;
            return this;
        }

        public Builder setAdjacentCountryName(String adjacent_country_name) {
            this.adjacent_country_name = adjacent_country_name;
            return this;
        }

        public AdjacentCountryViewModel build() {
            return new AdjacentCountryViewModel(country_name, adjacent_country_name);
        }
    }
}
