package mst;

import java.util.*;

public class Prim {
    public static MSTResult run(Graph.InputGraph g) {
        MSTResult res = new MSTResult();
        res.verticesCount = g.nodes.size();
        res.edgesCount = g.edges.size();

        Map<String, List<Edge>> adj = Graph.buildAdjacency(g);

        int runs = 100;
        long totalNano = 0;

        for (int r = 0; r < runs; r++) {
            Set<String> visited = new HashSet<>();
            PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
            List<Edge> mst = new ArrayList<>();
            int totalCost = 0;

            if (g.nodes.isEmpty()) continue;
            String start = g.nodes.get(0);
            visited.add(start);
            pq.addAll(adj.get(start));

            long startTime = System.nanoTime();

            while (!pq.isEmpty() && mst.size() < g.nodes.size() - 1) {
                Edge e = pq.poll();
                if (visited.contains(e.to)) continue;
                visited.add(e.to);
                mst.add(e);
                totalCost += e.weight;
                for (Edge ne : adj.get(e.to)) {
                    if (!visited.contains(ne.to)) pq.add(ne);
                }
            }

            long endTime = System.nanoTime();
            totalNano += (endTime - startTime);

            res.mstEdges = mst;
            res.totalCost = totalCost;
        }

        res.timeMs = totalNano / (runs * 1_000_000.0);
        return res;
    }
}
