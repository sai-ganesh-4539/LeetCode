class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        boolean[][] p = new boolean[n][n];
        for (int i = 1; i <= n; i++) {
            for (int l = 0; l + i - 1 < n; l++) {
                int r = l + i - 1;
                if (s.charAt(l) == s.charAt(r) && (i <= 2 || p[l + 1][r - 1])) p[l][r] = true; 
            }
        }
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1];
            for (int j = 0; j < i; j++)
                if (i - j >= k && p[j][i - 1]) dp[i] = Math.max(dp[i], dp[j] + 1);
        }
        return dp[n];
    }
}