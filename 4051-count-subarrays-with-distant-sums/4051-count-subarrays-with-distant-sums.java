class Solution {
    /*beautiful sum, instead of identifying or dividing each subarray, we can just take two
     prefix sums and just calculate the subarray sum using them. Perfect question for fenwick tree
     or merge sort.*/
    static long ans;
    public long distantSubarrays(int[] nums, int goal, int k) {
        int n = nums.length;
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        ans = 0;
        sort(prefix, 0, n, goal, k);
        return ans;
    }
    private static void sort(long[] arr, int l, int r, int goal, int k) {
        if (l < r) {
            int m = l + (r - l) / 2;
            sort(arr, l, m, goal, k);
            sort(arr, m + 1, r, goal, k);
            countPairs(arr, l, m, r, goal, k);
            merge(arr, l, m, r);
        }
    }
    private static void countPairs(long[] arr, int l, int m, int r, int goal, int k) {
        int p1 = l;
        int p2 = l;
        // sum >= goal + k
        // arr[j] - arr[i] >= goal + k
        // arr[i] <= arr[j] - (goal + k)
        for (int j = m + 1; j <= r; j++) {
            while (p1 <= m && arr[p1] <= arr[j] - ((long) goal + k)) {
                p1++;
            }
            ans += p1 - l;
        }
        // sum <= goal - k;
        // arr[j] - arr[i] <= goal - k
        // arr[i] >= arr[j] - (goal - k)
        for (int j = m + 1; j <= r; j++) {
            if (k == 0) {
                while (p2 <= m && arr[p2] <= arr[j] - ((long) goal - k)) {
                    p2++;
                }
            } else {
                while (p2 <= m && arr[p2] < arr[j] - ((long) goal - k)) {
                    p2++;
                }
            }
            ans += m - p2 + 1;
        }
    }
    private static void merge(long[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        long[] L = new long[n1];
        long[] R = new long[n2];
        System.arraycopy(arr, l, L, 0, n1);
        System.arraycopy(arr, m + 1, R, 0, n2);
        int i = 0;
        int j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = L[i];
            i++;k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            j++;k++;
        }
    }
}