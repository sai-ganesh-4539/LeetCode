class Solution {
    public boolean uniformArray(int[] nums1) {
        int n = nums1.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, nums1[i]);
        }
        if (min % 2 == 0) {
            for (int x : nums1) {
                if (x % 2 != 0) return false;
            }
        }
        return true;
    }
}