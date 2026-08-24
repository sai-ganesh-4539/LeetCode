class Solution {
    public int uniquePaths(int m, int n) {
        long x = 1;
        for (int i = 1; i <= m - 1; i++) {
            x = x * (n - 1 + i) / i;
        }
        return (int) x;
    }
}