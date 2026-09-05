class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] s = new int[n];
        s[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            s[i] = Math.min(nums[i], s[i + 1]);
        }
        int p = nums[0];
        for (int i = 0; i < n; i++) {
            p = Math.max(p, nums[i]);
            if ((long) p - s[i] <= k) return i;
        }
        return -1;
    }
}