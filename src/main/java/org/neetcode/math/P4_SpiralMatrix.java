package org.neetcode.math;

import java.util.ArrayList;
import java.util.List;

class P4_SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        int visitMark = 300, isVisited = 100;
        List<Integer> res = new ArrayList<>();
        int rows = matrix.length;
        int cols = matrix[0].length;
        Direction d = new Direction();
        int len = rows*cols;
//        for(int i = 0; i < rows; i++) {
//            for (int j = 0; j < cols; j++) {
        int i = 0, j = 0;
        while(res.size() < len) {
            res.add(matrix[i][j]);
            matrix[i][j] += visitMark;
            int i_new = i + d.i, j_new = j + d.j;

            if(i_new >= rows || i_new < 0 || j_new >= cols || j_new < 0 || matrix[i_new][j_new] > isVisited) {
                d.rotate90Clock();
                i_new = i + d.i; j_new = j + d.j;
            }

            i = i_new; j = j_new;
//
//            if(i_new >= rows || j_new >= cols || matrix[i_new][j_new] > isVisited) {
//                return res;
//            }

        }

        return res;
    }

    class Direction {
        int i=0, j=1;
        void rotate90Clock() {
            int temp_i = i;
             i = j;
             j = -temp_i;
//            if(i == 0 && j == 1) {
//                i = 1; j = 0;
//            } else if(i == 1 && j == 0) {
//                i = 0; j = -1;
//            } else if(i == 0 && j == -1) {
//                i = -1; j = 0;
//            } else if(i == -1 && j == 0) {
//                i = 0; j = 1;
//            }
        }
    }

    public static void main(String[] args) {
//        int[][] nums = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        int[][] nums = new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        List<Integer> res = new P4_SpiralMatrix().spiralOrder(nums);

        System.out.println(res);
    }
}
