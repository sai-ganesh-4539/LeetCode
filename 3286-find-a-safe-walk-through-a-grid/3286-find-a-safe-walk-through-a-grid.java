class Solution {
    /* well the bad part is we cannot use union find, it only answers whether two nodes are connected or not, but it cannot give a pathwhose total cost is less than the health, so we can't use union find as before one.
    so, this is like
    move into 0 -> cost = 0;
    move into 1 -> cost = 1;
    maybe this is like finding the shortest path
    we can use bfs or dijkstra let's see. */
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        /* we will use deque instead of priority queue because
        if the move costs 0, we should process it immediately because it doesn't increase the total cost. 
        move to safe cell -> costs 0
        move into a unsafe cell -> costs 1. */
        int m = grid.size();
        int n = grid.get(0).size();
        // dist[i][j] means minimum health lost to reach cell (i,j)
        int[][] dist = new int[m][n];
        // initially each cell is unreachable, Integer.MAX_VALUE is infinity sort of
        for (int[] x : dist) Arrays.fill(x, Integer.MAX_VALUE);
        Deque<int[]> q = new ArrayDeque<>(); // initializing deque
        // as we will insert at the front and sometimes at the back, we use deque here.
        dist[0][0] = grid.get(0).get(0);
        /* suppose the grid is 
           1 0 
           0 0  then the starting cell is unsafe, you immediately lose one health.
           so dist[0][0] = 1, if the start is 0 then dist[0][0] = 0;
        */
        q.offerFirst(new int[]{0,0}); // put the starting cell into the deque.
        int[] dr = {-1,1,0,0};
        int[] dc = {0,0,-1,1};
        /* for each k = 0,1,2,3 it represents up, down, left, right.
        current = (r,c); up = (r - 1, c); down = (r + 1, c);.
        */
        // process cells until nothing is left.
        while (!q.isEmpty()) {
            int[] a = q.pollFirst(); // take the front cell.
            /* suppose deque = [(1,2), (0,3), (2,1)] after pollFirst() current = (1,2) and
               deque becomes = [(0,3), (2,1)]
            */
            int r = a[0], c = a[1]; // extracting co-ordinates
            // visit all four neigbours
            for (int k = 0; k < 4; k++) {
                // compute neighbours
                int nr = r + dr[k];
                int nc = c + dc[k];
                /* suppose r = 2, c = 3 
                   for Up -> nr = 1, nc = 3;
                */
                // ignore neighbours outside the grid
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                // like at (0,0) up becomes (-1,0) which is invalid so skip it.
                int w = grid.get(nr).get(nc); //weight of entering this neighbour(possible values = 0 or 1)
                // current cost + cost of entering neighbour.
                int newCost = dist[r][c] + w;
                if (newCost < dist[nr][nc]) { // have we found the better way.
                    dist[nr][nc] = newCost; // store the better answer.
                    // maintain nodes in non - decreasing order of distance.
                    if (w == 0) q.offerFirst(new int[]{nr, nc}); // same distance.
                    else q.offerLast(new int[]{nr, nc}); // distance + 1.
                }
            }
        }
        // Health after reaching destination = health - minimum cost.
        // it must remain > 0.
        return dist[m - 1][n - 1] < health;
    }
}