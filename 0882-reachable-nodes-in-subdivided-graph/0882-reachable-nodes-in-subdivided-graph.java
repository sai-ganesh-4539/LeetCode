class Solution {
    public int reachableNodes(int[][] edges, int maxMoves, int n) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            graph.get(u).add(new int[]{v, w + 1});
            graph.get(v).add(new int[]{u, w + 1});
        }
        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1] - b[1]);
        pq.add(new int[]{0, 0});
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
        for (int i = 0; i < n; i++) {
            if (distance[i] <= maxMoves) ans++;
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            int U = 0;
            int V = 0;
            if (distance[u] <= maxMoves) U = maxMoves - distance[u];
            if (distance[v] <= maxMoves) V = maxMoves - distance[v];
            ans += Math.min(w, U + V);
        }
        return ans;
    }
}