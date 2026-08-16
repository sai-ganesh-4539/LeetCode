class Solution {
    class DSU {
        int[] parent, rank;
        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }
        int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }
        boolean union(int a, int b) {
            int c = find(a);
            int d = find(b);
            if (c == d) return false;
            if (rank[c] < rank[d]) parent[c] = d;
            else if (rank[c] > rank[d]) parent[d] = c;
            else {
                parent[d] = c;
                rank[c]++;
            }
            return true;
        }
    }
    public int maxStability(int n, int[][] edges, int k) {
        int l = 1;
        int h = 200000;
        int ans = -1;
        while (l <= h) {
            int m = l + (h - l) / 2;
            if (canBuild(n, edges, k, m)) {
                ans = m;
                l = m + 1;
            } else {
                h = m - 1;
            }
        }
        return ans;
    }
    boolean canBuild(int n, int[][] edges, int k, int t) {
        DSU dsu = new DSU(n);
        int uE = 0;
        int up = 0;
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int s = edge[2];
            int must = edge[3];
            if (must == 1) {
                if (s < t) return false;
                if (!dsu.union(u, v)) return false;
                uE++;
            }
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int s = edge[2];
            int must = edge[3];
            if (must == 0 && s >= t) {
                if (dsu.union(u, v)) uE++;
            }
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int s = edge[2];
            int must = edge[3];
            if (must == 0 && s < t && s * 2 >= t) {
                if (up > k) continue;
                if (dsu.union(u, v)) {
                    up++;
                    uE++;
                }
            }
        }
        return uE == n - 1 && up <= k;
    }
}