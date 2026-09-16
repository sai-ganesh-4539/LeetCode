class Solution {
    public int maximalRectangle(char[][] matrix) {
        int r = matrix.length;
        int c = matrix[0].length;
        int[] h = new int[c];
        int a = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (matrix[i][j] == '1') h[j]++;
                else h[j] = 0;
            }
            a = Math.max(a, lH(h));
        }
        return a;
    }
    public int lH(int[] h) {
        int n = h.length;
        int a = 0;
        int[] s = new int[n + 1];
        int t = -1;
        for (int i = 0; i <= n; i++) {
            int c = (i == n) ? 0 : h[i];
            while (t >= 0 && h[s[t]] > c) {
                int he = h[s[t--]];
                int w;
                if (t == -1) w = i;
                else w = i - s[t] - 1;
                a = Math.max(a, he * w);
            }
            s[++t] = i;
        }
        return a;
    }
}