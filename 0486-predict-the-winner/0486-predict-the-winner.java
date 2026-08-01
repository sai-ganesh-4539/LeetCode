class Solution {
    public boolean predictTheWinner(int[] nums) {
        return solve(nums, 0, nums.length - 1) >= 0;
    }
    private int solve(int[] nums, int a, int b) {
        if (a == b) return nums[a];
        int l = nums[a] - solve(nums, a + 1, b);
        int r = nums[b] - solve(nums, a, b - 1);
        return Math.max(l, r);
    }
}