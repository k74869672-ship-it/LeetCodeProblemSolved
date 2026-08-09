class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        int[] suffix = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }
        Integer[][] memory = new Integer[n][n + 1];
        return dp(0, 1, piles, suffix, memory, n);
    }

    private int dp(int i, int M, int[] piles, int[] suffix, Integer[][] memory, int n) {
        if (i >= n) {
            return 0;
        }
        if (i + 2 * M >= n) {
            return suffix[i];
        }
        if (memory[i][M] != null) {
            return memory[i][M];

        }
        int best = 0;
        for (int X = 1; X <= 2 * M; X++) {
            int newM = Math.max(M, X);
            int opponent = dp(i + X, newM, piles, suffix, memory, n);
            int mine = suffix[i] - opponent;
            best = Math.max(best, mine);
        }
        memory[i][Math.min(M, n)] = best;
        return best;
    }
}