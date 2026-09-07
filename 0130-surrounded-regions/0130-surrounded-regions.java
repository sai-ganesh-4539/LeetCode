class DSU {
    int[] parent;
    int[] size;
    DSU(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }
    int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
    void union(int a, int b) {
        int c = find(a);
        int d = find(b);
        if (c == d) return;
        if (size[c] < size[d]) {
            parent[c] = d;
            size[d] += size[c];
        } else {
            parent[d] = c;
            size[c] += size[d];
        }
    }
}
class Solution {
    public void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        // well, it is used for all boundary O's very useful yk 
        int dummy = m * n;
        DSU dsu = new DSU(m * n + 1);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != 'O') continue;
                int c = i * n + j;
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) dsu.union(c, dummy);
                //top
                if (i > 0 && board[i - 1][j] == 'O') dsu.union(c, (i - 1) * n + j);
                // left
                if (j > 0 && board[i][j - 1] == 'O') dsu.union(c, i * n + (j - 1));
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    int c = i * n + j;
                    if (dsu.find(c) != dsu.find(dummy)) board[i][j] = 'X';
                }
            }
        }
    }
}