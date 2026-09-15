class Solution {
    public int maxProduct(int[] nums) {
        // kadane's algorithm modified for products
        int max = nums[0];
        int min = nums[0];
        int x = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int r = nums[i];
            if (r < 0) {
                int temp = max;
                max = min;
                min = temp;
            }
            max = Math.max(r, max * r);
            min = Math.min(r, min * r);
            x = Math.max(x, max);
        }
        return x;
    }
}