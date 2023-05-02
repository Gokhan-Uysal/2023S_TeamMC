package app.domain.models.GameMap;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import javax.imageio.ImageIO;
import app.common.AppConfig;

public class Territory {
    private String name;
    private String imageName;
    private TerritoryPosition territoryPosition;
    private Set<String> adjList;
    private int ownerId;
    private int infantryAmount = 0;
    private int cavalryAmount = 0;
    private int artilleryAmount = 0;


    public Territory(String name, String imageName, TerritoryPosition territoryPosition, Set<String> adjList) {
        this.name = name;
        this.imageName = imageName;
        this.territoryPosition = territoryPosition;
        this.adjList = adjList;
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

    public int getInfantryAmount() {
        return infantryAmount;
    }

    public int getCavalryAmount() {
        return cavalryAmount;
    }

    public int getArtilleryAmount() {
        return artilleryAmount;
    }

    public void increaseInfantry(int amount){
        this.infantryAmount += amount;
    }

    public void decreaseInfantry(int amount){
        this.infantryAmount -= amount;
    }

    public void increaseCavalry(int amount){
        this.cavalryAmount += amount;
    }

    public void decreaseCavalry(int amount){
        this.cavalryAmount -= amount;
    }

    public void increaseArtillery(int amount){
        this.artilleryAmount += amount;
    }

    public void decreaseArtillery(int amount){
        this.artilleryAmount -= amount;
    }

    public int getArmyValue(){
        return 10*this.artilleryAmount + 5*this.cavalryAmount + this.infantryAmount;
    }

    public int getArmyAmount(){
        return this.infantryAmount + this.cavalryAmount + this.artilleryAmount;
    }

}
