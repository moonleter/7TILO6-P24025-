import java.util.*;

public class Graph {
    private List<Node> nodes;
    private List<Edge> edges;

    public Graph() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void addEdge(Node from, Node to, int cost) {
        edges.add(new Edge(from, to, cost));
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public int getNumNodes() {
        return nodes.size();
    }
}