package app.domain.services;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import app.common.GraphError;
import app.domain.models.GameMap.Territory;

public class TerritoryGraph {
    private Map<Territory, List<Territory>> graph;

    public TerritoryGraph() {
        this.graph = new HashMap<Territory, List<Territory>>(7);
    }

    public void validateEdges(Territory source, Territory destination) {
        if (source.equals(destination)) {
            throw new GraphError("Source and destination territory could not be same!");
        }

        if (!graph.containsKey(source)) {
            throw new GraphError("Source territory not found!");
        }

        if (!graph.containsKey(destination)) {
            throw new GraphError("Destionation territory not found!");
        }
    }

    public void addEdge(Territory source, Territory destination) {
        validateEdges(source, destination);

        List<Territory> srcAdjecentList = graph.get(source);
        srcAdjecentList.add(destination);

        List<Territory> distAdjecentList = graph.get(destination);
        distAdjecentList.add(source);

    }

    public void removeEdge(Territory source, Territory destination) {
        validateEdges(source, destination);

        List<Territory> srcAdjecentList = graph.get(source);
        srcAdjecentList.remove(destination);

        List<Territory> distAdjecentList = graph.get(destination);
        distAdjecentList.remove(source);
    }

    public void addVertex(Territory territory) {
        this.graph.put(territory, new ArrayList<Territory>());
    }

    public void addVertex(Territory territory, List<Territory> adjacencyList) {
        this.graph.put(territory, adjacencyList);
    }

    public int getEdgeCount() {
        int count = 0;
        for (List<Territory> list : this.graph.values()) {
            count += list.size();
        }
        return count / 2;
    }

    public int getVertexCount() {
        return this.graph.size();
    }
}
