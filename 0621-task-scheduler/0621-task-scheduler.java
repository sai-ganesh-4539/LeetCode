class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] x = new int[26];
        for (char t : tasks) x[t - 'A']++;
        int max = 0;
        for (int c : x) max = Math.max(max, c);
        int maxc = 0;
        for (int c : x) if (c == max) maxc++;
        int r = (max - 1) * (n + 1) + maxc;
        return Math.max(tasks.length, r);
    }
}