package app.domain.models.GameMap;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import javax.imageio.ImageIO;
import app.common.AppConfig;
import app.domain.models.ArmyUnit.Army;

public class Territory {
    private String name;
    private String imageName;
    private TerritoryPosition territoryPosition;
    private Set<String> adjList;
    private int ownerId;
    private int territoryId;
    private static int classTerritoryId = 0;
    private Army territoryArmy;


    public Territory(String name, String imageName, TerritoryPosition territoryPosition, Set<String> adjList) {
        this.name = name;
        this.imageName = imageName;
        this.territoryPosition = territoryPosition;
        this.adjList = adjList;

        this.territoryArmy = new Army();
        this.territoryId = ++classTerritoryId;
    }

    public BufferedImage getImage() throws IOException {
        File imageFile = new File(AppConfig.basePath + "/resource/assets/territories/" + imageName);
        return ImageIO.read(imageFile);
    }

    @Override
    public String toString() {
        String info = "";
        info += name;
        info += "\s\s";
        info += imageName;
        info += "\s\s";
        info += territoryPosition.toString();
        info += "\s\s";
        info += "\n";
        return info;
    }

    public String getName() {
        return name;
    }

    public String getImageName() {
        return imageName;
    }

    public TerritoryPosition getTerritoryPosition() {
        return territoryPosition;
    }

    public Set<String> getAdjList() {
        return adjList;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public void setTerritoryPosition(TerritoryPosition territoryPosition) {
        this.territoryPosition = territoryPosition;
    }

    public Army getTerritoryArmy(){
        return this.territoryArmy;
    }

    public int getTerritoryId(){
        return this.territoryId;
    }
}
