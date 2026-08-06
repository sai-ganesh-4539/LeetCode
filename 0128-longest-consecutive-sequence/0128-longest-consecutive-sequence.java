class Solution {
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> s = new HashSet<>();
        for (int num : nums) s.add(num);
        int l = 0;
        for (int num : s){
            if (!s.contains(num - 1)) {
                int x = num;
                int len = 1;
                while (s.contains(x + 1)) {
                    x++;
                    len++;
                }
                l = Math.max(l, len);
            }
        }
        return l;
    }
}