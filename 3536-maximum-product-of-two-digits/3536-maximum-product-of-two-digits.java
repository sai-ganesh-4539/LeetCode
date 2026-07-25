class Solution {
    public int maxProduct(int n) {
        int m = 0;
        int s = 0;
        while (n > 0) {
            int d = n % 10;
            if (d > m) {
                s = m;
                m = d;
            } else if (d > s) {
                s = d;
            }
            n /= 10;
        }
        return m * s;
    }
}