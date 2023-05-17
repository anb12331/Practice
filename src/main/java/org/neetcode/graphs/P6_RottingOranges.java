package org.neetcode.graphs;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class P6_RottingOranges {
    public int orangesRotting(int[][] grid) {
        int rows = grid.length; int cols = grid[0].length;
        int time = 0;
        Deque<int[]> q = new ArrayDeque<>();

        for(int i = 0; i < rows; i++)
            for(int j = 0; j < cols; j++) {
                if(grid[i][j] == 2) {
                    q.add(new int[] {i,j,0});
                }
            }

        int[][] offsets = {{0,1},{1,0},{0,-1},{-1,0}};

        while(!q.isEmpty()) {
            int[] pt = q.pollFirst();
            int i = pt[0]; int j = pt[1];
            int time1 = pt[2];
            if(grid[i][j] == 1) {
                time = Math.max(time, time1);
                grid[i][j] = 2;
            }

            for(int[] ofs: offsets) {
                int iN = i + ofs[0];
                int jN = j + ofs[1];

                if(iN >= 0 && iN < rows && jN >= 0 && jN < cols &&
                    grid[iN][jN] == 1) {
                    q.add(new int[] {iN, jN, time1 + 1});
                }
            }
        }

        for(int i = 0; i < rows; i++)
            for(int j = 0; j < cols; j++) {
                if(grid[i][j] == 1) {
                    return -1;
                }
            }

        return time;
    }



    public static void main(String[] args) {
        int[][] grid1 = {{2,1,1},{1,1,0},{0,1,1}};
        int[][] grid2 = {{2,1,1},{0,1,1},{1,0,1}};
        int[][] grid = {{2,2},{1,1},{0,0},{2,0}};
        System.out.println(new P6_RottingOranges().orangesRotting(grid));
        for(int[] r: grid) System.out.println(Arrays.toString(r));
    }
}
