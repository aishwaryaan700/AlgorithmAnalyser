package algorithms;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n==== Algorithm Analyzer ====");
            System.out.println("1. Run Sorting Algorithm Analysis");
            System.out.println("2. Run Graph Traversals (BFS/DFS)");
            System.out.println("3. Run Searching Algorithms");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> runSortingAnalysis(sc);
                case 2 -> runGraphAnalysis();
                case 3 -> runSearchingAnalysis(sc);
                case 4 -> {
                    System.out.println("Exiting... Goodbye!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    // ================= Sorting Analysis =================
    private static void runSortingAnalysis(Scanner sc) {
        SortingAlgorithm[] algorithms = {
            new BubbleSort(),
            new QuickSort(),
            new MergeSort(),
            new HeapSort(),
            new InsertionSort(),
            new SelectionSort()
        };

        System.out.println("\nSorting Options:");
        System.out.println("1. Compare execution times (tabular)");
        System.out.println("2. Show actual sorting output (for small arrays)");
        System.out.print("Choose option: ");
        int option = sc.nextInt();

        if (option == 1) {
            int[] sizes = {1000, 5000, 10000};
            Random rand = new Random();

            // Print header row
            System.out.printf("%-20s", "Algorithm");
            for (int size : sizes) {
                System.out.printf("%-15s", "n=" + size);
            }
            System.out.println();

            // Measure time for each algorithm
            for (SortingAlgorithm algo : algorithms) {
                System.out.printf("%-20s", algo.getName());
                for (int size : sizes) {
                    int[] arr = rand.ints(size, 0, 10000).toArray();
                    int[] copy = Arrays.copyOf(arr, arr.length);

                    long start = System.nanoTime();
                    algo.sort(copy);
                    long end = System.nanoTime();

                    System.out.printf("%-15d", (end - start) / 1_000_000); // in ms
                }
                System.out.println();
            }
        } else if (option == 2) {
            System.out.print("Enter array size (recommended ≤ 20): ");
            int n = sc.nextInt();
            int[] arr = new int[n];
            Random rand = new Random();

            for (int i = 0; i < n; i++) arr[i] = rand.nextInt(100);

            System.out.println("\nOriginal Array: " + Arrays.toString(arr));

            for (SortingAlgorithm algo : algorithms) {
                int[] copy = Arrays.copyOf(arr, arr.length);
                algo.sort(copy);
                System.out.println(algo.getName() + " → " + Arrays.toString(copy));
            }
        } else {
            System.out.println("Invalid option!");
        }
    }

    // ================= Graph Traversals =================
    private static void runGraphAnalysis() {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(1, Arrays.asList(2, 3));
        graph.put(2, Arrays.asList(4, 5));
        graph.put(3, Arrays.asList(6));
        graph.put(4, Collections.emptyList());
        graph.put(5, Collections.emptyList());
        graph.put(6, Collections.emptyList());

        GraphAlgorithm bfs = new BFS();
        GraphAlgorithm dfs = new DFS();

        System.out.println("\nGraph Traversals:");
        System.out.print("→ ");
        bfs.traverse(graph, 1);
        System.out.print("→ ");
        dfs.traverse(graph, 1);
    }

    // ================= Searching Algorithms =================
    private static void runSearchingAnalysis(Scanner sc) {
        SearchingAlgorithms[] algorithms = {
            new LinearSearch(),
            new BinarySearch()
        };

        System.out.print("Enter array size: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        Random rand = new Random();

        for (int i = 0; i < n; i++) arr[i] = rand.nextInt(100);

        System.out.println("\nUnsorted Array: " + Arrays.toString(arr));
        Arrays.sort(arr); // for Binary Search
        System.out.println("Sorted Array (for Binary Search): " + Arrays.toString(arr));

        System.out.print("Enter target element: ");
        int target = sc.nextInt();

        for (SearchingAlgorithms algo : algorithms) {
            long start = System.nanoTime();
            int index = algo.search(arr, target);
            long end = System.nanoTime();

            if (index != -1)
                System.out.println(algo.getName() + " → Found at index " + index +
                        " (Time: " + (end - start) + " ns)");
            else
                System.out.println(algo.getName() + " → Not Found" +
                        " (Time: " + (end - start) + " ns)");
        }
    }
}
