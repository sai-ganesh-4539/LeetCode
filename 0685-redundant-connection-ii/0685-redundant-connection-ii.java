class Solution {
    class DSU {
        int[] parent, rank;
        DSU(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            for (int i = 0; i < n; i++) parent[i] = i;
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
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        int[] parent = new int[n + 1];
        int[] a = null;
        int[] b = null;
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            if (parent[v] == 0) parent[v] = u;
            else {
                a = new int[]{ parent[v], v};
                b = edge;
                break;
            }
        }
        DSU dsu = new DSU(n);
        for (int[] edge : edges) {
            if (b != null && edge[0] == b[0] && edge[1] == b[1]) continue;
            if (!dsu.union(edge[0], edge[1])) {
                if (a == null) return edge;
                return a;
            }
        }
        return b;
    }
}