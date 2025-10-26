package mst;

public class Edge implements Comparable<Edge> {
    public final String from;
    public final String to;
    public final int weight;

    public Edge(String from, String to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.weight, o.weight);
    }

    @Override
    public String toString() {
        return String.format("{\"from\": \"%s\", \"to\": \"%s\", \"weight\": %d}", from, to, weight);
    }
}
