class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> ans = new ArrayList<>();
        String s = "123456789";
        int min = String.valueOf(low).length();
        int max = String.valueOf(high).length();
        for (int l = min; l <= max; l++) {
            for (int i = 0; i + l <= 9; i++) {
                int num = Integer.parseInt(s.substring(i, i + l));
                if (num >= low && num <= high) ans.add(num);
            }
        }
        return ans;
    }
}