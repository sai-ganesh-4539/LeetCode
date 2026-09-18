class Solution {
    int[] parent;
    int[] size;
    public int largestComponentSize(int[] nums) {
        int n = nums.length;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        int max = 0;
        for (int x : nums) {
            max = Math.max(max, x);
        }
        int[] pf = new int[max + 1];
        for (int i = 2; i <= max; i++) {
            if (pf[i] == 0) pf[i] = i;
            if ((long) i * i <= max) {
                for (int j = i * i; j <= max; j += i) {
                    if (pf[j] == 0) pf[j] = i;
                }
            }
        }
        int[] fO = new int[max + 1];
        Arrays.fill(fO, -1);
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            while (x > 1) {
                int f = pf[x];
                if (fO[f] == -1) fO[f] = i;
                else union(i, fO[f]);
                while (x % f == 0) x /= f;
            }
        }
        int ans = 1;
        for (int i = 0; i < n; i++) {
            if (find(i) == i) {
                ans = Math.max(ans, size[i]);
            }
        }
        return ans;
    }
    int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa == pb) {
            return;
        }
        if (size[pa] < size[pb]) {
            int temp = pa;
            pa = pb;
            pb = temp;
        }
        parent[pb] = pa;
        size[pa] += size[pb];
    }
}