package app.domain.services.Map;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import app.common.GraphError;
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

    public void addEdges(List<Territory> territoryListFromReadService) {
        territoryListFromReadService.forEach((Territory territory) -> {
            Set<String> adjList = territory.adjList;
            adjList.forEach((String adj) -> {
                addEdge(territory, adj);
            });
        });
        System.out.println(graph.entrySet());
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

    public void addEdge(Territory sourceTerritory, String destinationName) {
        Territory destination = getVertex(destinationName);
        super.addEdge(sourceTerritory, destination);
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
