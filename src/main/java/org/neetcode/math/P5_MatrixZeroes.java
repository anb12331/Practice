package org.neetcode.math;

import java.util.Arrays;

class P5_MatrixZeroes {
    public void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[] rowHasZero = new int[rows];
        int[] colHasZero = new int[cols];

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(matrix[i][j] == 0) {
                    rowHasZero[i] = 1;
                    colHasZero[j] = 1;
                }
            }
        }

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(rowHasZero[i] == 1 || colHasZero[j] == 1) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,1,1},{1,0,1},{1,1,1}};

        new P5_MatrixZeroes().setZeroes(matrix);

        for(int[] row: matrix)
            System.out.println(Arrays.toString(row));
    }
}
