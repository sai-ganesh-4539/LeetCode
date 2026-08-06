class Solution {
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> p = new HashMap<>();
        p.put(0,1);
        int sum = 0;
        int count = 0;
        for (int num : nums) {
            sum += num;
            if (p.containsKey(sum - k)) count += p.get(sum - k);
            p.put(sum, p.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}