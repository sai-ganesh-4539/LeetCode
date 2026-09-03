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
        DSU() {
            parent = new int[26];
            for (int i = 0; i < 26; i++) {
                parent[i] = i;
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
            if (c < d) parent[d] = c;
            else parent[c] = d;
            return true;
        }
        boolean isConnected(int x, int y) {
            return find(x) == find(y);
        }
    }
    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        DSU dsu = new DSU();
        for (int i = 0; i < s1.length(); i++) {
            int a = s1.charAt(i) - 'a';
            int b = s2.charAt(i) - 'a';
            dsu.union(a, b);
        }
        StringBuilder ans = new StringBuilder();
        for (char ch : baseStr.toCharArray()) {
            int x = ch - 'a';
            int root = dsu.find(x);
            ans.append((char)(root + 'a'));
        }
        return ans.toString();
    }
}