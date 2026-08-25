class Solution {
    public int missingMultiple(int[] nums, int k) {
        for (int i = k; ; i += k) {
            boolean found = false;
            for (int num : nums) {
                if (num == i) {
                    found = true;
                    break;
                }
            }
            if (!found) return i;
        }
    }
}