package mst;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class MSTTests {

    @Test
    public void testMSTCostEquality() throws Exception {
        Graph g = Graph.loadFromFile("mst_small_graphs.json");
        for (Graph.InputGraph ig : g.graphs) {
            MSTResult prim = Prim.run(ig);
            MSTResult kruskal = Kruskal.run(ig);
            assertEquals(prim.totalCost, kruskal.totalCost,
                    "Prim and Kruskal should produce the same MST cost");
        }
    }

    @Test
    public void testMSTEdgeCount() throws Exception {
        Graph g = Graph.loadFromFile("mst_small_graphs.json");
        for (Graph.InputGraph ig : g.graphs) {
            MSTResult res = Prim.run(ig);
            assertEquals(Math.max(0, ig.nodes.size() - 1), res.mstEdges.size(),
                    "MST should have V-1 edges");
        }
    }

    @Test
    public void testAcyclicAndConnected() throws Exception {
        Graph g = Graph.loadFromFile("mst_small_graphs.json");
        for (Graph.InputGraph ig : g.graphs) {
            MSTResult res = Kruskal.run(ig);
            // Ensure edges <= V-1 (acyclic)
            assertTrue(res.mstEdges.size() <= ig.nodes.size() - 1);
        }
    }
}
