package app.domain.models.GameMap;

public class TerritoryPosition {
    private int x;
    private int y;

    public TerritoryPosition(int xPos, int yPos) {
        this.x = xPos;
        this.y = yPos;
    }

    @Override
    public String toString() {
        String info = "";
        info += "X: " + x;
        info += "\t";
        info += "Y:" + y;
        return info;
    }
}
