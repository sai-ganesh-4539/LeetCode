class Solution {
    class BIT {
        int[] bit;
        BIT(int n) {
            bit = new int[n + 2];
        }
        void add(int i, int val) {
            while(i < bit.length) {
                bit[i] += val;
                i += i & -i;
            }
        }
        int sum(int i) {
            int r = 0;
            while(i > 0) {
                r += bit[i];
                i -= i & -i;
            }
            return r;
        }
    }
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int o = n + 2;
        BIT bit = new BIT(2 * n + 10);
        int p = 0;
        long a = 0;
        bit.add(o, 1);
        for (int x : nums) {
            if (x == target) p++;
            else p--;
            int i = p + o;
            a += bit.sum(i - 1);
            bit.add(i, 1);
        }
        return a;
    }
}