class Solution {
    public int numberOfSubstrings(String s) {
        int[] l = {-1,-1,-1};
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            l[s.charAt(i) - 'a'] = i;
            count += Math.min(l[0], Math.min(l[1], l[2])) + 1;
        }
        return count;
    }
}