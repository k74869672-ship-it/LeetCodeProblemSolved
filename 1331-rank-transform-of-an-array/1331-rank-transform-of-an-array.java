import java.util.*;

class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        
        if (n == 0) return result;
        
        // Step 1: Create a sorted copy of the array
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        
        // Step 2: Assign ranks to unique values using a HashMap
        Map<Integer, Integer> rankMap = new HashMap<>();
        int rank = 1;
        for (int num : sorted) {
            if (!rankMap.containsKey(num)) {
                rankMap.put(num, rank);
                rank++;
            }
        }
        
        // Step 3: Build the result array using the rank map
        for (int i = 0; i < n; i++) {
            result[i] = rankMap.get(arr[i]);
        }
        
        return result;
    }
}