package app.common.errors;

public class GraphError extends Error {
    public String title;

    public GraphError(String title, String message) {
        super(message);
        this.title = title;
    }

    public GraphError(String message) {
        super(message);
        this.title = "Graph Error";
    }
}
