class Solution {
    int m, n;
    public int maxAreaOfIsland(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int x = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    x = Math.max(x, dfs(grid, i, j));
                }
            }
        }
        return x;
    }
    private int dfs(int[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= m || c >= n) return 0;
        if (grid[r][c] == 0) return 0;
        grid[r][c] = 0;
        return 1 
        + dfs(grid, r + 1, c)
        + dfs(grid, r - 1, c)
        + dfs(grid, r, c + 1)
        + dfs(grid, r, c - 1);
    }
}