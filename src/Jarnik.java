import java.util.*;

class Jarnik {
    private Graph graph;

    public Jarnik(Graph graph) {
        this.graph = graph;
    }

    public void findMST() {
        Set<String> visited = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getCost));
        List<Edge> mst = new ArrayList<>();
        int totalCost = 0;
        int totalDays = 0;
        int hoursWorked = 0;
        int kmBuilt = 0;

        String startNode = graph.getNodes().iterator().next().getName();
        visited.add(startNode);
        pq.addAll(getEdges(startNode));

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            String from = edge.getFrom().getName();
            String to = edge.getTo().getName();
            int cost = edge.getCost();

            if (visited.contains(from) && visited.contains(to)) {
                continue;
            }

            mst.add(edge);
            totalCost += cost;

            // Add an extra hour for connecting non-adjacent cities
            if (!visited.contains(from) && !visited.contains(to)) {
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

            String nextNode = visited.contains(from) ? to : from;
            visited.add(nextNode);
            pq.addAll(getEdges(nextNode));
        }
        System.out.println("Result of Jarnik’s algorithm: " + totalDays + " days, " + totalCost + " km");
        System.out.println("-------------------------------------");
        System.out.println("");
    }

    private List<Edge> getEdges(String node) {
        List<Edge> edges = new ArrayList<>();
        for (Edge edge : graph.getEdges()) {
            if (edge.getFrom().getName().equals(node) || edge.getTo().getName().equals(node)) {
                edges.add(edge);
            }
        }
        return edges;
    }
}