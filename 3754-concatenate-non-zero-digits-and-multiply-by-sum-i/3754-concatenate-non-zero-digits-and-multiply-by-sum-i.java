class Solution {
    public long sumAndMultiply(int n) {
        if (n == 0) return 0;
        long sum = 0;
        long x = 1;
        long p = 0;
        while (n > 0) {
            int d = n % 10;
            if (d != 0) {
                p += (long) d * x;
                x *= 10;
                sum += d;
            }
            n /= 10;
        }
        return p * sum;
    }
}