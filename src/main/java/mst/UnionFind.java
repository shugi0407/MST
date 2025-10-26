package mst;

import java.util.HashMap;
import java.util.Map;

public class UnionFind {
    private final Map<String, String> parent = new HashMap<>();
    private final Map<String, Integer> rank = new HashMap<>();

    public long findOperations = 0;
    public long unionOperations = 0;

    public UnionFind(Iterable<String> items) {
        for (String s : items) {
            parent.put(s, s);
            rank.put(s, 0);
        }
    }

    public String find(String x) {
        findOperations++;
        String p = parent.get(x);
        if (!p.equals(x)) {
            String root = find(p);
            parent.put(x, root);
            return root;
        }
        return p;
    }

    public boolean union(String a, String b) {
        unionOperations++;
        String ra = find(a);
        String rb = find(b);
        if (ra.equals(rb)) return false;
        int raRank = rank.get(ra);
        int rbRank = rank.get(rb);
        if (raRank < rbRank) parent.put(ra, rb);
        else if (raRank > rbRank) parent.put(rb, ra);
        else {
            parent.put(rb, ra);
            rank.put(ra, raRank + 1);
        }
        return true;
    }
}
