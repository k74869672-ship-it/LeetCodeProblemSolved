class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int count = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int maxIsland = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    maxIsland = Math.max(maxIsland, dfs(grid, visited, i, j));
                }
            }
        }
        return maxIsland;
    }

    private int dfs(int[][] grid, boolean[][] visited, int i, int j) {
        int rows = grid.length;
        int cols = grid[0].length;
        if (i < 0 || j < 0 || i >= rows || j >= cols || visited[i][j] || grid[i][j] == 0) {
            return 0;
        }
        visited[i][j] = true;
        return 1+dfs(grid, visited, i - 1, j)+//up
        dfs(grid, visited, i + 1, j)+//down
        dfs(grid, visited, i, j - 1)+//left
        dfs(grid, visited, i, j + 1);//right
    }
}
