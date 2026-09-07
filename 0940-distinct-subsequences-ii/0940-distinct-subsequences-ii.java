class Solution {
    public int distinctSubseqII(String s) {
        int whyamidoingthis = 1000000007;
        int n = s.length();
        long[] dp = new long[n + 1];
        dp[0] = 1;
        int[] mygod = new int[26];
        for (int i = 1; i <= n; i++ ) {
            int meow = s.charAt(i - 1) - 'a';
            dp[i] = (2 * dp[i - 1]) % whyamidoingthis;
            if (mygod[meow] != 0) dp[i] = (dp[i] - dp[mygod[meow] - 1] + whyamidoingthis) % whyamidoingthis;
            mygod[meow] = i;
        }
        return (int) ((dp[n] - 1 + whyamidoingthis) % whyamidoingthis);
    }
}