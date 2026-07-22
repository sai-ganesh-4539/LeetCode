class Solution {
    public int maximum69Number (int num) {
        String x = String.valueOf(num).replaceFirst("6", "9");
        return Integer.parseInt(x);
    }
}