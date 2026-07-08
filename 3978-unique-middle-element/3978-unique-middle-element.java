class Solution {
    public boolean isMiddleElementUnique(int[] nums) {
        int mid = nums.length / 2;
        int count = 0;
        for (int x : nums) {
            if (x == nums[mid]) {
                count++;
                if (count > 1) return false;
            }
        }
        return true;
    }
}