class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int l = 1;
        int r = 0;
        for (int p : piles) r = Math.max(r, p);
        while (l < r) {
            int m = l + (r - l) / 2;
            long t = 0;
            for (int p : piles) t += (p + m - 1) / m;
            if (t <= h) r = m;
            else l = m + 1;
        }
        return l;
    }
}