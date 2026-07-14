class Solution {
    public boolean splitArraySameAverage(int[] nums) {
        int n = nums.length;
        if (n == 1) return false;
        int t = 0;
        for (int num : nums) t += num;
        int m = n / 2;
        List<Set<Integer>> p = new ArrayList<>();
        for (int i = 0; i <= m; i++) p.add(new HashSet<>());
        p.get(0).add(0);
        for (int num : nums) {
            for (int k = m; k >= 1; k--) {
                for (int s : p.get(k - 1)) {
                    p.get(k).add(s + num);
                }
            }
        }
        for (int k = 1; k <= m; k++) {
            if ((t * k) % n == 0) {
                int target = (t * k) / n;
                if (p.get(k).contains(target)) return true;
            }
        }
        return false;
    }
}