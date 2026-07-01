class Solution {
    static class DSU {
        int[] parent;
        int[] rank;
        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }
        void union(int a, int b) {
            int t = find(a), r = find(b);
            if (t == r) return;
            if (rank[t] < rank[r]) parent[t] = r;
            else if (rank[t] > rank[r]) parent[r] = t;
            else {
                parent[r] = t;
                rank[t]++;
            }
        }
    }
    int id(int r, int c, int n) {
        return r * n + c;
    }
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int m = grid.size();
        int n = grid.get(0).size();
        if (grid.get(0).get(0) == 1 || grid.get(m - 1).get(n - 1) == 1) return 0;
        DSU dsu = new DSU(m * n);
        int[][] dist = new int[m][n];
        for (int[] p : dist) Arrays.fill(p, -1);
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    dist[i][j] = 0;
                    q.offer(new int[]{i, j});
                }
            }
        }
        int[][] d = {{-1, 0}, {1, 0}, {0, -1}, {0,1}};
        while (!q.isEmpty()) {
            int[] cell = q.poll();
            int r = cell[0], c = cell[1];
            for (int[] v : d) {
                int nr = r + v[0];
                int nc = c + v[1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && dist[nr][nc] == -1) {
                    dist[nr][nc] = dist[r][c] + 1;
                    q.offer(new int[]{nr, nc});
                }
            }
        }
        List<int[]> cells = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                cells.add(new int[]{dist[i][j], i, j});
            }
        }
        Collections.sort(cells, (a,b) -> b[0] - a[0]);
        boolean[][] active = new boolean[m][n];
        for (int[] t : cells) {
            int s = t[0];
            int r = t[1];
            int c = t[2];
            active[r][c] = true;
            for (int[] v : d) {
                int nr = r + v[0];
                int nc = c + v[1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && active[nr][nc]) {
                    dsu.union(id(r,c,n), id(nr,nc,n));
                }
            }
            if (active[0][0] && active[m - 1][n - 1] && dsu.find(id(0,0,n)) == dsu.find(id(m - 1, n - 1, n))) return s;
        }
        return 0;
    }
}