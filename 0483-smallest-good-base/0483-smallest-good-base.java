class Solution {
    public String smallestGoodBase(String n) {
        // n = 1 + k + k^2 + ..... + k^(m - 1)
        long num = Long.parseLong(n);
        int max = 64 - Long.numberOfLeadingZeros(num);
        for (int i = max; i >= 2; i--) {
            long l = 2;
            long h = (long) Math.pow(num, 1.0 / (i - 1)) + 1;
            while (l <= h) {
                long b = l + (h - l) / 2;
                int r = compare(num, b, i);
                if (r == 0) return String.valueOf(b);
                if (r < 0) l = b + 1;
                else h = b - 1;
            }
        }
        return String.valueOf(num - 1);
    }
    private int compare(long n, long b, int x) {
        long sum = 1;
        long p = 1;
        for (int i = 1; i < x; i++) {
            if (p > (n - 1) / b) return 1;
            p *= b;
            if (sum > n - p) return 1;
            sum += p;
        }
        return Long.compare(sum, n);
    }
}