package app.domain.services;

import app.domain.models.GameMap.Territory;
import app.domain.services.base.BaseGraph;

public class MapGraphService extends BaseGraph<Territory> {
    public MapGraphService() {
        super(7);
    }
}
