class Solution {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        for (int[] d : dislikes) {
            int a = d[0];
            int b = d[1];
            graph[a].add(b);
            graph[b].add(a);
        }
        int[] color = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (color[i] != 0) continue;
            Queue<Integer> q = new LinkedList<>();
            color[i] = 1;
            q.add(i);
            while (!q.isEmpty()) {
                int curr = q.poll();
                for (int next : graph[curr]) {
                    if (color[next] == 0) {
                        color[next] = -color[curr];
                        q.add(next);
                    } else if (color[next] == color[curr]) return false;
                }
            }
        }
        return true;
    }
}