class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        int s = start_node;
        int e = end_node;
        List<List<double[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            double p = succProb[i];
            graph.get(u).add(new double[]{v, p});
            graph.get(v).add(new double[]{u, p});
        }
        double[] x = new double[n];
        x[s] = 1.0;
        PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> Double.compare(b[1], a[1]));
        pq.add(new double[]{s, 1.0});
        while (!pq.isEmpty()) {
            double[] curr = pq.poll();
            int node = (int) curr[0];
            double p = curr[1];
            if (p < x[node]) continue;
            if (node == e) return p;
            for (double[] edge : graph.get(node)) {
                int next = (int) edge[0];
                double ep = edge[1];
                double np = p * ep;
                if (np > x[next]) {
                    x[next] = np;
                    pq.add(new double[]{next, np});
                }
            }
        }
        return 0.0;
    }
}