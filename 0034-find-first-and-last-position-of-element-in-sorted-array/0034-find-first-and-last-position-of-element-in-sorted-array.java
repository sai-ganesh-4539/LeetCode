class Solution {
    public int[] searchRange(int[] nums, int target) {
        int s = 0;
        int e = nums.length - 1;
        int[] f = {-1,-1};
        while (s <= e) {
            int m = s + (e - s) / 2;
            if (target == nums[m]) {
                f[0] = m;
                e = m - 1;
            } else if (nums[m] < target) s = m + 1;
            else e = m - 1;
        }
        s = 0;
        e = nums.length - 1;
        while (s <= e) {
            int m = s + (e - s) / 2;
            if (target == nums[m]) {
                f[1] = m;
                s = m + 1;
            } else if (nums[m] < target) s = m + 1;
            else e = m - 1;
        }
        return f;
    }
}