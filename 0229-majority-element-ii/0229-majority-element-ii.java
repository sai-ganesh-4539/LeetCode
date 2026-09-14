class Solution {
    public List<Integer> majorityElement(int[] nums) {
        // ok we will just find each and increment
        int x = 0;
        int y = 0;
        int a = 0;
        int b = 0;
        for (int num : nums) {
            if (num == x) a++;
            else if (num == y) b++;
            else if (a == 0) {
                x = num;
                a = 1;
            } else if (b == 0) {
                y = num;
                b = 1;
            } else {
                a--;
                b--;
            }
        }
        a = 0;
        b = 0;
        for (int num : nums) {
            if (num == x) a++;
            if (num == y) b++;
        }
        List<Integer> ans = new ArrayList<>();
        if (a > nums.length / 3) ans.add(x);
        if (y != x && b > nums.length / 3) ans.add(y);
        return ans;
    }
}