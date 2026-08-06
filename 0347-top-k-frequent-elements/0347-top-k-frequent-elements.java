class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) map.put(num, map.getOrDefault(num, 0) + 1);
        ArrayList<Integer>[] b = new ArrayList[nums.length + 1];
        for (int key : map.keySet()) {
            int f = map.get(key);
            if (b[f] == null) b[f] = new ArrayList<>();
            b[f].add(key);
        }
        int[] a = new int[k];
        int x = 0;
        for (int i = b.length - 1; i >= 0 && x < k; i--) {
            if (b[i] != null) {
                for (int num : b[i]) {
                    a[x++] = num;
                    if (x == k) break;
                }
            }
        }
        return a;
    }
}