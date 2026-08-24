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
        boolean isConnected(int x, int y) {
            return find(x) == find(y);
        }
    }
    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) return -1;
        DSU dsu = new DSU(n);
        int c = n;
        int e = 0;
        for (int[] co : connections) {
            int u = co[0];
            int v = co[1];
            if (dsu.union(u, v)) c--;
            else e++;
        }
        return c - 1 <= e ? c - 1 : -1;
    }
}