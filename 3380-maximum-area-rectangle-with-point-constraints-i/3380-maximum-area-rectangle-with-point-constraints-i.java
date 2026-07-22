class Solution {
    public int maxRectangleArea(int[][] points) {
        int n = points.length;
        int m = -1;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int x1 = points[i][0];
                int y1 = points[i][1];
                int x2 = points[j][0];
                int y2 = points[j][1];
                if (x1 == x2 || y1 == y2) continue;
                int l = Math.min(x1, x2);
                int r = Math.max(x1, x2);
                int b = Math.min(y1, y2);
                int t = Math.max(y1, y2);
                boolean c1 = false;
                boolean c2 = false;
                boolean c3 = false;
                boolean c4 = false;
                for (int[] p : points) {
                    int x = p[0];
                    int y = p[1];
                    if (x == l && y == b) c1 = true;
                    if (x == r && y == t) c2 = true;
                    if (x == l && y == t) c3 = true;
                    if (x == r && y == b) c4 = true;
                }
                if (!c1 || !c2 || !c3 || !c4) continue;
                boolean v = true;
                for (int k = 0; k < n; k++) {
                    int x = points[k][0];
                    int y = points[k][1];
                    boolean w = l <= x && x <= r && b <= y && y <= t;
                    boolean q = (x == l && y == b) || (x == l && y == t) || (x == r && y == b) || (x == r && y == t);
                    if (w && !q) {
                        v = false;
                        break;
                    }
                }
                if (v) {
                    int a = (r - l) * (t - b);
                    m = Math.max(m, a);
                }
            }
        }
        return m;
    }
}