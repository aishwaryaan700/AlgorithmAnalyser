package algorithms;

import java.util.*;

public class DFS implements GraphAlgorithm {

    private void dfsUtil(int node, Map<Integer, List<Integer>> graph, Set<Integer> visited) {
        visited.add(node);
        System.out.print(node + " ");

        for (int neighbor : graph.getOrDefault(node, Collections.emptyList())) {
            if (!visited.contains(neighbor)) {
                dfsUtil(neighbor, graph, visited);
            }
        }
    }

    @Override
    public void traverse(Map<Integer, List<Integer>> graph, int startNode) {
        Set<Integer> visited = new HashSet<>();
        System.out.println("DFS Traversal starting from node " + startNode + ":");
        dfsUtil(startNode, graph, visited);
        System.out.println();
    }
}
