class Solution {
    class DSU {
        int[] parent, rank;
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
            int c = find(a);
            int d = find(b);
            if (c == d) return;
            if (rank[c] < rank[d]) parent[c] = d;
            else if (rank[c] > rank[d]) parent[d] = c;
            else {
                parent[d] = c;
                rank[c]++;
            }
        }
    }
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        DSU dsu = new DSU(n);
        HashMap<String, Integer> etA = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<String> a = accounts.get(i);
            for (int j = 1; j < a.size(); j++) {
                String email = a.get(j);
                if (!etA.containsKey(email)) etA.put(email, i);
                else dsu.union(i, etA.get(email));
            }
        }
        HashMap<Integer, TreeSet<String>> g = new HashMap<>();
        for (String email : etA.keySet()) {
            int root = dsu.find(etA.get(email));
            g.putIfAbsent(root, new TreeSet<>());
            g.get(root).add(email);
        }
        List<List<String>> ans = new ArrayList<>();
        for (int root : g.keySet()) {
            List<String> merged = new ArrayList<>();
            merged.add(accounts.get(root).get(0));
            merged.addAll(g.get(root));
            ans.add(merged);
        }
        return ans;
    }
}