package mst;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.*;

public class Graph {
    public static class InputEdge {
        public String from;
        public String to;
        public int weight;
    }

    public static class InputGraph {
        public int id;
        public List<String> nodes;
        public List<InputEdge> edges;
    }

    public List<InputGraph> graphs;

    public static Graph loadFromFile(String path) throws Exception {
        Gson g = new Gson();
        try (Reader r = new FileReader(path)) {
            Type t = new TypeToken<Graph>() {}.getType();
            return g.fromJson(r, t);
        }
    }

    public static void writeResults(Object results, String path) throws Exception {
        Gson g = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter w = new FileWriter(path)) {
            g.toJson(results, w);
        }
    }

    public static Map<String, List<Edge>> buildAdjacency(InputGraph g) {
        Map<String, List<Edge>> adj = new HashMap<>();
        for (String v : g.nodes) adj.put(v, new ArrayList<>());
        for (InputEdge e : g.edges) {
            adj.get(e.from).add(new Edge(e.from, e.to, e.weight));
            adj.get(e.to).add(new Edge(e.to, e.from, e.weight));
        }
        return adj;
    }

    public static List<Edge> buildEdgeList(InputGraph g) {
        List<Edge> list = new ArrayList<>();
        Set<String> seen = new HashSet<>();
        for (InputEdge e : g.edges) {
            String key1 = e.from + "-" + e.to;
            String key2 = e.to + "-" + e.from;
            if (seen.contains(key1) || seen.contains(key2)) continue;
            seen.add(key1);
            list.add(new Edge(e.from, e.to, e.weight));
        }
        return list;
    }
}
