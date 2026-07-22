class Solution {
    public int maximum69Number (int num) {
        char[] x = String.valueOf(num).toCharArray();
        for (int i = 0; i < x.length; i++) {
            if (x[i] == '6') {
                x[i] = '9';
                break;
            }
        }
        return Integer.parseInt(new String(x));
    }
}