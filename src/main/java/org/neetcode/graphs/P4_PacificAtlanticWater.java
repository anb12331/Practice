package org.neetcode.graphs;

import java.util.*;

class P4_PacificAtlanticWater {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        rows = heights.length;
        cols = heights[0].length;
        visited = new int[rows][cols];
        res = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(i == 11 && j == 3) {
                    int debug = 1;
                }

                if(visited[i][j] != 3) {
                    dfs(i, j, heights);
                }
            }
        }

        return res;
    }

    List<List<Integer>> res;

    private int[][] offsets = {{0,1},{1,0},{0,-1},{-1,0}};

    private boolean[] dfs(int i, int j, int[][] heights) {
        visited[i][j] = -1;

        boolean flowsToPacific = false;
        boolean flowsToAtlantic = false;

        if(i == 11 && j == 3) {
            int debug = 1;
        }

        if(i == 1 && j == 4) {
            int debug = 1;
        }

        for(int[] ofs: offsets) {
            int iN = i + ofs[0];
            int jN = j + ofs[1];
            if(iN < 0 || jN < 0) {
                flowsToPacific = true;
            } else if (iN >= rows || jN >= cols) {
                flowsToAtlantic = true;
            }

            if(flowsToAtlantic && flowsToPacific) {
                if(i == 4 && j == 0) {
                    int debug = 1;
                }
                visited[i][j] = 3;
                res.add(Arrays.asList(i,j));
                return new boolean[] {true, true};
            }
        }

        boolean[] thisFlows = new boolean[] {flowsToAtlantic, flowsToPacific};

        for(int[] ofs: offsets) {
            int iN = i + ofs[0];
            int jN = j + ofs[1];
            if( iN >= 0 && iN < rows &&
                jN >= 0 && jN < cols &&
                visited[iN][jN] != -1 &&
                heights[iN][jN] <= heights[i][j]) {
                int visitCode = visited[iN][jN];
                if(visitCode == 1) thisFlows[0] = true;
                else if (visitCode == 2) thisFlows[1] = true;
                else if (visitCode == 3) {
                    thisFlows[0] = true;
                    thisFlows[1] = true;
                } else if (visitCode == 0) {
                    boolean[] flows = dfs(iN, jN, heights);
                    if(flows[0]) thisFlows[0] = true;
                    if(flows[1]) thisFlows[1] = true;
                }
            }
        }

        if(thisFlows[0] && thisFlows[1]) {
            if(i == 4 && j == 0) {
                int debug = 1;
            }
            res.add(Arrays.asList(i, j));
            visited[i][j] = 3;
        } else if (thisFlows[0]) {
            visited[i][j] = 1;
        } else if (thisFlows[1]) {
            visited[i][j] = 2;
        } else {
            visited[i][j] = -1;
        }

        return thisFlows;
    }

    private boolean bfs_wrong(int i, int j, int[][] heights) {
        Deque<int[]> q = new ArrayDeque<>();
        q.addLast(new int[] {i,j});
        boolean flowsToPacific = false;
        boolean flowsToAtlantic = false;

        while(!q.isEmpty() || (flowsToAtlantic && flowsToPacific)) {
            int[] pt = q.getFirst();
            int iV = pt[0], jV = pt[1];
            if(visited[iV][jV] == 1 )
                continue;
            visited[iV][jV] = 1;
            int height = heights[iV][jV];

            for(int[] ofs: offsets) {
                int iN = iV + ofs[0];
                int jN = jV + ofs[1];
                if(visited[iN][jN] != 1 &&
                    heights[iN][jN] <= height
                ) {
                    if(iN < 0 || jN < 0) {
                        flowsToPacific = true;
                    }

                    if (iN >= rows || jN >= cols) {
                        flowsToAtlantic = true;
                    }
                    q.addLast(new int[]{iN, jN});
                }
                if(flowsToPacific && flowsToAtlantic)
                    return true;
            }

        }
        return false;
    }

    int rows, cols;

    int[][] visited;

    public static void main(String[] args) {
        int[][] heights = {{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
        System.out.println(new P4_PacificAtlanticWater().pacificAtlantic(heights2));
    }

    static int[][] heights2 =
    {{7,1,17,13,9,10,5,14,0,3},{7,15,7,8,15,16,10,10,5,13},{18,9,15,8,19,16,7,5,5,10},{15,11,18,3,1,17,6,4,10,19},
    {3,16,19,12,12,19,2,14,5,9},{7,16,0,13,14,7,2,8,6,19},{5,10,1,10,2,12,19,1,0,19},{13,18,19,12,17,17,4,5,8,2},
    {2,1,17,13,14,12,14,2,16,10},{5,8,1,11,16,1,18,15,6,19},{3,8,14,14,5,0,2,7,5,1},{17,1,9,17,10,10,10,7,1,16},
    {14,18,5,11,17,15,8,8,14,13},{6,4,10,17,8,0,11,4,2,8},{16,11,17,9,3,2,11,0,6,5},{12,18,18,11,1,7,12,16,12,12},
    {2,14,12,0,2,8,5,10,7,0},{16,13,1,19,8,13,11,8,11,3},{11,2,8,19,6,14,14,6,16,12},{18,0,18,10,16,15,15,12,4,3},
    {8,15,9,13,8,2,6,11,17,6},{7,3,0,18,7,12,2,3,12,10},{7,9,13,0,11,16,9,9,12,13},{9,4,19,6,8,10,12,6,7,11},
    {5,9,18,0,4,9,6,4,0,1},{9,12,1,11,13,13,0,16,0,6},{7,15,4,8,15,17,17,19,15,1},{7,17,4,1,1,14,10,19,10,19},
    {10,5,12,5,8,8,14,14,6,0},{16,10,10,7,13,4,0,15,18,0},{11,2,10,6,5,13,4,5,3,1},{9,14,16,14,15,3,2,13,17,8},
    {19,2,10,1,2,15,12,10,2,5},{12,4,8,9,8,6,4,14,14,0},{11,17,17,4,16,13,6,15,5,7},{12,18,1,3,9,10,7,1,1,1},
    {18,6,10,8,12,14,9,12,10,3},{15,13,18,13,8,5,12,14,18,0},{15,4,8,9,19,18,6,19,12,0},{4,14,15,4,17,17,9,17,9,0},
    {6,17,16,10,3,8,8,18,15,9},{3,8,4,2,13,0,2,8,8,2},{14,12,13,12,17,4,16,9,8,7},{0,19,8,16,1,13,7,6,15,11},
    {1,13,16,14,10,4,11,19,9,13},{8,0,2,1,16,12,16,9,9,9},{5,2,10,4,8,12,17,0,2,15},{11,2,15,15,14,9,11,19,18,11},
    {4,4,1,5,13,19,9,17,17,17},{4,1,8,0,8,19,11,0,5,4},{8,16,14,18,12,2,0,19,0,13},{7,11,3,18,8,2,2,19,8,7},
    {3,13,6,1,12,16,4,13,0,5},{12,1,16,19,2,12,16,15,19,6},{1,7,12,15,3,3,13,17,16,12}};

}
