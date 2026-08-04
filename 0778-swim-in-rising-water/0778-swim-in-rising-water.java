class Solution {
    class DSU {
        int[] parent, rank;
        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }
        void union(int a, int b) {
            int c = find(a);
            int d = find(b);
            if (c == d) return;
            if (rank[c] < rank[d]) parent[c] = d;
            else if(rank[c] > rank[d]) parent[d] = c;
            else {
                parent[d] = c;
                rank[c]++;
            }
        }
    }
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int[][] x = new int[n * n][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                x[grid[i][j]][0] = i;
                x[grid[i][j]][1] = j;
            }
        }
        DSU dsu = new DSU(n * n);
        boolean[][] open = new boolean[n][n];
        int[][] dir = {{1,0}, {-1,0}, {0,1}, {0, -1}};
        for (int t = 0; t < n * n; t++) {
            int r = x[t][0];
            int c = x[t][1];
            open[r][c] = true;
            int id = r * n + c;
            for (int[] d : dir) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (nr < 0 || nc < 0 || nr >= n || nc >= n) {
                    continue;
                }
                if (!open[nr][nc]) continue;
                dsu.union(id, nr * n + nc);
            }
            if (dsu.find(0) == dsu.find(n * n - 1)) return t;
        }
        return -1;
    }
}