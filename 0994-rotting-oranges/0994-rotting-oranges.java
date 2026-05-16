class Solution {
    public int orangesRotting(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int count = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 2) {
                    count++;
                    dfs(grid, i, j, 2);
                }
            }
        }
        int minutes = 2;
        for (int[] rows : grid) {
            for (int cell : rows) {
                if (cell == 1) {
                    return -1;

                }
                minutes = Math.max(minutes, cell);
            }
        }
        return minutes - 2;
    }

    private void dfs(int[][] grid, int i, int j, int min) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0
                || (grid[i][j] > 1 && grid[i][j] < min)) {
            return;
        }
        grid[i][j] = min;
        dfs(grid, i - 1, j, min + 1);
        dfs(grid, i + 1, j, min + 1);
        dfs(grid, i, j - 1, min + 1);
        dfs(grid, i, j + 1, min + 1);
    }
}