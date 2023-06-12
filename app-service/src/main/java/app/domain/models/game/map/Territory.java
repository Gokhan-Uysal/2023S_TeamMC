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
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Territory {
    @JsonProperty("name")
    private String _name;
    @JsonProperty("imageName")
    private String _imageName;
    @JsonProperty("territoryPosition")
    private TerritoryPosition _territoryPosition;
    private Set<String> _adjList;
    @JsonProperty("territoryArmy")
    private Army _territoryArmy;
    private int _territoryId;
    @JsonProperty("ownerId")
    private int _ownerId;
    @JsonProperty("isOpen")
    private boolean _isOpen;

    public boolean getIsOpen() {
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

    public Territory(int id, String name, String imageName, TerritoryPosition territoryPosition, Set<String> adjList) {
        this._name = name;
        this._imageName = imageName;
        this._territoryPosition = territoryPosition;
        this._adjList = adjList;
        this._territoryArmy = new Army();
        _ownerId = -1;
        _isOpen = true;
        this._territoryId = id;
    }
    public Territory() {
    }

    @JsonIgnore
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

    public Set<String> getAdjList() {
        return _adjList;
    }

    public void setTerritoryPosition(TerritoryPosition territoryPosition) {
        this._territoryPosition = territoryPosition;
    }

    public Army getTerritoryArmy() {
        return this._territoryArmy;
    }

    public int getTerritoryId() {
        return this._territoryId;
    }

    public Point getTerritoryPositionAsPoint(){
        return new Point(this._territoryPosition.getX(), this._territoryPosition.getY());
    }

    @Override
    public String toString() {
        String info = "";
        info += "-----------------------";
        info += String.format("\n%s \s %s \s \n%s\n %s\n", _name, _imageName, _territoryArmy == null ? "No army" : _territoryArmy.toString(),_territoryPosition);
        info += "-----------------------";
        return info;
    }
}
