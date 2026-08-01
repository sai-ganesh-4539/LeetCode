class Solution {
    public boolean canMakeArithmeticProgression(int[] arr) {
        int n = arr.length;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int num : arr) {
            max = Math.max(num, max);
            min = Math.min(num, min);
        }
        if ((max - min) % (n - 1) != 0) return false;
        int d = (max - min) / (n - 1);
        if (d == 0) return true;
        boolean[] x = new boolean[n];
        for (int t : arr) {
            int r = t - min;
            if (r % d != 0) return false;
            int a = r / d;
            if (a >= n || x[a]) return false;
            x[a] = true;
        }
        return true;
    }
}