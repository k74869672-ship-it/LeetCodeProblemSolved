class Solution {
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        
        // Initialize with base case (length 1 subarrays)
        for (int i = 0; i < n; i++) {
            dp[i] = nums[i];
        }
        
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                // dp[i] currently holds dp[i][j-1] (before update)
                // dp[i+1] currently holds dp[i+1][j] (from previous iteration, not yet overwritten)
                dp[i] = Math.max(nums[i] - dp[i + 1], nums[j] - dp[i]);
            }
        }
        
        return dp[0] >= 0;
    }
}