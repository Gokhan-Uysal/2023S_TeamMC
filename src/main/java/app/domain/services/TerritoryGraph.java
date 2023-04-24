package app.domain.services;

import app.domain.models.GameMap.Territory;
import app.domain.services.base.BaseGraph;

public class TerritoryGraph extends BaseGraph<Territory> {
    public TerritoryGraph() {
        super(7);
    }
}
