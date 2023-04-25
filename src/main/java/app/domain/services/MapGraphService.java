package app.domain.services;

import java.util.Set;

import app.common.GraphError;
import app.domain.models.GameMap.Territory;
import app.domain.services.base.BaseGraph;

public class MapGraphService extends BaseGraph<Territory> {
    public MapGraphService() {
        super(7);
    }

    public Territory getVertex(String territoryName) {
        Set<Territory> keySet = this.graph.keySet();
        for (Territory territory : keySet) {
            if (territory.name.equals(territoryName)) {
                return territory;
            }
        }

        throw new GraphError("Territory vertex not found");
    }

    public void addEdge(String sourceName, String destinationName) {
        Territory source = getVertex(sourceName);
        Territory destination = getVertex(destinationName);
        super.addEdge(source, destination);
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
