class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] x = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            x[i] = Integer.MIN_VALUE;
            int sum = 0;
            for (int k = 0; k < 3 && i + k < n; k++) {
                sum += stoneValue[i + k];
                x[i] = Math.max(x[i], sum - x[i + k + 1]);
            }
        }
        if (x[0] > 0) return "Alice";
        if (x[0] < 0) return "Bob";
        return "Tie";
    }
}