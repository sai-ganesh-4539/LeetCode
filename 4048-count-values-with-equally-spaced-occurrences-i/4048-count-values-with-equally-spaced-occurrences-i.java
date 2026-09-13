class Solution {
    public int countSpecialIntegers(int[] nums) {
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if ((nums[i] == nums[j]) && (nums[j] == nums[k]) && (j - i == k - j)) {
                        int t = 0;
                        for (int x : nums) if (x == nums[i]) t++;
                        if (t == 3) count++;
                    }
                }
            }
        }
        return count;
    }
}