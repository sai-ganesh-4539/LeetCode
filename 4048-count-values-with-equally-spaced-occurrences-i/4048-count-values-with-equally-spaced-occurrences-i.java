class Solution {
    public int countSpecialIntegers(int[] nums) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.putIfAbsent(nums[i], new ArrayList<>());
            map.get(nums[i]).add(i);
        }
        int count = 0;
        for (ArrayList<Integer> x : map.values()) {
            if (x.size() == 3) {
                int i = x.get(0);
                int j = x.get(1);
                int k = x.get(2);
                if (j - i == k - j) count++;
            }
        }
        return count;
    }
}