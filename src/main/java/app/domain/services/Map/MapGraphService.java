package app.domain.services.Map;

import java.util.ArrayList;
import java.util.List;

import app.domain.models.GameMap.Territory;
import app.domain.services.base.BaseGraph;

public class MapGraphService extends BaseGraph<Territory> {
    public MapGraphService() {
        super(7);
    }

    public List<Territory> getVerticies() {
        List<Territory> territoryVerticies = new ArrayList<>();
        super.graph.keySet().forEach((Territory territory) -> {
            territoryVerticies.add(territory);
        });

        return territoryVerticies;
    }

    @Override
    public String toString() {
        String graph = "";
        for (Territory key : this.graph.keySet()) {
            graph += key.toString();
            graph += "\t";
            graph += this.graph.get(key);
            graph += "\n";
        }
        return graph;
    }
}
