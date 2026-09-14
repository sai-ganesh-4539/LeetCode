class Solution {
    // 0/1 bfs is more optimized than dijkstra here 
    // O(mn) - 0/1 bfs
    // O(mnlog(mn)) - dijkstra
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] d = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(d[i], Integer.MAX_VALUE);
        }
        int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        d[0][0] = 0;
        pq.add(new int[]{0, 0, 0});
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int r = curr[0];
            int c = curr[1];
            int cost = curr[2];
            if (cost > d[r][c]) continue;
            for (int i = 0; i < 4; i++) {
                int nR = r + dir[i][0];
                int nC = c + dir[i][1];
                if (nR < 0 || nR >= m || nC < 0 || nC >= n) continue;
                int newCost;
                if (grid[r][c] == i + 1) newCost = cost;
                else newCost = cost + 1;
                if (newCost < d[nR][nC]) {
                    d[nR][nC] = newCost;
                    pq.add(new int[]{nR, nC, newCost});
                }
            }
        }
        return d[m - 1][n - 1];
    }
}