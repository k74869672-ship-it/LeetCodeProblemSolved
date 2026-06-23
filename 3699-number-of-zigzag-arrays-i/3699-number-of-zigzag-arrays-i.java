class Solution {
    public int zigZagArrays(int n, int l, int r) {
        int MOD = 1000000007;
        int k = r - l + 1;
        
        if (n <= 1) {
            return 0;
        }

        long[][] dp = new long[k + 1][2];
        long[] prev = new long[k + 1];
        for (int i = 1; i <= k; i++) {
            prev[i] = (prev[i - 1] + 1) % MOD;
        }

        for (int v = 1; v <= k; v++) {
            // dir = 0 (prev_val > v): sum of all prev_val from v+1 to k
            long downWays = (prev[k] - prev[v] + MOD) % MOD;
            // dir = 1 (prev_val < v): sum of all prev_val from 1 to v-1
            long upWays = prev[v - 1];

            dp[v][0] = downWays;
            dp[v][1] = upWays;
        }
        
        // FIX 1: Loop should run up to array length 'n', not range 'k'
        for (int i = 3; i <= n; i++) { 
            long[][] nextDp = new long[k + 1][2];

            // Prefix sums for current dp states to optimize transitions to O(1)
            long[] pref0 = new long[k + 1]; // for dir = 0
            long[] pref1 = new long[k + 1]; // for dir = 1

            // FIX 2: Loop should run up to range 'k', not length 'n'
            for (int v = 1; v <= k; v++) { 
                pref0[v] = (pref0[v - 1] + dp[v][0]) % MOD;
                pref1[v] = (pref1[v - 1] + dp[v][1]) % MOD;
            }
            
            for (int v = 1; v <= k; v++) {
                // If next state needs to be UP (current v > previous val), 
                // Thus, the previous transition must have been DOWN (dir = 0).
                nextDp[v][1] = pref0[v - 1];

                // If next state needs to be DOWN (current v < previous val),
                // Thus, the previous transition must have been UP (dir = 1).
                nextDp[v][0] = (pref1[k] - pref1[v] + MOD) % MOD;
            }

            dp = nextDp;
        }
        
        // Accumulate total answer from the final length n
        long totalSum = 0;
        for (int v = 1; v <= k; v++) {
            totalSum = (totalSum + dp[v][0] + dp[v][1]) % MOD;
        }

        return (int) totalSum;
    }
}