import java.util.Arrays;

class Solution {
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }

        int[][] memo = new int[n][n];
        for (int[] row : memo) Arrays.fill(row, -1);

        return search(0, n - 1, prefix, memo);
    }

    private int search(int i, int j, int[] prefix, int[][] memo) {
        if (i == j) return 0; // only one stone left, game over for this segment
        if (memo[i][j] != -1) return memo[i][j];

        int res = 0;
        for (int k = i; k < j; k++) {
            int leftSum = prefix[k + 1] - prefix[i];
            int rightSum = prefix[j + 1] - prefix[k + 1];

            if (leftSum < rightSum) {
                res = Math.max(res, leftSum + search(i, k, prefix, memo));
            } else if (leftSum > rightSum) {
                res = Math.max(res, rightSum + search(k + 1, j, prefix, memo));
            } else {
                // equal sums — Alice can choose which one is kept
                res = Math.max(res, leftSum + search(i, k, prefix, memo));
                res = Math.max(res, rightSum + search(k + 1, j, prefix, memo));
            }
        }

        memo[i][j] = res;
        return res;
    }
}