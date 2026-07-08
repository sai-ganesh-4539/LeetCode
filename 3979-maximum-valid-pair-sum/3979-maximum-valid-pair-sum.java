class Solution {
    public int maxValidPairSum(int[] nums, int k) {
        int max = Integer.MIN_VALUE;
        int x = Integer.MIN_VALUE;
        for (int i = k; i < nums.length; i++) {
            max = Math.max(max, nums[i - k]);
            x = Math.max(x, max + nums[i]);
        }
        return x;
    }
}