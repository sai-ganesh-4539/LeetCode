class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int INF = 1000000;
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            b[i] = INF;
        }
        int l = 0;
        int sum = 0;
        int x = INF;
        for (int r = 0; r < n; r++) {
            sum += arr[r];
            while (sum > target) {
                sum -= arr[l];
                l++;
            }
            if (sum == target) {
                int y = r - l + 1;
                if (l > 0 && b[l - 1] != INF) {
                    x = Math.min(x, y + b[l - 1]);
                }
                if (r == 0) {
                    b[r] = y;
                } else {
                    b[r] = Math.min(b[r - 1], y);
                }
            } else {
                if (r > 0) {
                    b[r] = b[r - 1];
                }
            }
        }
        return x == INF ? -1 : x;
    }
}