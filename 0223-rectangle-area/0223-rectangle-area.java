class Solution {
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int a = Math.abs(ax2 - ax1) * Math.abs(ay2 - ay1);
        int b = Math.abs(bx2 - bx1) * Math.abs(by2 - by1);
        int c = Math.min(ax2, bx2) - Math.max(ax1, bx1);
        int d = Math.min(ay2, by2) - Math.max(ay1, by1);
        if (c < 0 || d < 0) return a + b;
        return a + b - (c * d);
    }
}