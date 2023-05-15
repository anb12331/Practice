package org.neetcode.graphs;

import java.util.*;

class P1_NumOfIslands {
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        rows = grid.length;
        cols = grid[0].length;
        Set<Integer> visited = new HashSet<>();
        int islands = 0;

        for(int r = 0; r < rows; r++) {
            for(int c = 0; c < cols; c++) {
                if(grid[r][c] == '1' && !visited.contains(enc(new int[] {r,c}))) {
                    bfs(grid, r, c, visited, rows, cols);
                    islands++;
                }
            }
        }

        return islands;
    }

    private int rows;
    private int cols;

    private Integer enc(int[] point) {
        return cols*point[0] + point[1];
    }


    private void bfs(char[][] grid, int r, int c, Set<Integer> visited, int rows, int cols) {
        Deque<int[]> q = new ArrayDeque<>();
        int[] point = new int[]{r,c};
        q.addLast(point);

        int[][] offsets = new int[][] {{0,1}, {0,-1}, {1,0}, {-1,0}};

        while(!q.isEmpty()) {
            int[] curr = q.pollFirst();
            visited.add(enc(curr));
            for(int[] o: offsets) {
                int[] nbor = new int[] {curr[0] + o[0], curr[1] + o[1]};
                if(nbor[0] >= 0 && nbor[1] >= 0 &&
                    nbor[0] < rows && nbor[1] < cols &&
                    !visited.contains(enc(nbor)) &&
                    grid[nbor[0]][nbor[1]] == '1') {
                    visited.add(enc(nbor));
                    q.addLast(nbor);
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new P1_NumOfIslands()
                .numIslands(new char[][]{
                      {'1','1','1','1','0'},
                      {'1','1','0','1','0'},
                      {'1','1','0','0','1'},
                      {'0','0','0','1','0'}}
                )
        );
        /*
        Deque<Integer> q = new ArrayDeque<>();
        q.add(1);
        q.add(2);
        q.add(3);
        q.addAll(List.of(4,5,6));
        q.addLast(7);
        q.addFirst(0);
        System.out.println(q);
        System.out.println("pollLast " + q.pollLast() + " " + q);
        System.out.println("pollFirst " + q.pollFirst() + " " + q);
        System.out.println("poll " + q.poll() + " " + q);
        System.out.println("pop " + q.pop() + " " + q);
        q.push(8);
        System.out.println("push 8 " + q);

         */

    }
}
