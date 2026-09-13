class Solution {
    public int minDays(int n) {
        // same like coin change, but here our denominations are streak sums
        // unlike normal coin change, a streak of k costs k days
        // unbounded knapsack
        // if we start a new streak, we also need 1 skip day to reset it
        // wow what a question, beautiful indeed.
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = n + 1;
        }
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            int sum = 0;
            for (int k = 1; ; k++) {
                sum += k;
                if (sum > n) break;
                if (i >= sum) {
                int d = k;
                if (i - sum != 0) d++;
                dp[i] = Math.min(dp[i], dp[i - sum] + d);
                }
            }
        }
        return dp[n];
    }
}