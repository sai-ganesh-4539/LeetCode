class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] a = new int[26];
        int[] b = new int[26];
        Arrays.fill(a, -1);
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (a[c] == -1) a[c] = i;
            b[c] = i;
        }
        List<int[]> x = new ArrayList<>();
        for (int c = 0; c < 26; c++) {
            if (a[c] == -1) continue;
            int l = a[c];
            int r = b[c];
            boolean valid = true;
            for (int i = l; i <= r; i++) {
                int t = s.charAt(i) - 'a';

                if (a[t] < l) {
                    valid = false;
                    break;
                }
                r = Math.max(r, b[t]);
            }
            if (valid) x.add(new int[]{l, r});
        }
        x.sort((u, v) -> u[1] - v[1]);
        List<String> ans = new ArrayList<>();
        int pe = -1;
        for (int[] p : x) {
            int l = p[0];
            int r = p[1];
            if (l > pe) {
                ans.add(s.substring(l, r + 1));
                pe = r;
            }
        }
        return ans;
    }
}