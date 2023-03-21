package app.data_access.entities;

public class Country {
    private final String name;
    private final Continent continent;

    public String getName() {
        return name;
    }

    public Continent getContinent() {
        return continent;
    }

    public Country(Builder builder) {
        this.name = builder.name;
        this.continent = builder.continent;
    }

    public static class Builder{
        private String name;
        private Continent continent;

        public Builder(){};
        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder continent(Continent continent){
            this.continent = continent;
            return this;
        }

        public Country build(){
            return new Country(this);
        }
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", continent=" + continent +
                '}';
    }
}
