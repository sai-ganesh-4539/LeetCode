class Solution {
    // 0/1 BFS is more optimized than Dijkstra here
    // O(mn) - 0/1 BFS
    // O(mn log(mn)) - Dijkstra
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] d = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(d[i], Integer.MAX_VALUE);
        }
        int[][] dir = {{0, 1},{0, -1},{1, 0},{-1, 0}};
        Deque<int[]> dq = new ArrayDeque<>();
        d[0][0] = 0;
        dq.addFirst(new int[]{0, 0});
        while (!dq.isEmpty()) {
            int[] curr = dq.pollFirst();
            int r = curr[0];
            int c = curr[1];
            for (int i = 0; i < 4; i++) {
                int nR = r + dir[i][0];
                int nC = c + dir[i][1];
                if (nR < 0 || nR >= m || nC < 0 || nC >= n) {
                    continue;
                }
                int cost;
                if (grid[r][c] == i + 1) {
                    cost = 0;
                } else {
                    cost = 1;
                }
                int newCost = d[r][c] + cost;
                if (newCost < d[nR][nC]) {
                    d[nR][nC] = newCost;
                    if (cost == 0) {
                        dq.addFirst(new int[]{nR, nC});
                    } else {
                        dq.addLast(new int[]{nR, nC});
                    }
                }
            }
        }
        return d[m - 1][n - 1];
    }
}