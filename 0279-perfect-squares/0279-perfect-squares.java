class Solution {
    int[] dp;

    public int numSquares(int n) {
        dp = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            dp[i] = -1;
        }

        return perfectSquare(n);
    }

    public int perfectSquare(int n) {
        if (n == 0) {
            return 0;
        }
        if (dp[n] != -1) {
            return dp[n];
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i * i <= n; i++) {

            int square = i * i;
            ans = Math.min(ans, 1 + perfectSquare(n - square));

        }
        dp[n] = ans;
        return dp[n];
    }
}