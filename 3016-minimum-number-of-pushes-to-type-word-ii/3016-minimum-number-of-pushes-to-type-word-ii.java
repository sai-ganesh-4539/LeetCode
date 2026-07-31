class Solution {
    public int minimumPushes(String word) {
        int[] x = new int[26];
        for (char c : word.toCharArray()) x[c - 'a']++;
        Arrays.sort(x);
        int ans = 0;
        int t = 0;
        for (int i = 25; i >= 0; i--) {
            if (x[i] == 0) break;
            int p = t / 8 + 1;
            ans += x[i] * p;
            t++;
        }
        return ans;
    }
}