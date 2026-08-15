class Solution {
    class Edge implements Comparable<Edge> {
        int src, dest, weight, index;
        Edge(int s, int d, int w, int i) {
            src = s;
            dest = d;
            weight = w;
            index = i;
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
        boolean union(int a, int b) {
            int c = find(a);
            int d = find(b);
            if (c == d) return false;
            if (rank[c] < rank[d]) parent[c] = d;
            else if (rank[c] > rank[d]) parent[d] = c;
            else {
                parent[d] = c;
                rank[c]++;
            }
            return true;
        }
    }
    int kruskal(int n, List<Edge> edges, int skip, int force) {
        DSU dsu = new DSU(n);
        int cost = 0;
        int count = 0;
        if (force != -1) {
            Edge e = edges.get(force);
            if (dsu.union(e.src, e.dest)) {
                cost += e.weight;
                count++;
            }
        }
        for(int i = 0; i < edges.size(); i++) {
            if (i == skip || i == force) continue;
            Edge e = edges.get(i);
            if (dsu.union(e.src, e.dest)) {
                cost += e.weight;
                count++;
            }
            if (count == n - 1) break;
        }
        if (count != n - 1) return Integer.MAX_VALUE;
        return cost;
    }
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        List<Edge> list = new ArrayList<>();
        for (int i = 0; i < edges.length; i++) {
            list.add(new Edge(
                edges[i][0],
                edges[i][1],
                edges[i][2],
                i
            ));
        }
        Collections.sort(list);
        int b = kruskal(n, list, -1, -1);
        List<Integer> c = new ArrayList<>();
        List<Integer> p = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            int without = kruskal(n, list, i, -1);
            if (without > b) {
                c.add(list.get(i).index);
                continue;
            }
            int with = kruskal(n, list, -1, i);
            if (with == b) {
                p.add(list.get(i).index);
            }
        }
        List<List<Integer>> answer = new ArrayList<>();
        answer.add(c);
        answer.add(p);
        return answer;
    }
}