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
    private String _name;
    private String _imageName;
    private TerritoryPosition _territoryPosition;
    private Set<String> _adjList;
    private Army _territoryArmy;
    private int territoryId;
    private int _ownerId;
    private boolean _isOpen;

    public boolean get_isOpen() {
        return this._isOpen;
    }

    public void setIsOpen(Boolean isOpen) {
        this._isOpen = isOpen;
    }

    public int getOwnerId() {
        return this._ownerId;
    }

    public void setOwnerId(int id) {
        this._ownerId = id;
    }

    public Territory(String name, String imageName, TerritoryPosition territoryPosition, Set<String> adjList) {
        this._name = name;
        this._imageName = imageName;
        this._territoryPosition = territoryPosition;
        this._adjList = adjList;
        this._territoryArmy = new Army();
        _ownerId = -1;
        _isOpen = true;
    }

    public BufferedImage getImage() throws IOException {
        File imageFile = new File(AppConfig.basePath + "/__resource__/assets/territories/" + _imageName);
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
        return this._territoryArmy.getArmyAmount(ArmyUnitType.Infantry);
    }

    public int getChivalryCount() {
        return this._territoryArmy.getArmyAmount(ArmyUnitType.Chivalry);
    }

    public int getArtilleryCount() {
        return this._territoryArmy.getArmyAmount(ArmyUnitType.Artillery);
    }

    public String getName() {
        return _name;
    }

    public String getImageName() {
        return _imageName;
    }

    public TerritoryPosition getTerritoryPosition() {
        return _territoryPosition;
    }

    public Set<String> get_adjList() {
        return _adjList;
    }

    public void setTerritoryPosition(TerritoryPosition territoryPosition) {
        this._territoryPosition = territoryPosition;
    }

    public Army getTerritoryArmy() {
        return this._territoryArmy;
    }

    public int getTerritoryId(){
        return this.territoryId;
    }

    @Override
    public String toString() {
        String info = "";
        info += _name;
        info += "\s\s";
        info += _imageName;
        info += "\s\s";
        info += _territoryPosition.toString();
        info += "\s\s";
        info += "\n";
        return info;
    }
}
