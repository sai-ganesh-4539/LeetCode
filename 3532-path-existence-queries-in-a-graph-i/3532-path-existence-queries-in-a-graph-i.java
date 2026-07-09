class Solution {
    class DSU {
        int[] parent, rank;
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
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        DSU dsu = new DSU(n);
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }
        Arrays.sort(arr, (a,b) -> Integer.compare(a[0], b[0]));
        for (int i = 1; i < n; i++) {
            if (arr[i][0] - arr[i - 1][0] <= maxDiff) {
                dsu.union(arr[i][1], arr[i - 1][1]);
            }
        }
        boolean[] ans = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = dsu.find(queries[i][0]) == dsu.find(queries[i][1]);
        }
        return ans;
    }
}