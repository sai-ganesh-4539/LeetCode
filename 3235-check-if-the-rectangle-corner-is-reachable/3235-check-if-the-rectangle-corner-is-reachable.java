class Solution {
    // heavy math damn
    // but it is good
    // puts union find to its extreme limit
    static class DSU {
        int[] parent;
        int[] rank;
        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }
        void union(int a, int b) {
            int t = find(a), r = find(b);
            if (t == r) return;
            if (rank[t] < rank[r]) parent[t] = r;
            else if (rank[t] > rank[r]) parent[r] = t;
            else {
                parent[r] = t;
                rank[t]++;
            }
        }
        
    }
    public boolean canReachCorner(int xCorner, int yCorner, int[][] circles) {
        int n = circles.length;
        DSU dsu = new DSU(n + 4);
        int l = n;
        int r = n + 1;
        int b = n + 2;
        int u = n + 3;
        for (int i = 0; i < n; i++) {
            int x1 = circles[i][0];
            int y1 = circles[i][1];
            int r1 = circles[i][2];
            long dx = Math.max(0L, Math.max(-x1, x1 - xCorner));
            long dy = Math.max(0L, Math.max(-y1, y1 - yCorner));
            if (dx * dx + dy * dy > (long) r1 * r1) continue;
            long cly = Math.max(0, Math.min((long)y1, (long)yCorner));
            if ((long)x1*x1 + (y1-cly)*(y1-cly) <= (long)r1*r1) dsu.union(i,l);
            long clx = Math.max(0, Math.min((long)x1, (long)xCorner));
            if ((long)y1*y1 + (x1-clx)*(x1-clx) <= (long)r1*r1) dsu.union(i,b);
            long cry = Math.max(0, Math.min((long)y1, (long)yCorner));
            long rdx = x1 - xCorner;
            if (rdx*rdx + (y1-cry)*(y1-cry) <= (long)r1*r1) dsu.union(i,r);
            long tcx = Math.max(0, Math.min((long)x1, (long)xCorner));
            long tdy = y1 - yCorner;
            if (tdy*tdy + (x1-tcx)*(x1-tcx) <= (long)r1*r1) dsu.union(i,u);
            for (int j = 0; j < i; j++) {
                int x2 = circles[j][0];
                int y2 = circles[j][1];
                int r2 = circles[j][2];
                long d1 = x1 - x2;
                long d2 = y1 - y2;
                long R = r1 + r2;
                if (d1 * d1 + d2 * d2 <= R * R && seg(x1, y1, x2, y2, xCorner, yCorner)) dsu.union(i, j);
            }
        }
        if (dsu.find(l) == dsu.find(r)) return false;
        if (dsu.find(b) == dsu.find(u)) return false;
        if (dsu.find(l) == dsu.find(b)) return false;
        if (dsu.find(r) == dsu.find(u)) return false;
        return true;
    }
    boolean seg(long a, long b, long c, long u, long x, long y) {
        double p = 0, h = 1;
        long[] dv = {c - a, u - b};
        long[] mins = {-a, -b};
        long[] maxs = {x - a, y - b};
        for (int i = 0; i < 2; i++) {
            if (dv[i] == 0) {
                if (mins[i] > 0 || maxs[i] < 0) return false;
            } else {
                double t1 = (double) mins[i] / dv[i];
                double t2 = (double) maxs[i] / dv[i];
                if (t1 > t2) {
                    double temp = t1;
                    t1 = t2;
                    t2 = temp;
                }
                p = Math.max(p, t1);
                h = Math.min(h, t2);
                if (p > h) return false;
            }
        }
        return true;
    }   
}