import java.util.*;

/**
 * Represents a graph using an adjacency list.
 * Supports directed edges, BFS and DFS traversals.
 */
public class Graph {

    // Maps each vertex id -> Vertex object
    private Map<Integer, Vertex> vertices;

    // Adjacency list: vertex id -> list of neighbour vertex ids
    private Map<Integer, List<Integer>> adjacencyList;

    public Graph() {
        vertices = new HashMap<>();
        adjacencyList = new HashMap<>();
    }

    /**
     * Adds a vertex to the graph.
     * If a vertex with the same id already exists, it is ignored.
     */
    public void addVertex(Vertex v) {
        if (!vertices.containsKey(v.getId())) {
            vertices.put(v.getId(), v);
            adjacencyList.put(v.getId(), new ArrayList<>());
        }
    }

    /**
     * Adds a directed edge from vertex 'from' to vertex 'to'.
     * Both vertices must already exist in the graph.
     */
    public void addEdge(int from, int to) {
        if (!vertices.containsKey(from) || !vertices.containsKey(to)) {
            throw new IllegalArgumentException(
                    "Both vertices must exist before adding an edge. Missing: "
                    + (!vertices.containsKey(from) ? from : to));
        }
        adjacencyList.get(from).add(to);
    }

    /**
     * Prints the adjacency list of the graph.
     */
    public void printGraph() {
        System.out.println("Graph (adjacency list):");
        for (int id : getSortedIds()) {
            System.out.println("  " + id + " -> " + adjacencyList.get(id));
        }
    }

    // ------------------------------------------------------------------ //
    //  Traversal algorithms                                               //
    // ------------------------------------------------------------------ //

    /**
     * Breadth-First Search starting from vertex 'start'.
     *
     * How it works:
     *   1. Enqueue the start vertex and mark it as visited.
     *   2. Dequeue a vertex, print it, then enqueue all unvisited neighbours.
     *   3. Repeat until the queue is empty.
     *
     * Time complexity: O(V + E)
     */
    public void bfs(int start) {
        if (!vertices.containsKey(start)) return;

        Set<Integer> visited = new LinkedHashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        // Step 1 – initialise
        queue.offer(start);
        visited.add(start);

        System.out.print("BFS from " + start + ": ");

        while (!queue.isEmpty()) {
            // Step 2 – process front of queue
            int current = queue.poll();
            System.out.print(current + " ");

            // Step 3 – enqueue unvisited neighbours in sorted order for reproducibility
            List<Integer> neighbours = new ArrayList<>(adjacencyList.get(current));
            Collections.sort(neighbours);
            for (int neighbour : neighbours) {
                if (!visited.contains(neighbour)) {
                    visited.add(neighbour);
                    queue.offer(neighbour);
                }
            }
        }
        System.out.println();
    }

    /**
     * Depth-First Search starting from vertex 'start'.
     *
     * How it works:
     *   1. Push the start vertex onto a stack and mark it as visited.
     *   2. Pop a vertex, print it, then push all unvisited neighbours.
     *   3. Repeat until the stack is empty.
     *
     * Time complexity: O(V + E)
     */
    public void dfs(int start) {
        if (!vertices.containsKey(start)) return;

        Set<Integer> visited = new LinkedHashSet<>();
        Deque<Integer> stack = new ArrayDeque<>();

        // Step 1 – initialise
        stack.push(start);

        System.out.print("DFS from " + start + ": ");

        while (!stack.isEmpty()) {
            // Step 2 – process top of stack
            int current = stack.pop();
            if (visited.contains(current)) continue; // already processed via another path
            visited.add(current);
            System.out.print(current + " ");

            // Step 3 – push unvisited neighbours (reversed so smallest id is explored first)
            List<Integer> neighbours = new ArrayList<>(adjacencyList.get(current));
            Collections.sort(neighbours, Collections.reverseOrder());
            for (int neighbour : neighbours) {
                if (!visited.contains(neighbour)) {
                    stack.push(neighbour);
                }
            }
        }
        System.out.println();
    }

    // ------------------------------------------------------------------ //
    //  Helpers                                                            //
    // ------------------------------------------------------------------ //

    public int size() {
        return vertices.size();
    }

    public Set<Integer> getVertexIds() {
        return vertices.keySet();
    }

    private List<Integer> getSortedIds() {
        List<Integer> ids = new ArrayList<>(vertices.keySet());
        Collections.sort(ids);
        return ids;
    }
}
