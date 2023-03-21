package app.data_access.entities;

public class Continent {
    private final String name;
    public String getName() {
        return name;
    }

    public Continent(Builder builder){
        this.name = builder.name;
    }
    public static class Builder {
        private String name;

        public Builder(){}

        public Builder name(String name){
            this.name = name;
            return this;
        }
        public Continent build(){
            return new Continent(this);
        }
    }

    @Override
    public String toString() {
        return "Continent{" +
                "name='" + name + '\'' +
                '}';
    }
}
