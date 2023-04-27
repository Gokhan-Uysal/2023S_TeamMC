package app.ui.controllers.game.map;

import app.domain.models.GameMap.Territory;
import app.ui.views.game.map.TerritoryComponent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TerritoryComponentController {
    private TerritoryComponent territoryComponent;
    private Territory territory;

    public TerritoryComponentController(Territory territory) {
        this.territory = territory;
        this.territoryComponent = new TerritoryComponent(territory);
        setupListeners();
    }

    private void setupListeners() {
        territoryComponent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleTerritoryClicked();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                handleTerritoryEnter();
            }
        });
    }

    public TerritoryComponent getTerritoryComponent() {
        return territoryComponent;
    }

    private void handleTerritoryClicked() {
        System.out.println("Territory clicked: " + territory.name);
    }

    private void handleTerritoryEnter() {
        System.out.println("Territory hover: " + territory.name);
    }
}
