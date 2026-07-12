class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int[] x = Arrays.copyOf(arr, arr.length);
        Arrays.sort(x);
        Map<Integer, Integer> rank = new HashMap<>();
        int r = 1;
        for (int num : x) {
            if (!rank.containsKey(num)) rank.put(num, r++);
        }
        for (int i = 0; i < arr.length; i++) arr[i] = rank.get(arr[i]);
        return arr;
    }
}