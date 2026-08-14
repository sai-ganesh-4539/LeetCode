class Solution {
    class DSU {
        int[] parent, rank;
        int components;
        DSU(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            components = n;
            for (int i = 0; i <= n; i++) {
                parent[i] = i;
                rank[i] = 0;
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
            else if (rank[c] > rank[d]) parent[d] = c;
            else {
                parent[d] = c;
                rank[c]++;
            }
            components--;
        }
        boolean isConnected(int x, int y) {
            return find(x) == find(y);
        }
    }
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        DSU alice = new DSU(n);
        DSU bob = new DSU(n);
        int count = 0;
        for (int[] edge : edges) {
            if (edge[0] == 3) {
                int src = edge[1];
                int dest = edge[2];
                if (!alice.isConnected(src, dest) || !bob.isConnected(src, dest)) {
                    alice.union(src, dest);
                    bob.union(src, dest);
                    count++;
                }
            }
        }
        for (int[] edge : edges) {
            if (edge[0] == 1) {
                int src = edge[1];
                int dest = edge[2];
                if (!alice.isConnected(src, dest)) {
                    alice.union(src, dest);
                    count++;
                }
            }
        }
        for (int[] edge : edges) {
            if (edge[0] == 2) {
                int src = edge[1];
                int dest = edge[2];
                if (!bob.isConnected(src, dest)) {
                    bob.union(src, dest);
                    count++;
                }
            }
        }    
        if (alice.components != 1 || bob.components != 1) return -1;
        return edges.length - count;
    }
}