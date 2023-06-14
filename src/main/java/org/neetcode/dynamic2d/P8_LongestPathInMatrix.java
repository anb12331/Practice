package org.neetcode.dynamic2d;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class P8_LongestPathInMatrix {
    public int longestIncreasingPath(int[][] matrix) {
        rows = matrix.length;
        cols = matrix[0].length;
        dp = new int[rows][cols];
        this.matrix = matrix;

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                dfs(i, j, -1);
            }
        }

        return longestPath;
    }

    int longestPath = 0;
    int[][] matrix;
    /*
    [[7,7,5]
    ,[2,4,6]
    ,[8,2,0]]
     */
    private int dfs(int i, int j, int prev) {
        if(i < 0 || i >= rows || j < 0 || j >= cols) return 0;

        int curr = matrix[i][j];
        if(curr <= prev) return 0;

        if(dp[i][j] > 0) return dp[i][j];

        int p = dfs(i + 1, j, curr);
        p = Math.max(p, dfs(i - 1, j, curr));
        p = Math.max(p, dfs(i, j + 1, curr));
        p = Math.max(p, dfs(i, j - 1, curr));

        int res = 1 + p;
        longestPath = Math.max(res, longestPath);
        dp[i][j] = res;
        return res;
    }

    int rows;
    int cols;

    int[][] dp;

    public static void main(String[] args) {
        int[][] matrix = {{9,9,4},{6,6,8},{2,1,1}};
        matrix = new int[][] {{1,2}};
        matrix = new int[][] {{7,7,5},{2,4,6},{8,2,0}};
        System.out.println(new P8_LongestPathInMatrix().longestIncreasingPath(matrix));
    }
}
