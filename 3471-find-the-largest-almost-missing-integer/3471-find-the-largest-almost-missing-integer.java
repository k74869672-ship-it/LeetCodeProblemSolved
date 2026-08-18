import java.util.*;

class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        int numWindows = n - k + 1;
        int result = -1;

        // Only need to check distinct values present in nums
        Set<Integer> distinctValues = new HashSet<>();
        for (int num : nums) distinctValues.add(num);

        for (int x : distinctValues) {
            int windowCount = 0; // number of size-k subarrays that contain x
            for (int start = 0; start < numWindows; start++) {
                boolean present = false;
                for (int i = start; i < start + k; i++) {
                    if (nums[i] == x) {
                        present = true;
                        break;
                    }
                }
                if (present) windowCount++;
                if (windowCount > 1) break; // early exit, can't be "almost missing"
            }
            if (windowCount == 1) {
                result = Math.max(result, x);
            }
        }

        return result;
    }
}