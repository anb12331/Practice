package org.neetcode.graphs;

import java.util.*;

class P7_WallsAndGates {
    public void wallsAndGates(int[][] rooms) {
        this.rooms = rooms;
        rows = rooms.length; cols = rooms[0].length;

        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(rooms[i][j] == 0) {
                    dfs(i, j, 0);
                    return;
                }
            }
        }
    }

//    private Set<int[]> gates;

    private int[][] rooms;
    private int rows;
    private int cols;

    private void dfs(int i, int j, int dist) {

        if(i >= 0 && i < rows && j >= 0 && j < cols) {
            dist++;
            if(rooms[i][j] == 0) {
                dist = 0;
            } else if(rooms[i][j] > dist) {
                rooms[i][j] = dist;
            } else {
                // room is -1 or lte dist
                return;
            }

            dfs(i + 1, j, dist);
            dfs(i, j + 1, dist);
            dfs(i - 1, j, dist);
            dfs(i, j - 1, dist);
        }
    }

    public static void main(String[] args) {
        int[][] rows = {
                {2147483647,-1,0,2147483647},
                {2147483647,2147483647,2147483647,-1},
                {2147483647,-1,2147483647,-1},
                {0,-1,2147483647,2147483647}
        };

        int[][] rows1 = {
                {0, -1},
                {2147483647, 2147483647}
        };

        new P7_WallsAndGates().wallsAndGates(rows);

        for(int[] row: rows) {
            System.out.println(Arrays.toString(row));
        }
    }

    public void wallsAndGates_bestSol(int[][] rooms) {
        //1. BFS - add all 0 nodes to q,
        //2. do bfs on 0 nodes get 1 nodes, added to q
        //3. do BFS on 1 nodes to get 2 nodes and so on...

        Queue<int[]> q = new LinkedList<>();
        int m = rooms.length;
        int n = rooms[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) q.add(new int[] { i, j });
            }
        }
        if (q.size() == 0) return;
        int[][] dirs = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
        int dis = 0;
        while (!q.isEmpty()) {
            ++dis;
            int[] cur = q.poll();
            int row = cur[0];
            int col = cur[1];
            for (int[] dir : dirs) {
                int x = row + dir[0];
                int y = col + dir[1];
                if (
                        x >= m ||
                                y >= n ||
                                x < 0 ||
                                y < 0 ||
                                rooms[x][y] != Integer.MAX_VALUE
                ) continue;
                q.add(new int[] { x, y });
                //since cur is basically the index of door (which is equal to 0)
                //So, we can just grab that value (rooms[row][col]) and add 1 to it and change it every time
                rooms[x][y] = rooms[row][col] + 1;
                //So, one level further from door (value 0) is equal to 1. Now, we do bfs from that position so value will be 2 and so on.
            }
        }

    }
}
