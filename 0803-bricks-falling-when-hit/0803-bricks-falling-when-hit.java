class DSU {
    int[] parent;
    int[] size;
    DSU (int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
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
        if (size[c] < size[d]) {
            parent[c] = d;
            size[d] += size[c];
        } else {
            parent[d] = c;
            size[c] += size[d];
        }
    }
}
class Solution {
    public int[] hitBricks(int[][] grid, int[][] hits) {
        // why the heck am i doing this problem
        // its so lengthy heavy, damn bro
        int m = grid.length;
        int n = grid[0].length;
        int dummy = m * n;
        DSU dsu = new DSU(m * n + 1);
        int[][] x = new int[m][n];
        for (int i  = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                x[i][j] = grid[i][j];
            }
        }
        for (int[] hit : hits) {
            int r = hit[0];
            int c = hit[1];
            x[r][c] = 0;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (x[i][j] == 0) continue;
                int c = i * n + j;
                if (i == 0) dsu.union(c, dummy);
                if (i > 0 && x[i - 1][j] == 1) dsu.union(c, (i - 1) * n + j);
                if (j > 0 && x[i][j - 1] == 1) dsu.union(c, i * n + (j - 1));
            }
        }
        int[] result = new int[hits.length];
        for (int k = hits.length - 1; k >= 0; k--) {
            int r = hits[k][0];
            int c = hits[k][1];
            if (grid[r][c] == 0) {
                result[k] = 0;
                continue;
            }
            int b = dsu.size[dsu.find(dummy)];
            x[r][c] = 1;
            int curr = r * n + c;
            //top row - dummy
            if (r == 0) dsu.union(curr, dummy);
            //down
            if (r + 1 < m && x[r + 1][c] == 1) {
                dsu.union(curr, (r + 1) * n + c);
            }
            //up
            if (r - 1 >= 0 && x[r - 1][c] == 1) {
                dsu.union(curr, (r - 1) * n + c);
            }
            //right
            if (c + 1 < n && x[r][c + 1] == 1) {
                dsu.union(curr, r * n + (c + 1));
            }
            //left
            if (c - 1 >= 0 && x[r][c - 1] == 1) {
                dsu.union(curr, r * n + (c - 1));
            }
            int a = dsu.size[dsu.find(dummy)];
            result[k] = Math.max(0, a - b - 1);
        }
        return result;
    }
}