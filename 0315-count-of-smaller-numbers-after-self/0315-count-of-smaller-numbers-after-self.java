class Solution {
    class Pair {
        int val;
        int index;
        Pair(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }
    private static void merge(Pair[] arr, int l, int m, int r, int[] ans) {
        int n1 = m - l + 1;
        int n2 = r - m;
        Pair[] L = new Pair[n1];
        Pair[] R = new Pair[n2];
        System.arraycopy(arr, l, L, 0, n1);
        System.arraycopy(arr, m + 1, R, 0, n2);
        int i = 0;
        int j = 0;
        int k = l;
        int count = 0;
        while (i < n1 && j < n2) {
            if (L[i].val <= R[j].val) {
                ans[L[i].index]+= count;
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
                count++;
            }
            k++;
        }
        while (i < n1) {
            ans[L[i].index] += count;
            arr[k] = L[i];
            i++;k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            j++;k++;
        }
    }
    private static void sort(Pair[] arr, int l, int r, int[] ans) {
        if (l < r) {
            int m = l + (r - l) / 2;
            sort(arr, l, m, ans);
            sort(arr, m + 1, r, ans);
            merge(arr, l, m, r, ans);
        }
    }
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        Pair[] arr = new Pair[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Pair(nums[i], i);
        }
        int[] ans = new int[n];
        sort(arr, 0, n - 1, ans);
        List<Integer> x = new ArrayList<>();
        for (int t : ans) x.add(t);
        return x;
    }
}