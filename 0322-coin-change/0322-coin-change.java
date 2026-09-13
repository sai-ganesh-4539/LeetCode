class Solution {
    public int coinChange(int[] coins, int amount) {
        /* this is knapsack, but not 0/1 knapsack because each items - unlimited items
         i.e., unlimited usage but in 0/1 knapsack it's either take it once or don't take it.
         So, this is unbounded knapsack*/
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) dp[i] = amount + 1;
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
            }
        }
        if (dp[amount] == amount + 1) return -1;
        return dp[amount];
    }
}