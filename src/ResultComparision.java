public class ResultComparision {
    private int boruvkaDays;
    private int boruvkaKms;
    private int jarnikDays;
    private int jarnikKms;
    private int kruskalDays;
    private int kruskalKms;

    public void setBoruvkaResult(int days, int kms) {
        this.boruvkaDays = days;
        this.boruvkaKms = kms;
    }

    public void setJarnikResult(int days, int kms) {
        this.jarnikDays = days;
        this.jarnikKms = kms;
    }

    public void setKruskalResult(int days, int kms) {
        this.kruskalDays = days;
        this.kruskalKms = kms;
    }

    public void printComparisionResults() {
        System.out.println("");
        System.out.println("____________________________________________________");
        System.out.println("Comparision of all MST algorithms:");
        System.out.println("Boruvka's algorithm: " + boruvkaDays + " days, " + boruvkaKms + " km");
        System.out.println("Jarnik's algorithm: " + jarnikDays + " days, " + jarnikKms + " km");
        System.out.println("Kruskal's algorithm: " + kruskalDays + " days, " + kruskalKms + " km");
    }

}
