class Solution {
    class DSU {
        int[] parent;
        DSU(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }
        void union(int a, int b) {
            int A = find(a);
            int B = find(b);
            if (A != B) parent[A] = B;
        }
    }
    public int minSwapsCouples(int[] row) {
        int t = row.length / 2;
        DSU dsu = new DSU(t);
        for (int i = 0; i < row.length; i += 2) {
            int c1 = row[i] / 2;
            int c2 = row[i + 1] / 2;
            dsu.union(c1, c2);
        }
        int c = 0;
        for (int i = 0; i < t; i++) {
            if (dsu.find(i) == i) c++;
        }
        return t - c;
    }
}