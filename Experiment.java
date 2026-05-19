import java.util.*;

/**
 * Handles graph traversal experiments and performance analysis.
 */
public class Experiment {

    /** Stores results: graph label -> {bfsNs, dfsNs} */
    private Map<String, long[]> results = new LinkedHashMap<>();

    /**
     * Runs BFS and DFS on the given graph, measures time in nanoseconds,
     * and stores the results under a label derived from the graph size.
     *
     * @param g     the graph to traverse
     * @param label a short description (e.g. "Small (10 vertices)")
     */
    public void runTraversals(Graph g, String label) {
        int startVertex = g.getVertexIds().iterator().next(); // pick any starting vertex

        // --- BFS timing ---
        long bfsStart = System.nanoTime();
        g.bfs(startVertex);
        long bfsEnd = System.nanoTime();

        // --- DFS timing ---
        long dfsStart = System.nanoTime();
        g.dfs(startVertex);
        long dfsEnd = System.nanoTime();

        long bfsTime = bfsEnd - bfsStart;
        long dfsTime = dfsEnd - dfsStart;

        results.put(label, new long[]{bfsTime, dfsTime});
    }

    /**
     * Builds three test graphs (small / medium / large) with random edges,
     * runs traversals on each, and stores timing results.
     */
    public void runMultipleTests() {
        int[] sizes = {10, 30, 100};
        String[] labels = {"Small (10 vertices)", "Medium (30 vertices)", "Large (100 vertices)"};

        for (int i = 0; i < sizes.length; i++) {
            Graph g = buildRandomGraph(sizes[i]);
            System.out.println("\n=== " + labels[i] + " ===");

            // Print full graph structure only for the small graph
            if (sizes[i] == 10) {
                g.printGraph();
            }

            runTraversals(g, labels[i]);
        }
    }

    /**
     * Prints a formatted table of BFS vs DFS execution times.
     */
    public void printResults() {
        System.out.println("\n========================================");
        System.out.println("       Performance Results (ns)         ");
        System.out.println("========================================");
        System.out.printf("%-22s %12s %12s%n", "Graph Size", "BFS (ns)", "DFS (ns)");
        System.out.println("----------------------------------------");

        for (Map.Entry<String, long[]> entry : results.entrySet()) {
            long[] times = entry.getValue();
            System.out.printf("%-22s %12d %12d%n", entry.getKey(), times[0], times[1]);
        }
        System.out.println("========================================");
    }

    // ------------------------------------------------------------------ //
    //  Private helper                                                     //
    // ------------------------------------------------------------------ //

    /**
     * Creates a random directed graph with the given number of vertices.
     * Each vertex gets approximately 2–4 outgoing edges to random neighbours.
     */
    private Graph buildRandomGraph(int vertexCount) {
        Graph g = new Graph();
        Random rng = new Random(42); // fixed seed for reproducibility

        // Add all vertices first
        for (int i = 0; i < vertexCount; i++) {
            g.addVertex(new Vertex(i));
        }

        // Add random directed edges (avoid self-loops)
        int edgesPerVertex = Math.max(2, vertexCount / 5);
        for (int i = 0; i < vertexCount; i++) {
            Set<Integer> targets = new HashSet<>();
            while (targets.size() < edgesPerVertex) {
                int target = rng.nextInt(vertexCount);
                if (target != i) targets.add(target);
            }
            for (int target : targets) {
                g.addEdge(i, target);
            }
        }
        return g;
    }
}
