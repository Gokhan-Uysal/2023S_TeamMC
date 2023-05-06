package app.domain.models.game.map;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import javax.imageio.ImageIO;
import app.common.AppConfig;
import app.domain.models.army.Army;
import app.domain.models.army.ArmyUnitType;

public class Territory {
    private String name;
    private String imageName;
    private TerritoryPosition territoryPosition;
    private Set<String> adjList;
    private int ownerId;
    private int territoryId;
    private static int classTerritoryId = 0;
    private Army territoryArmy;
    private boolean isOpen;

    public boolean getIsOpen() {
        return this.isOpen;
    }

    public void setIsOpen(Boolean isOpen) {
        this.isOpen = isOpen;
    }

    public Territory(String name, String imageName, TerritoryPosition territoryPosition, Set<String> adjList) {
        this.name = name;
        this.imageName = imageName;
        this.territoryPosition = territoryPosition;
        this.adjList = adjList;

        this.territoryArmy = new Army();
        this.territoryId = ++classTerritoryId;
        this.ownerId = -1;
        isOpen = true;
    }

    public BufferedImage getImage() throws IOException {
        File imageFile = new File(AppConfig.basePath + "/__resource__/assets/territories/" + imageName);
        BufferedImage originalImage = ImageIO.read(imageFile);
        int newWidth = originalImage.getWidth() / 2;
        int newHeight = originalImage.getHeight() / 2;
        BufferedImage scaledImage = new BufferedImage(newWidth, newHeight, originalImage.getType());

        Graphics2D g = scaledImage.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        g.dispose();

        return scaledImage;
    }

    public int getInfantryCount() {
        return this.territoryArmy.getArmyAmount(ArmyUnitType.Infantry);
    }

    public int getChivalryCount() {
        return this.territoryArmy.getArmyAmount(ArmyUnitType.Chivalry);
    }

    public int getArtilleryCount() {
        return this.territoryArmy.getArmyAmount(ArmyUnitType.Artillery);
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

    public Army getTerritoryArmy() {
        return this.territoryArmy;
    }

    public int getTerritoryId() {
        return this.territoryId;
    }
}
