public class Main {
    public static void main(String[] args) {
        // TODO: add comaprision between the algoirthms at the end
        Graph graph = new Graph();

        Node lordaeron = new Node("Lordaeron");
        Node tristram = new Node("Tristram");
        Node arraken = new Node("Arraken");
        Node lannisport = new Node("Lannisport");
        Node ankhMorpork = new Node("Ankh Morpork");
        Node gondolin = new Node("Gondolin");
        Node mosEisley = new Node("Mos Eisley");
        Node lv426 = new Node("LV 426");
        Node godricsHollow = new Node("Godric’s Hollow");
        Node mordheim = new Node("Mordheim");
        Node minasTirith = new Node("Minas Tirith");
        Node rivie = new Node("Rivie");
        Node solitude = new Node("Solitude");

        graph.addNode(lordaeron);
        graph.addNode(tristram);
        graph.addNode(arraken);
        graph.addNode(lannisport);
        graph.addNode(ankhMorpork);
        graph.addNode(gondolin);
        graph.addNode(mosEisley);
        graph.addNode(lv426);
        graph.addNode(godricsHollow);
        graph.addNode(mordheim);
        graph.addNode(minasTirith);
        graph.addNode(rivie);
        graph.addNode(solitude);

        graph.addEdge(lordaeron, tristram, 2);
        graph.addEdge(lordaeron, arraken, 9);
        graph.addEdge(arraken, tristram, 7);
        graph.addEdge(arraken, lannisport, 16);
        graph.addEdge(lannisport, ankhMorpork, 10);
        graph.addEdge(ankhMorpork, gondolin, 5);
        graph.addEdge(gondolin, mosEisley, 20);
        graph.addEdge(gondolin, godricsHollow, 15);
        graph.addEdge(gondolin, mordheim, 5);
        graph.addEdge(gondolin, minasTirith, 9);
        graph.addEdge(minasTirith, tristram, 4);
        graph.addEdge(ankhMorpork, mosEisley, 4);
        graph.addEdge(mosEisley, lv426, 7);
        graph.addEdge(lv426, godricsHollow, 3);
        graph.addEdge(godricsHollow, mordheim, 1);
        graph.addEdge(minasTirith, mordheim, 8);
        graph.addEdge(minasTirith, rivie, 3);
        graph.addEdge(tristram, rivie, 12);
        graph.addEdge(rivie, solitude, 8);
        graph.addEdge(arraken, ankhMorpork, 24);

        Boruvka boruvka = new Boruvka(graph);
        boruvka.performBoruvkaMST();

        Jarnik jarnik = new Jarnik(graph);
        jarnik.performJarnikMST();

        Kruskal kruskal = new Kruskal(graph);
        kruskal.performKruskalMST();

        ResultComparision resultComparision = new ResultComparision();
        resultComparision.setBoruvkaResult(boruvka.getTotalDays(), boruvka.getTotalKms());
        resultComparision.setJarnikResult(jarnik.getTotalDays(), jarnik.getTotalKms());
        resultComparision.setKruskalResult(kruskal.getTotalDays(), kruskal.getTotalKms());


        resultComparision.printComparisionResults();
    }
}