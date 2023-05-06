package app.ui.controllers.game.map;

import java.io.IOException;
import java.util.List;

import app.common.Logger;
import app.domain.models.game.map.Territory;
import app.domain.services.GameManagerService;
import app.domain.services.base.ISubscriber;
import app.ui.controllers.game.state.BaseStatePanelController;
import app.ui.views.game.map.MapPanel;

public class MapPanelController implements ISubscriber<Territory> {
    private MapPanel _mapPanel;

    public MapPanelController(MapPanel mapPanel) {
        this._mapPanel = mapPanel;
    }

    public void displayMap() {
        List<Territory> territoryList = GameManagerService.getInstance().getMap();
        territoryList.forEach((territory) -> {
            TerritoryComponentController trController;
            try {
                trController = new TerritoryComponentController(territory);
                _mapPanel.drawTerriotry(trController.getTerritoryComponent());
                trController.addSubscriber(this);
                trController.addSubscriber(BaseStatePanelController.getInstance());
            } catch (IOException e) {
                Logger.error(e);
            }
        });
    }

    public void loadMap() {
        GameManagerService.getInstance().loadMap();
    }

    @Override
    public void update(Territory message) {
        _mapPanel.updateMapInfo(message.getName(), message.getInfantryCount(), message.getChivalryCount(),
                message.getArtilleryCount());
    }
}
