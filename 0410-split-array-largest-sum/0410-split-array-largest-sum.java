class Solution {
    public int splitArray(int[] nums, int k) {
        int l = 0;
        int r = 0;
        for (int num : nums) {
            l = Math.max(l, num);
            r += num;
        }
        while (l < r) {
            int m = l + (r - l) / 2;
            if (split(nums, k, m)) r = m;
            else l = m + 1;
        }
        return l;
    }
    private boolean split(int[] nums, int k, int max) {
        int p = 1;
        int sum = 0;
        for (int num : nums) {
            if (sum + num > max) {
                p++;
                sum = num;
                if (p > k) return false;
            } else {
                sum += num;
            }
        }
        return true;
    }
}