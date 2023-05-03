package app.domain.services.base;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import app.common.GraphError;

public class BaseGraph<Vertex> {
    protected Map<Vertex, HashSet<Vertex>> graph;

    protected BaseGraph(int capacity) {
        this.graph = new HashMap<Vertex, HashSet<Vertex>>(capacity);
    }

    // Graph validations
    private void validateEdge(Vertex source, Vertex destination) {
        validateVertex(source);
        validateVertex(destination);

        if (source.equals(destination)) {
            throw new GraphError("Source and destination edges are the same!");
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
        validateEdge(source, destination);

        HashSet<Vertex> srcAdjecentList = graph.get(source);
        srcAdjecentList.add(destination);

        HashSet<Vertex> distAdjecentList = graph.get(destination);
        distAdjecentList.add(source);

    }

    public void removeEdge(Vertex source, Vertex destination) throws GraphError {
        if (source.equals(destination)) {
            throw new GraphError("Source and destination edges are the same!");
        }

        HashSet<Vertex> srcAdjecentList = graph.get(source);
        srcAdjecentList.remove(destination);

        HashSet<Vertex> distAdjecentList = graph.get(destination);
        distAdjecentList.remove(source);
    }

    // Vertex operations
    public void addVertex(Vertex vertex) {
        this.graph.put(vertex, new HashSet<Vertex>());
    }

    public void addVerticies(List<Vertex> verticies) {
        verticies.forEach((vertex) -> addVertex(vertex));
    }

    public void removeVertex(Vertex vertex) throws GraphError {
        validateVertex(vertex);

        for (HashSet<Vertex> list : this.graph.values()) {
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

    public List<Vertex> shortestPath(Vertex source, Vertex destionation) {
        List<Vertex> path = new ArrayList<>();
        try {
            validateVertex(source);
            validateVertex(destionation);
        } catch (Error e) {
            return path;
        }

        Map<Vertex, Vertex> previousVertices = new HashMap<>();
        Map<Vertex, Integer> distances = new HashMap<>();
        PriorityQueue<Vertex> queue = new PriorityQueue<>((v1, v2) -> distances.get(v1) - distances.get(v2));
        Set<Vertex> visited = new HashSet<>();

        for (Vertex vertex : graph.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        distances.put(source, 0);

        queue.add(source);

        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            visited.add(current);

            if (current.equals(destionation)) {
                break;
            }

            for (Vertex neighbor : graph.get(current)) {
                if (visited.contains(neighbor)) {
                    continue;
                }

                int tentativeDistance = distances.get(current) + 1;
                if (tentativeDistance < distances.get(neighbor)) {
                    distances.put(neighbor, tentativeDistance);
                    previousVertices.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        return buildPath(previousVertices, source, destionation);
    }

    private List<Vertex> buildPath(Map<Vertex, Vertex> previousVertices, Vertex source, Vertex target) {
        LinkedList<Vertex> path = new LinkedList<>();

        Vertex current = target;
        while (current != null) {
            path.addFirst(current);
            current = previousVertices.get(current);
        }

        return source.equals(path.getFirst()) ? path : null;
    }

    public int getEdgeCount() {
        int count = 0;
        for (HashSet<Vertex> list : this.graph.values()) {
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
