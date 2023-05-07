package app.domain.services.map;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import app.common.errors.GraphError;
import app.domain.models.game.map.Territory;
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
            Set<String> adjList = territory.get_adjList();
            adjList.forEach((String adj) -> {
                addEdge(territory, adj);
            });
        });
    }

    public Territory getVertex(String territoryName) {
        Set<Territory> keySet = this.graph.keySet();
        for (Territory territory : keySet) {
            if (territory.getName().equals(territoryName)) {
                return territory;
            }
        }
        System.out.println(territoryName);
        throw new GraphError("Territory vertex not found");
    }

    public Set<Territory> getEdges(Territory territory) {
        return super.getEdges(territory);
    }

    public void addEdge(Territory sourceTerritory, String destinationName) {
        Territory destination = getVertex(destinationName);
        super.addEdge(sourceTerritory, destination);
    }

    public boolean validateMap() {
        Set<Territory> visited = new HashSet<>();
        Queue<Territory> queue = new LinkedList<>();
        int edgeCount = 0;
        int removedEdgeCount = 0;

        Territory startVertex = graph.keySet().iterator().next();
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            Territory current = queue.poll();

            if (!visited.contains(current)) {
                visited.add(current);

                for (Territory neighbor : graph.get(current)) {
                    if (!visited.contains(neighbor)) {
                        if (!neighbor.getIsOpen()) {
                            removedEdgeCount += getEdgeCount(neighbor);
                            continue;
                        }
                        queue.add(neighbor);
                    }
                    edgeCount++;
                }
            }
        }

        if ((edgeCount / 2 + removedEdgeCount / 4) != getEdgeCount()) {
            return false;
        }

        return true;
    }

    public void removeClosedTerritories() {
        getVerticies().forEach((Territory territory) -> {
            if (!territory.getIsOpen()) {
                removeVertex(territory);
            }
        });
    }

    public void openAllTerritories() {
        getVerticies().forEach((Territory territory) -> {
            territory.setIsOpen(true);
        });
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
