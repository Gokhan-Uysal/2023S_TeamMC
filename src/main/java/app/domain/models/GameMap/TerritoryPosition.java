package app.domain.models.GameMap;

import javax.xml.xpath.XPath;

public class TerritoryPosition {
    public int x;
    public int y;

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
