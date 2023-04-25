package app.domain.models.GameMap;

import java.awt.Shape;

public class Territory {
    public String name;
    public Shape shape;

    public Territory(String name, Shape shape) {
        this.name = name;
        this.shape = shape;
    }

    public Territory(String name) {
        this.name = name;
        shape = null;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
