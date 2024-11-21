import java.util.*;

public class Jarnik {
    private Graph graph;
    private int dayCounter = 1;
    private int totalDays = 0;
    private int totalKms = 0;
    private final Set<String> alreadyVisitedNodes = new HashSet<>();

    public Jarnik(Graph graph) {
        this.graph = graph;
    }

    public void performJarnikMST() {
        List<Edge> mst = new ArrayList<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getCost));
        Set<Node> alreadyVisitedNodes = new HashSet<>();

        Node startNode = graph.getNodes().iterator().next();
        alreadyVisitedNodes.add(startNode);

        for (Edge edge : graph.getEdges()) {
            if (edge.getFrom().equals(startNode) || edge.getTo().equals(startNode)) {
                pq.add(edge);
            }
        }

        while (!pq.isEmpty() && mst.size() < graph.getNodes().size() - 1) {
            Edge edge = pq.poll();

            Node from = edge.getFrom();
            Node to = edge.getTo();

            if (!alreadyVisitedNodes.contains(from) || !alreadyVisitedNodes.contains(to)) {
                mst.add(edge);

                Node nextNode = alreadyVisitedNodes.contains(from) ? to : from;
                alreadyVisitedNodes.add(nextNode);

                for (Edge neighborEdge : graph.getEdges()) {
                    if ((neighborEdge.getFrom().equals(nextNode) && !alreadyVisitedNodes.contains(neighborEdge.getTo())) ||
                            (neighborEdge.getTo().equals(nextNode) && !alreadyVisitedNodes.contains(neighborEdge.getFrom()))) {
                        pq.add(neighborEdge);
                    }
                }
            }
        }

        for (Edge edge : mst) {
            int dailyHours = 8;
            int travelTime = 0;

            if (!this.alreadyVisitedNodes.contains(edge.getFrom().getName()) && !this.alreadyVisitedNodes.contains(edge.getTo().getName())) {
                travelTime = 1;
            }

            int remainingWork = edge.getCost();
            int totalWorkedTime = remainingWork + travelTime;

            while (totalWorkedTime > 0) {
                int hoursWorked = Math.min(totalWorkedTime, dailyHours);
                totalWorkedTime -= hoursWorked;

                int doneKms = Math.min(remainingWork, hoursWorked);

                if (travelTime > 0) {
                    travelTime = 0;
                    if (remainingWork > 8) {
                        doneKms -= 1;
                    }
                }

                totalKms += doneKms;
                remainingWork -= doneKms;

                System.out.println("[Day " + dayCounter + "] " + edge.getFrom().getName() + " -> "
                        + edge.getTo().getName() + ": " + hoursWorked + " hours, " + doneKms + " km");

                dayCounter++;
                totalDays++;
            }
            this.alreadyVisitedNodes.add(edge.getFrom().getName());
            this.alreadyVisitedNodes.add(edge.getTo().getName());
        }

        System.out.println("-------------------------------------");
        System.out.println("Result of Jarnik’s algorithm: " + totalDays + " days, " + totalKms + " km");
        System.out.println("");
        System.out.println("");
    }
}