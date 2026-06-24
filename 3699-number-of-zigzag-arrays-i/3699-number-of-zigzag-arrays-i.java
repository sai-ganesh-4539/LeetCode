class Solution {
    static final int mod = 1_000_000_007;
    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        long[] x = new long[m];
        long[] y = new long[m];
        for (int i = 0; i < m; i++) {
            x[i] = i;
            y[i] = m - 1 - i;
        }
        for (int i = 3; i <= n; i++) {
            long[] a = new long[m];
            long[] b = new long[m];
            a[0] = y[0];
            for (int j = 1; j < m; j++) {
                a[j] = (a[j - 1] + y[j]) % mod;
            }
            b[m - 1] = x[m - 1];
            for (int k = m - 2; k >= 0; k--) {
                b[k] = (b[k + 1] + x[k]) % mod;
            }
            long[] p = new long[m];
            long[] t = new long[m];
            for (int j = 0; j < m; j++) {
                if (j > 0) p[j] = a[j - 1];
                if (j + 1 < m) t[j] = b[j + 1];
            }
            x = p;
            y = t;
        }
        long ans = 0;
        for (int i = 0; i < m; i++) {
            ans += x[i];
            ans += y[i];
            ans %= mod;
        }
        return (int) ans;
    }
}