class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int ans = Integer.MIN_VALUE;
        if (m <= n) {
            for (int i = 0; i < m; i++) {
                int[] sum = new int[n];
                for (int j = i; j < m; j++) {
                    for (int t = 0; t < n; t++) {
                        sum[t] += matrix[j][t];
                    }
                    ans = Math.max(ans, mS(sum, k));
                    if (ans == k) return k;
                }
            }
        } else {
            for (int i = 0; i < n; i++) {
                int[] sum = new int[m];
                for (int j = i; j < n; j++) {
                    for (int t = 0; t < m; t++) {
                        sum[t] += matrix[t][j];
                    }
                    ans = Math.max(ans, mS(sum, k));
                    if (ans == k) return k;
                }
            }
        }
        return ans;
    }
    private int mS(int[] arr, int k) {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        int p = 0;
        int b = Integer.MIN_VALUE;
        for (int x : arr) {
            p += x;
            Integer prev = set.ceiling(p - k);
            if (prev != null) {
                b = Math.max(b, p - prev);
            }
            set.add(p);
        }
        return b;
    }
}