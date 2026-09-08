class Solution {
    public long countCommas(long n) {
        long x = 0;
        long s = 1000;
        long c = 1;
        while (s <= n) {
            long e = Math.min(n, s * 1000 - 1);
            x += (e - s + 1) * c;
            s *= 1000;
            c++;
        }
        return x;
    }
}