package app.domain.services;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import app.common.GraphError;

public class BaseGraph<Key> {
    private Map<Key, List<Key>> graph;

    protected BaseGraph(int capacity) {
        this.graph = new HashMap<Key, List<Key>>(capacity);
    }

    // Graph validations
    public void validateEdges(Key source, Key destination) {
        if (source.equals(destination)) {
            throw new GraphError("Source and destination edges could not be the same!");
        }

        if (!graph.containsKey(source)) {
            throw new GraphError("Source edge not found!");
        }

        if (!graph.containsKey(destination)) {
            throw new GraphError("Destionation edge not found!");
        }
    }

    public void validateVertex(Key Key) {
        if (!graph.containsKey(Key)) {
            throw new GraphError("Vertex not found!");
        }
    }

    // Edge operations
    public void addEdge(Key source, Key destination) throws GraphError {
        validateEdges(source, destination);

        List<Key> srcAdjecentList = graph.get(source);
        srcAdjecentList.add(destination);

        List<Key> distAdjecentList = graph.get(destination);
        distAdjecentList.add(source);

    }

    public void removeEdge(Key source, Key destination) throws GraphError {
        validateEdges(source, destination);

        List<Key> srcAdjecentList = graph.get(source);
        srcAdjecentList.remove(destination);

        List<Key> distAdjecentList = graph.get(destination);
        distAdjecentList.remove(source);
    }

    // Vertex operations
    public void addVertex(Key Key) {
        this.graph.put(Key, new ArrayList<Key>());
    }

    public void addVertex(Key Key, List<Key> adjacencyList) {
        this.graph.put(Key, adjacencyList);
    }

    public void removeVertex(Key Key) throws GraphError {
        validateVertex(Key);

        for (List<Key> list : this.graph.values()) {
            list.remove(Key);
        }

        this.graph.remove(Key);
    }

    // Graph operations
    public void loadGraphFromMatrix(List<Key> keys, Integer[][] adjMatrix) {

    }

    public int getEdgeCount() {
        int count = 0;
        for (List<Key> list : this.graph.values()) {
            count += list.size();
        }
        return count / 2;
    }

    public int getVertexCount() {
        return this.graph.size();
    }
}
