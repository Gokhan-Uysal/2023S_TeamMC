package app.ui.controllers.game.map;

import app.domain.models.GameMap.Territory;
import app.domain.services.base.BasePublisher;
import app.ui.views.game.map.TerritoryComponent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class TerritoryComponentController extends BasePublisher<Territory> {
    private TerritoryComponent territoryComponent;
    private Territory territory;

    public TerritoryComponentController(Territory territory) throws IOException {
        super(territory);
        this.territory = territory;
        this.territoryComponent = new TerritoryComponent(territory.name, territory.getImage());
        updateComponentBounds();
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

    public void updateComponentBounds() {
        territoryComponent.setBounds(territory.territoryPosition.x,
                territory.territoryPosition.y,
                territoryComponent.getPreferredSize().width,
                territoryComponent.getPreferredSize().height);
    }

    private void handleTerritoryClicked() {
        System.out.println("Territory clicked: " + territory.name);
    }

    private void handleTerritoryEnter() {
        super.notifySubscribers(territory);
    }
}
