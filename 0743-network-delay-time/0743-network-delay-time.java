class Solution {
    // classic Dijkstra
    public int networkDelayTime(int[][] times, int n, int k) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : times) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            graph.get(u).add(new int[]{v, w});
        }
        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[k] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{k, 0});
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0];
            int dist = curr[1];
            if (dist > distance[node]) continue;
            for (int[] edge : graph.get(node)) {
                int next = edge[0];
                int weight = edge[1];
                int newDist = dist + weight;
                if (newDist < distance[next]) {
                    distance[next] = newDist;
                    pq.add(new int[]{next, newDist});
                }
            }
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (distance[i] == Integer.MAX_VALUE) return -1;
            ans = Math.max(ans, distance[i]);
        }
        return ans;
    }
}