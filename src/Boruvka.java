import java.util.*;

public class Boruvka {
    private Graph graph;

    public Boruvka(Graph graph) {
        this.graph = graph;
    }

    public void findMST() {
        Map<String, String> parent = new HashMap<>();
        Map<String, Integer> rank = new HashMap<>();
        List<Edge> mst = new ArrayList<>();
        int totalCost = 0;
        int totalDays = 0;
        int hoursWorked = 0;
        int kmBuilt = 0;

        for (Node node : graph.getNodes()) {
            parent.put(node.getName(), node.getName());
            rank.put(node.getName(), 0);
        }

        int numComponents = graph.getNumNodes();

        while (numComponents > 1) {
            Map<String, Edge> cheapest = new HashMap<>();

            for (Edge edge : graph.getEdges()) {
                String from = find(parent, edge.getFrom().getName());
                String to = find(parent, edge.getTo().getName());

                if (!from.equals(to)) {
                    if (!cheapest.containsKey(from) || edge.getCost() < cheapest.get(from).getCost()) {
                        cheapest.put(from, edge);
                    }
                    if (!cheapest.containsKey(to) || edge.getCost() < cheapest.get(to).getCost()) {
                        cheapest.put(to, edge);
                    }
                }
            }

            for (Edge edge : cheapest.values()) {
                String from = find(parent, edge.getFrom().getName());
                String to = find(parent, edge.getTo().getName());

                if (!from.equals(to)) {
                    union(parent, rank, from, to);
                    mst.add(edge);
                    totalCost += edge.getCost();

                    int cost = edge.getCost();
                    if (!parent.get(from).equals(parent.get(to))) {
                        cost += 1; // Add an extra hour for connecting non-adjacent cities
                    }

                    while (cost > 0) {
                        int workHours = Math.min(cost, 8);
                        cost -= workHours;
                        hoursWorked += workHours;
                        kmBuilt += workHours;

                        if (hoursWorked == 8) {
                            totalDays++;
                            System.out.println("[Day " + totalDays + "] " + edge.getFrom().getName() + " -> " + edge.getTo().getName() + ": " + hoursWorked + " hours, " + kmBuilt + " km");
                            hoursWorked = 0;
                            kmBuilt = 0;
                        }
                    }

                    if (hoursWorked > 0) {
                        totalDays++;
                        System.out.println("[Day " + totalDays + "] " + edge.getFrom().getName() + " -> " + edge.getTo().getName() + ": " + hoursWorked + " hours, " + kmBuilt + " km");
                        hoursWorked = 0;
                        kmBuilt = 0;
                    }

                    numComponents--;
                }
            }
        }
        System.out.println("Result of Boruvka’s algorithm: " + totalDays + " days, " + totalCost + " km");
        System.out.println("-------------------------------------");
        System.out.println("");
    }

    private String find(Map<String, String> parent, String node) {
        if (!parent.get(node).equals(node)) {
            parent.put(node, find(parent, parent.get(node)));
        }
        return parent.get(node);
    }

    private void union(Map<String, String> parent, Map<String, Integer> rank, String node1, String node2) {
        String root1 = find(parent, node1);
        String root2 = find(parent, node2);

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
}