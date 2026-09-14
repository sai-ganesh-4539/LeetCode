class Solution {
    /* dijkstra is better choice because O(n * E log n)
     where Floyd - Warshall is O(n^3) and Bellman - Ford is O(n^2 E)*/
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            graph.get(u).add(new int[]{v,w});
            graph.get(v).add(new int[]{u,w});
        }
        int ans = -1;
        int mC = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int[] d = new int[n];
            Arrays.fill(d, Integer.MAX_VALUE);
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
            pq.add(new int[]{i, 0});
            while (!pq.isEmpty()) {
                int[] curr = pq.poll();
                int node = curr[0];
                int dist = curr[1];
                if (dist > d[node]) continue;
                for (int[] edge : graph.get(node)) {
                    int next = edge[0];
                    int weight = edge[1];
                    int newDist = dist + weight;
                    if (newDist <= d[next] && newDist <= distanceThreshold) {
                        d[next] = newDist;
                        pq.add(new int[]{next, newDist});
                    }
                }
            }
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (j != i && d[j] <= distanceThreshold) count++;
            }
            if (count <= mC) {
                mC = count;
                ans = i;
            }
        }
        return ans;
    }
}