import java.util.*;

public class Kruskal {
    private Graph graph;
    private int dayCounter = 1;
    private int totalDays = 0;
    private int totalKms = 0;
    private final Set<String> alreadyVisitedNodes = new HashSet<>();

    public Kruskal(Graph graph) {
        this.graph = graph;
    }

    public void performKruskalMST() {
        List<Edge> mst = new ArrayList<>();
        List<Edge> edges = new ArrayList<>(graph.getEdges());
        edges.sort(Comparator.comparingInt(Edge::getCost));
        UnionFind unionFind = new UnionFind();

        for (Node node : graph.getNodes()) {
            unionFind.createNewSet(node);
        }

        for (Edge edge : edges) {
            Node rootFrom = unionFind.findRootNode(edge.getFrom());
            Node rootTo = unionFind.findRootNode(edge.getTo());

            if (!rootFrom.equals(rootTo)) {
                mst.add(edge);
                unionFind.mergeNodeSets(edge.getFrom(), edge.getTo());
            }
        }

        for (Edge edge : mst) {
            int dailyHours = 8;
            int travelTime = 0;

            if (!alreadyVisitedNodes.contains(edge.getFrom().getName()) && !alreadyVisitedNodes.contains(edge.getTo().getName())) {
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
            alreadyVisitedNodes.add(edge.getFrom().getName());
            alreadyVisitedNodes.add(edge.getTo().getName());
        }

        System.out.println("-------------------------------------");
        System.out.println("Result of Kruskal’s algorithm: " + totalDays + " days, " + totalKms + " km");
        System.out.println("");
        System.out.println("");
    }

    public int getTotalDays() {
        return totalDays;
    }

    public int getTotalKms() {
        return totalKms;
    }
}