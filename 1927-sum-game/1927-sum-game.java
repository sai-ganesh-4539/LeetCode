class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int h = n / 2;
        int d = 0;
        int l = 0;
        int r = 0;
        for (int i = 0; i < h; i++) {
            if (num.charAt(i) == '?') l++;
            else d += num.charAt(i) - '0';
        }
        for (int i = h; i < n; i++) {
            if (num.charAt(i) == '?') r++;
            else d -= num.charAt(i) - '0';
        }
        if (l == r) return d != 0;
        if (l < r) {
            d = -d;
            int temp = l;
            l = r;
            r = temp;
        }
        return 2 * d + 9 * (l - r) != 0;
    }
}