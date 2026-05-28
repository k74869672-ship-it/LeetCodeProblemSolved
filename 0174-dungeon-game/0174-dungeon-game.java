class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int row = dungeon.length;
        int col = dungeon[0].length;
        int[][] dp = new int[row][col];

        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 0; j--) {
                if (i == row - 1 && j == col - 1) {
                    dp[i][j] = Math.max(1 - dungeon[i][j], 1);

                } else if (i == row - 1) {
                    dp[i][j] = Math.max(dp[i][j+1] - dungeon[i][j], 1);
                } else if (j == col - 1) {
                    dp[i][j] = Math.max(dp[i+1][j] - dungeon[i][j], 1);
                } else {
                    int next = Math.min(dp[i + 1][j] , dp[i][j + 1]);
                    dp[i][j] = Math.max(next-dungeon[i][j],1 );
                }
            }
        }
        return dp[0][0];
    }
}