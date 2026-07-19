class Solution {
    public String smallestSubsequence(String s) {
        int[] x = new int[26];
        boolean[] t = new boolean[26];
        for (char c : s.toCharArray()) x[c - 'a']++;
        StringBuilder y = new StringBuilder();
        for (char c : s.toCharArray()) {
            int i = c - 'a';
            x[i]--;
            if (t[i]) continue;
            while (y.length() > 0 && y.charAt(y.length() - 1) > c && x[y.charAt(y.length() - 1) - 'a'] > 0) {
                char r = y.charAt(y.length() - 1);
                y.deleteCharAt(y.length() - 1);
                t[r - 'a'] = false;
            }
            y.append(c);
            t[i] = true;
        }
        return y.toString();
    }
}