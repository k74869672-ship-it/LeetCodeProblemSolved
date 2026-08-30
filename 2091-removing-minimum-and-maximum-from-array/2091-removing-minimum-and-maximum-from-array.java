class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        int minIndex = 0, maxIndex = 0;
        
        // Find index of the minimum and maximum elements
        for (int idx = 0; idx < n; idx++) {
            if (nums[idx] < nums[minIndex]) {
                minIndex = idx;
            }
            if (nums[idx] > nums[maxIndex]) {
                maxIndex = idx;
            }
        }
        
        int i = Math.min(minIndex, maxIndex); // smaller index
        int j = Math.max(minIndex, maxIndex); // larger index
        
        // Strategy 1: remove both from the front
        int removeFromFront = j + 1;
        
        // Strategy 2: remove both from the back
        int removeFromBack = n - i;
        
        // Strategy 3: remove one from front, one from back
        int removeSplit = (i + 1) + (n - j);
        
        return Math.min(removeFromFront, Math.min(removeFromBack, removeSplit));
    }
}