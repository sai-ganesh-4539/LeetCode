class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : invocations) {
            graph[edge[0]].add(edge[1]);
        }
        boolean[] sus = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        q.offer(k);
        sus[k] = true;
        while (!q.isEmpty()) {
            int c = q.poll();
            for (int next : graph[c]){
                if (!sus[next]) {
                    sus[next] = true;
                    q.offer(next);
                }
            }
        }
        for (int[] edge : invocations) {
            if (!sus[edge[0]] && sus[edge[1]]) {
                List<Integer> ans = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    ans.add(i);
                }
                return ans;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!sus[i]) ans.add(i);
        }
        return ans;
    }
}