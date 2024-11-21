import java.util.*;

public class Boruvka {
    private Graph graph;
    private int dayCounter = 1;
    private int totalDays = 0;
    private int totalKms = 0;
    private final Set<String> alreadyVisitedNodes = new HashSet<>();

    public Boruvka(Graph graph) {
        this.graph = graph;
    }

    public void performBoruvkaMST() {
        List<Edge> mst = new ArrayList<>();
        UnionFind unionFind = new UnionFind();

        for (Node node : graph.getNodes()) {
            unionFind.createNewSet(node);
        }

        int nodes = graph.getNodes().size();

        while (nodes > 1) {
            Map<Node, Edge> minEdge = new HashMap<>();

            for (Edge edge : graph.getEdges()) {
                Node rootFrom = unionFind.findRootNode(edge.getFrom());
                Node rootTo = unionFind.findRootNode(edge.getTo());

                if (!rootFrom.equals(rootTo)) {
                    if (!minEdge.containsKey(rootFrom) || edge.getCost() < minEdge.get(rootFrom).getCost()) {
                        minEdge.put(rootFrom, edge);
                    }
                    if (!minEdge.containsKey(rootTo) || edge.getCost() < minEdge.get(rootTo).getCost()) {
                        minEdge.put(rootTo, edge);
                    }
                }
            }

            for (Edge edge : minEdge.values()) {
                Node rootFrom = unionFind.findRootNode(edge.getFrom());
                Node rootTo = unionFind.findRootNode(edge.getTo());

                if (!rootFrom.equals(rootTo)) {
                    mst.add(edge);
                    unionFind.mergeNodeSets(edge.getFrom(), edge.getTo());
                    nodes--;
                }
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
                        + edge.getTo().getName() + ": "
                        + hoursWorked + " hours, "
                        + doneKms + " km");

                dayCounter++;
                totalDays++;
            }
            alreadyVisitedNodes.add(edge.getFrom().getName());
            alreadyVisitedNodes.add(edge.getTo().getName());
        }

        System.out.println("-------------------------------------");
        System.out.println("Result of Boruvka’s algorithm: " + totalDays + " days, " + totalKms + " km");
        System.out.println("");
        System.out.println("");
    }
}