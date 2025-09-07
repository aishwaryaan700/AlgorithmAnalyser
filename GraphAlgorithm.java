package algorithms;

import java.util.Map;
import java.util.List;

public interface GraphAlgorithm {
    // Traverse the graph starting from a node
    void traverse(Map<Integer, List<Integer>> graph, int startNode);
}
