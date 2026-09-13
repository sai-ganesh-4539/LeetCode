class Solution {
    public int countSpecialIntegers(int[] nums) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) map.put(nums[i], new ArrayList<>());
            map.get(nums[i]).add(i);
        }
        int count = 0;
        for (ArrayList<Integer> x : map.values()) {
            if (x.size() < 3) continue;
            int r = x.get(1) - x.get(0);
            boolean s = true;
            for (int i = 2; i < x.size(); i++) {
                if (x.get(i) - x.get(i - 1) != r) {
                    s = false;
                    break;
                }
            }
            if (s) count++;
        }
        return count;
    }
}