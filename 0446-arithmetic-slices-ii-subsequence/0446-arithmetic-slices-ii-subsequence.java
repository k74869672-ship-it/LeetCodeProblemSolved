import java.util.HashMap;
import java.util.Map;

class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        int totalSlices = 0;
        
        // dp[i] maps: common_difference -> count of sequences ending at index i
        Map<Long, Integer>[] dp = new HashMap[n];
        
        for (int i = 0; i < n; i++) {
            dp[i] = new HashMap<>();
            
            for (int j = 0; j < i; j++) {
                // Use long to prevent integer overflow during subtraction
                long diff = (long) nums[i] - nums[j];
                
                // Get the number of sequences ending at j with this difference
                int countAtJ = dp[j].getOrDefault(diff, 0);
                
                // If countAtJ > 0, it means we have sequences of length >= 2 ending at j.
                // Appending nums[i] makes them length >= 3 (valid slices).
                totalSlices += countAtJ;
                
                // Get what's already recorded at i for this difference
                int countAtI = dp[i].getOrDefault(diff, 0);
                
                // Update dp[i]: 
                // Accumulate sequences from j (countAtJ) + 1 (for the new pair [nums[j], nums[i]])
                dp[i].put(diff, countAtI + countAtJ + 1);
            }
        }
        
        return totalSlices;
    }
}