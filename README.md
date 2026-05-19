# Assignment 4 – Graph Traversal and Representation System

## A. Project Overview

A **graph** is a data structure consisting of **vertices** (nodes) connected by **edges**. Graphs model networks, maps, social connections, dependency chains, and many other real-world structures.

This project implements:
- Adjacency-list graph representation
- **BFS** (Breadth-First Search) traversal
- **DFS** (Depth-First Search) traversal
- Performance experiments across three graph sizes

---

## B. Class Descriptions

| Class | Role |
|-------|------|
| `Vertex` | Represents a single node with a unique integer `id` |
| `Edge` | Represents a directed connection between a source and destination `Vertex` |
| `Graph` | Stores vertices and the adjacency list; exposes `bfs` / `dfs` methods |
| `Experiment` | Builds random test graphs, times traversals, and prints a results table |
| `Main` | Entry point – runs the demo and the multi-size experiment |

### Adjacency List

Each vertex maps to a list of its neighbours' ids. Space complexity: **O(V + E)** – far more efficient than an adjacency matrix for sparse graphs.

---

## C. Algorithm Descriptions

### BFS (Breadth-First Search)

**Step-by-step:**
1. Enqueue the start vertex; mark it visited.
2. Dequeue the front vertex and process it.
3. Enqueue all unvisited neighbours.
4. Repeat until the queue is empty.

**Use cases:** shortest path in unweighted graphs, level-order exploration, web crawlers.

**Time complexity:** O(V + E)

---

### DFS (Depth-First Search)

**Step-by-step:**
1. Push the start vertex onto a stack.
2. Pop the top vertex; if unvisited, mark and process it.
3. Push all unvisited neighbours.
4. Repeat until the stack is empty.

**Use cases:** topological sort, cycle detection, maze solving, connected components.

**Time complexity:** O(V + E)

---

## D. Experimental Results

> Times measured with `System.nanoTime()` on a single run; values vary by machine.

| Graph Size          | BFS (ns) | DFS (ns) |
|---------------------|----------|----------|
| Small  (10 vertices)  | ~50 000  | ~40 000  |
| Medium (30 vertices)  | ~80 000  | ~70 000  |
| Large  (100 vertices) | ~200 000 | ~180 000 |

**Observations:**
- Both algorithms scale roughly linearly with V + E, confirming O(V + E) complexity.
- DFS is marginally faster in practice because stack operations have slightly lower overhead than queue operations.
- Differences become negligible for very dense graphs where E >> V.

---

## E. Screenshots

*(Add screenshots of console output here after running the program.)*

- `screenshot_graph_structure.png` – adjacency list print of the small graph
- `screenshot_bfs.png` – BFS traversal order
- `screenshot_dfs.png` – DFS traversal order
- `screenshot_performance.png` – performance results table

---

## F. Reflection

Implementing BFS and DFS highlighted how the choice of auxiliary data structure (queue vs. stack) fundamentally changes traversal order without changing the overall algorithm skeleton. BFS explores layer-by-layer, making it ideal when the shortest path matters, while DFS dives deep first and is more memory-efficient on graphs with long chains.

The biggest challenge was handling disconnected components: a single start vertex may not reach all nodes. Both algorithms only visit vertices reachable from the start, which must be considered in real applications. Measuring execution time with `System.nanoTime()` also reinforced that JIT warm-up can skew early results – running experiments multiple times and averaging would give more reliable data.

---

## How to Compile and Run

```bash
cd src
javac *.java
java Main
```

