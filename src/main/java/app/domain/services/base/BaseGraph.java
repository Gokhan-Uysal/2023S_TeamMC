package app.domain.services.base;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import app.common.GraphError;

public class BaseGraph<Vertex> {
    private Map<Vertex, List<Vertex>> graph;

    protected BaseGraph(int capacity) {
        this.graph = new HashMap<Vertex, List<Vertex>>(capacity);
    }

    // Graph validations
    private void validateEdges(Vertex source, Vertex destination) {
        if (source.equals(destination)) {
            throw new GraphError("Source and destination edges are the same!");
        }

        if (!graph.containsKey(source)) {
            throw new GraphError("Source edge not found!");
        }

        if (!graph.containsKey(destination)) {
            throw new GraphError("Destionation edge not found!");
        }
    }

    private void validateVertex(Vertex vertex) {
        if (!graph.containsKey(vertex)) {
            throw new GraphError("Vertex not found!");
        }
    }

    private void validateAdjMatrix(Integer[][] adjMatrix) {
        if (adjMatrix[0].length != adjMatrix.length) {
            throw new GraphError("Graph adjacency matrix is not a square matrix!");
        }
    }

    // Edge operations
    public void addEdge(Vertex source, Vertex destination) throws GraphError {
        validateEdges(source, destination);

        List<Vertex> srcAdjecentList = graph.get(source);
        srcAdjecentList.add(destination);

        List<Vertex> distAdjecentList = graph.get(destination);
        distAdjecentList.add(source);

    }

    public void removeEdge(Vertex source, Vertex destination) throws GraphError {
        validateEdges(source, destination);

        List<Vertex> srcAdjecentList = graph.get(source);
        srcAdjecentList.remove(destination);

        List<Vertex> distAdjecentList = graph.get(destination);
        distAdjecentList.remove(source);
    }

    // Vertex operations
    public void addVertex(Vertex vertex) {
        this.graph.put(vertex, new ArrayList<Vertex>());
    }

    public void addVerticies(List<Vertex> verticies) {
        verticies.forEach((vertex) -> addVertex(vertex));
    }

    public void removeVertex(Vertex vertex) throws GraphError {
        validateVertex(vertex);

        for (List<Vertex> list : this.graph.values()) {
            list.remove(vertex);
        }

        this.graph.remove(vertex);
    }

    // Graph operations
    public void loadGraphFromMatrix(List<Vertex> verticies, Integer[][] adjMatrix) throws GraphError {
        validateAdjMatrix(adjMatrix);

        addVerticies(verticies);

        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix[i].length; j++) {
                Boolean isAdjecent = getBoolean(adjMatrix[i][j]);
                if (!isAdjecent) {
                    continue;
                }

                Vertex src = verticies.get(i);
                Vertex dist = verticies.get(j);

                addEdge(src, dist);
            }
        }
    }

    // public void findShortestPath(Vertex source, Vertex destination) {
    // validateEdges(source, destination);

    // List<Integer> path = new ArrayList<>();

    // List<Vertex> sourceEdges = this.graph.get(source);

    // }

    public int getEdgeCount() {
        int count = 0;
        for (List<Vertex> list : this.graph.values()) {
            count += list.size();
        }
        return count / 2;
    }

    public int getVertexCount() {
        return this.graph.size();
    }

    public boolean getBoolean(int value) {
        return (value != 0);
    }

    public void clear() {
        this.graph.clear();
    }
}
