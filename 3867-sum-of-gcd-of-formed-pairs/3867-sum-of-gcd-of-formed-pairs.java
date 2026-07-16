class Solution {
    public long gcdSum(int[] nums) {
        int n = nums.length;
        int[] x = new int[n];
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, nums[i]);
            x[i] = gcd(nums[i], max);
        }
        Arrays.sort(x);
        long ans = 0;
        int l = 0, r = n - 1;
        while (l < r) {
            ans += gcd(x[l], x[r]);
            l++;
            r--;
        }
        return ans;
    }
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}