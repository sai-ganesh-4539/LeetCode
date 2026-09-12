class Solution {
    //entra naaku idhi
    class Node {
        int l, r, id;
        long w;
        Node (int l, int r, int w, int id) {
            this.l = l;
            this.r = r;
            this.w = w;
            this.id = id;
        }
    }
    class Pair {
        long s;
        List<Integer> a;
        Pair(long s, List<Integer> a) {
            this.s = s;
            this.a = a;
        }
    }
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        Node[] a = new Node[n];
        for (int i = 0; i < n; i++) {
            a[i] = new Node(intervals.get(i).get(0),intervals.get(i).get(1), intervals.get(i).get(2),i);
        }
        Arrays.sort(a, (x, y) -> x.r - y.r);
        int[] prev = new int[n];
        for (int i = 0; i < n; i++) {
            int l = 0;
            int r = i - 1;
            prev[i] = -1;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (a[mid].r < a[i].l) {
                    prev[i] = mid;
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        /*this is similar to 0/1 knapsack, for each interval, we either take it or skip it.
        But this is not 0/1 knapsack, it is weighted interval scheduling limit 4 intervals
        and binary search is used to find the last non-overlapping inteerval.*/
        Pair[][] dp = new Pair[n + 1][5];
        for (int i = 0; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                dp[i][k] = new Pair(0, new ArrayList<>());
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int k = 1; k <= 4; k++) {
                Pair x = dp[i - 1][k];
                Pair y = dp[prev[i - 1] + 1][k - 1];
                List<Integer> b = new ArrayList<>(y.a);
                b.add(a[i - 1].id);
                Collections.sort(b);
                long s = y.s + a[i - 1].w;
                if (s > x.s) dp[i][k] = new Pair(s, b);
                else if (s < x.s) dp[i][k] = x;
                else {
                    if (cmp(b, x.a) < 0) dp[i][k] = new Pair(s, b);
                    else dp[i][k] = x;
                }
            }
        }
        List<Integer> ans = dp[n][4].a;
        int[] result = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            result[i] = ans.get(i);
        }
        return result;
    }
    int cmp(List<Integer> a, List<Integer> b) {
        for (int i = 0; i < Math.min(a.size(), b.size());i++) {
            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) - b.get(i);
            }
        } 
        return a.size() - b.size();
    }
}