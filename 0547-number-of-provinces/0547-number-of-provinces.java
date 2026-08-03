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
        }
    }
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        DSU dsu = new DSU(n);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) dsu.union(i, j);
            }
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (dsu.find(i) == i) count++;
        }
        return count;
    }
}