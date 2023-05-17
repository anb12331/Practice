package org.neetcode.dynamic2d;

public class P1_UniquePaths {
    public int uniquePaths(int m, int n) {
        this.m = m; this.n = n;
        paths = new int[m][n];
        paths[m-1][n-1] = 1;
        for(int i = m - 1; i >= 0; i--)
            for(int j = n - 1; j >= 0; j--) {
                calcNborPaths(i, j);
            }

        return paths[0][0];
    }

    private int[][] paths;

    private int m, n;

    private int calcNborPaths(int i, int j) {
        if( i < 0 || i >= m || j < 0 || j >= n) return 0;
        if(paths[i][j] > 0) return paths[i][j];

        int currPaths = calcNborPaths(i + 1, j) + calcNborPaths(i, j + 1);
        this.paths[i][j] = currPaths;
        return currPaths;
    }

    public static void main(String[] args) {
        System.out.println(new P1_UniquePaths().uniquePaths(3,2));
    }
}
