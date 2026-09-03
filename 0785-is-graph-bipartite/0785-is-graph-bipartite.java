class Solution {
    class DSU {
        int[] parent;
        DSU(int n) {
            parent = new int[n];
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
            if (c != d) parent[d] = c;
        }
    }
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        DSU dsu = new DSU(n);
        for (int u = 0; u < n; u++) {
            int[] neighbours = graph[u];
            if (neighbours.length == 0) continue;
            for (int i = 1; i < neighbours.length; i++) {
                dsu.union(neighbours[0], neighbours[i]);
            }
            for (int v : neighbours) {
                if (dsu.find(u) == dsu.find(v)) return false;
            }
        }
        return true;
    }
}