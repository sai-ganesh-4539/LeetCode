class Solution {
    public int countSpecialIntegers(int[] nums) {
        HashSet<Integer> x = new HashSet<>();
        HashSet<Integer> y = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (x.contains(nums[i]) && nums[i] != nums[i - 1]) {
                y.add(nums[i]);
            }
            x.add(nums[i]);
        }
        return x.size() - y.size();
    }
}