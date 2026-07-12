class Solution {
    class DSU {
        int[] parent, rank;
        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }
        int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }
        void union(int a, int b) {
            int t = find(a);
            int r = find(b);
            if (t == r) return;
            if (rank[t] < rank[r]) {
                parent[t] = r;
                rank[r] += rank[t];
            } else {
                parent[r] = t;
                rank[t] += rank[r];
            }
        }
    }
    public int countCompleteComponents(int n, int[][] edges) {
        DSU dsu = new DSU(n);
        for (int[] e : edges) dsu.union(e[0], e[1]);
        int[] v = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            v[dsu.find(i)]++;
        }
        for (int[] e : edges) {
            int root = dsu.find(e[0]);
            y[root]++;
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (v[i] == 0) continue;
            int k = v[i];
            if (y[i] == k * (k - 1) / 2) ans++;
        }
        return ans;
    }
}