class Solution {
    class Edge implements Comparable<Edge> {
        int src, dest, weight;
        Edge(int s, int d, int w) {
            src = s;
            dest = d;
            weight = w;
        }
        @Override
        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
    }
    class DSU {
        int[] parent, rank;
        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }
        int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }
        void union(int a, int b) {
            int c = find(a);
            int d = find(b);
            if (c == d) return;
            if (rank[c] < rank[d]) parent[c] = d;
            else if (rank[d] < rank[c]) parent[d] = c;
            else {
                parent[d] = c;
                rank[c]++;
            }
        }
        boolean isConnected(int x, int y) {
            return find(x) == find(y);
        }
    }
    public int minCostConnectPoints(int[][] points) {
        int V = points.length;
        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            for (int j = i + 1; j < V; j++) {
                int w = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                edges.add(new Edge(i, j, w));
            }
        }
        Collections.sort(edges);
        DSU dsu = new DSU(V);
        int totalWeight = 0;
        List<Edge> mst = new ArrayList<>();
        for (Edge edge : edges) {
            if (!dsu.isConnected(edge.src, edge.dest)) {
                dsu.union(edge.src, edge.dest);
                mst.add(edge);
                totalWeight += edge.weight;
                if (mst.size() == V - 1) break;
            }
        }
        return totalWeight;
    }
}