class Solution {
    public int totalNumbers(int[] digits) {
        int[] x = new int[10];
        for (int d : digits) x[d]++;
        int ans = 0;
        for (int a = 1; a <= 9; a++) {
            if (x[a] == 0) continue;
            x[a]--;
            for (int b = 0; b <= 9; b++) {
                if (x[b] == 0) continue;
                x[b]--;
                for (int c = 0; c <= 8; c += 2) {
                    if (x[c] > 0) ans++;
                }
                x[b]++;
            }
            x[a]++;
        }
        return ans;
    }
}