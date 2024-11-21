import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Kruskal {
    private Graph graph;
    private Map<String, String> parent;
    private Map<String, Integer> rank;

    public Kruskal(Graph graph) {
        this.graph = graph;
        this.parent = new HashMap<>();
        this.rank = new HashMap<>();
        for (String node : graph.getEdges().stream().flatMap(e -> Stream.of(e.getFrom().getName(), e.getTo().getName())).collect(Collectors.toSet())) {
            parent.put(node, node);
            rank.put(node, 0);
        }
    }

    private String find(String node) {
        if (!parent.get(node).equals(node)) {
            parent.put(node, find(parent.get(node)));
        }
        return parent.get(node);
    }

    private void union(String node1, String node2) {
        String root1 = find(node1);
        String root2 = find(node2);

        if (!root1.equals(root2)) {
            if (rank.get(root1) > rank.get(root2)) {
                parent.put(root2, root1);
            } else if (rank.get(root1) < rank.get(root2)) {
                parent.put(root1, root2);
            } else {
                parent.put(root2, root1);
                rank.put(root1, rank.get(root1) + 1);
            }
        }
    }

    public void findMST() {
        List<Edge> edges = graph.getEdges();
        edges.sort(Comparator.comparingInt(Edge::getCost));

        List<Edge> mst = new ArrayList<>();
        int totalCost = 0;
        int totalDays = 0;
        int hoursWorked = 0;
        int kmBuilt = 0;

        for (Edge edge : edges) {
            String from = edge.getFrom().getName();
            String to = edge.getTo().getName();
            int cost = edge.getCost();

            if (!find(from).equals(find(to))) {
                union(from, to);
                mst.add(edge);
                totalCost += cost;

                // Add an extra hour for connecting non-adjacent cities
                if (!find(from).equals(find(to))) {
                    cost += 1;
                }

                while (cost > 0) {
                    int workHours = Math.min(cost, 8);
                    cost -= workHours;
                    hoursWorked += workHours;
                    kmBuilt += workHours;

                    if (hoursWorked == 8) {
                        totalDays++;
                        System.out.println("[Day " + totalDays + "] " + from + " -> " + to + ": " + hoursWorked + " hours, " + kmBuilt + " km");
                        hoursWorked = 0;
                        kmBuilt = 0;
                    }
                }

                if (hoursWorked > 0) {
                    totalDays++;
                    System.out.println("[Day " + totalDays + "] " + from + " -> " + to + ": " + hoursWorked + " hours, " + kmBuilt + " km");
                    hoursWorked = 0;
                    kmBuilt = 0;
                }
            }
        }
        System.out.println("Result of Kruskal’s algorithm: " + totalDays + " days, " + totalCost + " km");
        System.out.println("-------------------------------------");
        System.out.println("");
    }
}