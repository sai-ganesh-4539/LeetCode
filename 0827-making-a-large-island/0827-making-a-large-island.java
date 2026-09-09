class Solution {
    //rey entra naakidhi
    int[] parent;
    int[] size;
    public int largestIsland(int[][] grid) {
        int n = grid.length;
        int t = n * n;
        parent = new int[t];
        size = new int[t];
        for (int i = 0; i < t; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int current = i * n + j;
                    if (i + 1 < n && grid[i + 1][j] == 1) union(current, (i + 1) * n + j);
                    if (j + 1 < n && grid[i][j + 1] == 1) union(current, i * n + (j + 1));
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int root = find(i * n + j);
                    ans = Math.max(ans, size[root]);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    int totalSize = 1;
                    int[] roots = new int[4];
                    int count = 0;
                    if (i > 0 && grid[i - 1][j] == 1) {
                        roots[count++] = find((i - 1) * n + j);
                    }
                    if (i + 1 < n && grid[i + 1][j] == 1) {
                        roots[count++] = find((i + 1) * n + j);
                    }
                    if (j > 0 && grid[i][j - 1] == 1) {
                        roots[count++] = find(i * n + (j - 1));
                    }
                    if (j + 1 < n && grid[i][j + 1] == 1) {
                        roots[count++] = find(i * n + (j + 1));
                    }
                    for (int k = 0; k < count; k++) {
                        boolean duplicate = false;
                        for (int p = 0; p < k; p++) {
                            if (roots[p] == roots[k]) {
                                duplicate = true;
                                break;
                            }
                        }
                        if (!duplicate) totalSize += size[roots[k]];
                    }
                    ans = Math.max(ans, totalSize);
                }
            }
        }
        return ans;
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
            int temp = c;
            c = d;
            d = temp;
        }
        parent[d] = c;
        size[c] += size[d];
    }
}