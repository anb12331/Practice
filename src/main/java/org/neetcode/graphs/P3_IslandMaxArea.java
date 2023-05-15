package org.neetcode.graphs;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

class P3_IslandMaxArea {
    public int maxAreaOfIsland(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;
        int[][] visited = new int[rows][cols];
        int maxIslandSize = 0;

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(grid[i][j] == 1 && visited[i][j] < 1) {
                    int islandSize = bfs(i,j, grid, visited);
                    if(islandSize > maxIslandSize) maxIslandSize = islandSize;
                }
            }
        }
        return maxIslandSize;
    }

    private int bfs(int i, int j, int[][] grid, int[][] visited) {
        Deque<int[]> q = new ArrayDeque<>();
        q.addLast(new int[] {i,j});
        int[][] offsets = {{0,1},{1,0},{0,-1},{-1,0}};
        int islandSize = 0;

        while(!q.isEmpty()) {

            int[] pt = q.pollFirst();
            if(visited[pt[0]][pt[1]] == 1)
                continue;
            visited[pt[0]][pt[1]] = 1;
            islandSize++;

            for(int[] offset: offsets) {
                int[] nbor = {pt[0] + offset[0], pt[1] + offset[1]};
                if(nbor[0] >= 0 && nbor[0] < rows &&
                        nbor[1] >= 0 && nbor[1] < cols &&
                        visited[nbor[0]][nbor[1]] < 1 &&
                        grid[nbor[0]][nbor[1]] == 1
                ) {
                    q.addLast(nbor);
                }
            }
        }

        return islandSize;
    }

    private int rows, cols;

    public static void main(String[] args) {
        int[][] grid = {
                {0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0}};
        int[][] grid2 = {{1,1,0,0,0},{1,1,0,0,0},{0,0,0,1,1},{0,0,0,1,1}};
        System.out.println(new P3_IslandMaxArea().maxAreaOfIsland(grid2));

    }

    public int maxAreaOfIsland_dfs(int[][] grid) {
        int count=0;
        for (int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1){
                    count=Math.max(count, dfs(grid,i,j));

                }}
        }
        return count;
    }

    private int dfs(int [][] image, int i, int j){
        if ((i>=image.length)||(j>=image[0].length)||(i<0)||(j<0)) return 0;
        if (image[i][j]!=1) return 0;
        image[i][j]=-1;
        return 1+dfs(image,i+1,j) +dfs(image,i,j+1)+dfs(image,i-1,j)+dfs(image,i,j-1);
    }
}
