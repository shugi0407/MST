package mst;

import java.util.*;

public class Kruskal {
    public static MSTResult run(Graph.InputGraph g) {
        MSTResult res = new MSTResult();
        res.verticesCount = g.nodes.size();
        res.edgesCount = g.edges.size();

        List<Edge> edges = Graph.buildEdgeList(g);
        edges.sort(Comparator.naturalOrder());

        UnionFind uf = new UnionFind(g.nodes);

        // Repeat multiple times for more accurate timing
        int runs = 100;
        long totalNano = 0;

        for (int r = 0; r < runs; r++) {
            uf = new UnionFind(g.nodes);
            long start = System.nanoTime();

            int added = 0;
            int totalCost = 0;
            List<Edge> mst = new ArrayList<>();

            for (Edge e : edges) {
                String ra = uf.find(e.from);
                String rb = uf.find(e.to);
                if (!ra.equals(rb)) {
                    if (uf.union(ra, rb)) {
                        mst.add(e);
                        totalCost += e.weight;
                        added++;
                        if (added == g.nodes.size() - 1) break;
                    }
                }
            }

            long end = System.nanoTime();
            totalNano += (end - start);

            // Save result from last iteration
            res.mstEdges = mst;
            res.totalCost = totalCost;
        }

        // Average execution time
        res.timeMs = totalNano / (runs * 1_000_000.0);
        return res;
    }
}
