package app.domain.services;

import app.domain.models.GameMap.Territory;

public class TerritoryGraph extends BaseGraph<Territory> {
    public TerritoryGraph() {
        super(7);
    }
}
