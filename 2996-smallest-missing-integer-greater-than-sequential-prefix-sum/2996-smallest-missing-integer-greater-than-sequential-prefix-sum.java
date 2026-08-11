class Solution {
    public int missingInteger(int[] nums) {
        int n = nums.length;
        int sum = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                sum += nums[i];
            } else {
                break;
            }
        }
        HashSet<Integer> s = new HashSet<>();
        for (int num : nums) s.add(num);
        while (s.contains(sum)) sum++;
        return sum;
    }
}