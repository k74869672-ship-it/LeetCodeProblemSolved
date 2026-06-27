import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int maximumLength(int[] nums) {
        Map<Long, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put((long) num, countMap.getOrDefault((long) num, 0) + 1);
        }

        int maxLen = 1;

        // Handle the special case for 1
        if (countMap.containsKey(1L)) {
            int countOne = countMap.get(1L);
            // The length must be odd
            if (countOne % 2 == 0) {
                maxLen = Math.max(maxLen, countOne - 1);
            } else {
                maxLen = Math.max(maxLen, countOne);
            }
        }

        // Handle cases for x > 1
        for (long x : countMap.keySet()) {
            if (x == 1) continue;

            long current = x;
            int currentLen = 0;

            // Keep squaring while we have at least 2 copies of the current number
            while (countMap.containsKey(current) && countMap.get(current) >= 2) {
                currentLen += 2;
                current = current * current;
            }

            // The peak element only needs to appear at least once
            if (countMap.containsKey(current)) {
                currentLen += 1;
            } else {
                // If the peak doesn't even exist, we must backtrack the last element 
                // to act as the peak instead of an intermediate element.
                currentLen -= 1;
            }

            maxLen = Math.max(maxLen, currentLen);
        }

        return maxLen;
    }
}