package org.neetcode.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class P9_NQueens {
    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        solveNQueensRec(0);
        return resStr;
    }

    public void solveNQueensRec(int row) {
        if(row == n) {
            addToRes();
        } else {
            for (int j = 0; j < n; j++) {
                if (isValidCol(j) && isValidDiagonal(row,j)) {
                    addTempRes(row, j);
                    solveNQueensRec(row + 1);
                    removeTempRes(row, j);
                }
            }
        }
    }

    private void removeTempRes(int i, int j) {
        queens.remove(queens.size() - 1);
        filledCols.remove(j);
    }

    List<int[]> queens = new ArrayList<>();

    private void addTempRes(int i, int j) {
        queens.add(new int[] {i,j});
        filledCols.add(j);
    }

    List<List<String>> resStr = new ArrayList<>();
    Set<Integer> filledCols = new HashSet<>();

    private boolean isValidDiagonal(int i, int j) {
        for(int[] q: queens) {
            if(Math.abs(q[0] - i) == Math.abs(q[1] - j))
                return false;
        }
        return true;
    }

    private boolean isValidCol(int j) {
        return !filledCols.contains(j);
    }

    int n;

    private void addToRes() {
        char[][] charRes = new char[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                charRes[i][j] = '.';
            }
        }
        for(int[] q: queens) {
            charRes[q[0]][q[1]] = 'Q';
        }
        List<String> res = new ArrayList<>();

        for(char[] resRow: charRes) {
            res.add(new String(resRow));
        }
        resStr.add(res);
    }

    int[][] res;

    public static void main(String[] args) {
        int n = 5;
        System.out.println(new P9_NQueens().solveNQueens(n));
    }
}
