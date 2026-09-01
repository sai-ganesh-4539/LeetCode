class Solution {
    public int minBishopMoves(int[] source, int[] target) {
        int x = source[0];
        int y = source[1];
        int a = target[0];
        int b = target[1];
        if ((x + y) % 2 != (a + b) % 2) return -1;
        if (Math.abs(x - a) == Math.abs(y - b)) return 1;
        return 2;
    }
}