class Solution {
    // using quickselect will be better during interviews as it give O(n).
    // whereas heap would give O(nlogk). So, prefer quickselect during interviews.
    /* well there's a disgusting testcase and it causes imbalance as we are taking last element as pivot, so let's use 3-way partition */
    public int findKthLargest(int[] nums, int k) {
        int x = nums.length - k;
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int p = nums[l + (r - l) / 2];
            int low = l;
            int i = l;
            int high = r;
            while (i <= high) {
                if (nums[i] < p) {
                    swap(nums, i, low);
                    i++;
                    low++;
                } else if (nums[i] > p) {
                    swap(nums, i, high);
                    high--;
                } else {
                    i++;
                }
            }
            if (x < low) {
                r = low - 1;
            } else if (x > high) {
                l = high + 1;
            } else {
                return nums[x];
            }
        }
        return -1;
    }
    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}