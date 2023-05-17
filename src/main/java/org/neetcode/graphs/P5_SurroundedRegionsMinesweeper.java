package org.neetcode.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P5_SurroundedRegionsMinesweeper {
    public void solve(char[][] board1) {
        this.board = board1;
        rows = board.length;
        cols = board[0].length;
        visited = new int[rows][cols];


        for(int i = 0; i < rows; i++)
            for(int j = 0; j < cols; j ++) {
                if(visited[i][j] != 1 && board[i][j] == 'O') {
                    islandPts = new ArrayList<>();
                    touchesBorder = false;
                    dfs(i, j);

                    if(!touchesBorder) {
                        for(int[] oPoint: islandPts) {
                            board[oPoint[0]][oPoint[1]] = 'X';
                        }
                    }
                }
            }
    }

    int[][] offsets = {{0,1}, {1,0}, {0, -1}, {-1, 0}};

    private void dfs(int i, int j) {
        if(visited[i][j] == 1) return;
        visited[i][j] = 1;
        islandPts.add(new int[] {i,j});

        for(int[] ofs: offsets) {
            int iN = i + ofs[0];
            int jN = j + ofs[1];

            if(iN < 0 || iN >= rows || jN < 0 || jN >= cols) {
                touchesBorder = true;
            } else if(visited[iN][jN] != 1 && board[iN][jN] == 'O') {
                dfs(iN, jN);
            }
        }

    }

    char[][] board;

    boolean touchesBorder = false;
    List<int[]> islandPts;

    int rows, cols;

    int[][] visited;

    public static void main(String[] args) {
        char[][] board = {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
        new P5_SurroundedRegionsMinesweeper().solve(board);
        for(char[] r: board) System.out.println(Arrays.toString(r));
    }
}
