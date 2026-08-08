class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] == b[0]) return b[1] - a[1];
            return a[0] - b[0];
        });
        int[] lis = new int[envelopes.length];
        int x = 0;
        for (int[] e : envelopes) {
            int h = e[1];
            int l = 0;
            int r = x;
            while (l < r) {
                int m = l + (r - l) / 2;
                if (lis[m] < h) l = m + 1;
                else r = m;
            }
            lis[l] = h;
            if (l == x) x++;
        }
        return x;
    }
}