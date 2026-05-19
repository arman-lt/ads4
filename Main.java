/**
 * Entry point for the Graph Traversal and Representation System.
 *
 * Creates graphs of three sizes, runs BFS and DFS traversals,
 * measures execution time, and prints a performance summary.
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║   Graph Traversal & Representation System ║");
        System.out.println("╚══════════════════════════════════════════╝\n");

        // ----------------------------------------------------------------
        // Part 1 – Small graph (10 vertices): detailed output
        // ----------------------------------------------------------------
        System.out.println("--- Small Graph (10 vertices) ---");
        Graph small = buildSmallGraph();
        small.printGraph();

        System.out.println();
        long t0 = System.nanoTime();
        small.bfs(0);
        long t1 = System.nanoTime();
        System.out.println("  BFS time: " + (t1 - t0) + " ns");

        long t2 = System.nanoTime();
        small.dfs(0);
        long t3 = System.nanoTime();
        System.out.println("  DFS time: " + (t3 - t2) + " ns");

        // ----------------------------------------------------------------
        // Part 2 – Experiment across all sizes (10, 30, 100 vertices)
        // ----------------------------------------------------------------
        System.out.println("\n--- Multi-size Experiment ---");
        Experiment experiment = new Experiment();
        experiment.runMultipleTests();
        experiment.printResults();
    }

    // ------------------------------------------------------------------ //
    //  Helper: hand-crafted small graph for the detailed demo             //
    // ------------------------------------------------------------------ //

    /**
     * Builds a small undirected-like graph with 10 vertices (0-9)
     * and a set of manually defined edges — good for showing traversal order.
     */
    private static Graph buildSmallGraph() {
        Graph g = new Graph();

        // Add vertices 0 – 9
        for (int i = 0; i < 10; i++) {
            g.addVertex(new Vertex(i));
        }

        // Add edges (undirected simulation: add both directions)
        int[][] edges = {
            {0, 1}, {0, 2},
            {1, 3}, {1, 4},
            {2, 5}, {2, 6},
            {3, 7},
            {4, 7}, {4, 8},
            {5, 9},
            {6, 9},
            {7, 9},
            {8, 9}
        };

        for (int[] e : edges) {
            g.addEdge(e[0], e[1]);
            g.addEdge(e[1], e[0]); // undirected
        }

        return g;
    }
}
