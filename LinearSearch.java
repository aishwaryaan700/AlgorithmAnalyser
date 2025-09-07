package algorithms;

public class LinearSearch implements SearchingAlgorithms {
    @Override
    public int search(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i; // return index if found
            }
        }
        return -1; // not found
    }

    @Override
    public String getName() {
        return "Linear Search";
    }
}
