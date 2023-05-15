package org.neetcode.math;

import java.util.Arrays;

class P3_RotateImage {
    public void rotate(int[][] matrix) {
        // row i -> col L - i
        int L = matrix.length - 1;
        int side = matrix.length;

        int[][] newMatrix = new int[side][side];

        for(int i = 0; i < side; i++) {
            for(int j = 0; j < side; j++) {
                if(matrix[i][j] > 1000) continue;
                //j2 -> L - i, i2 -> j
                //newMatrix[j][L - i] = matrix[i][j];
                int[] pt0 = {i,j};
                int[] pt1 = transform(pt0, side);
                int[] pt2 = transform(pt1, side);
                int[] pt3 = transform(pt2, side);

                int temp = matrix[i][j];
                matrix[i][j] = 3000 + matrix[pt3[0]][pt3[1]];
                matrix[pt3[0]][pt3[1]] = 3000 + matrix[pt2[0]][pt2[1]];
                matrix[pt2[0]][pt2[1]] = 3000 + matrix[pt1[0]][pt1[1]];
                matrix[pt1[0]][pt1[1]] = 3000 + temp;
            }
        }

        for(int i = 0; i < side; i++) {
            for(int j = 0; j < side; j++) {
                matrix[i][j] = matrix[i][j] - 3000;
            }
        }
    }

    private int[] transform(int[] pt, int side) {
        int L = side - 1;
        return new int[]{pt[1], L - pt[0]};
    }

    public static void print(int[][] matrix) {
        for(int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

    public static void main(String[] args) {
        int[][] nums = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        int[][] nums2 = new int[][]{{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
        P3_RotateImage m = new P3_RotateImage();
        m.rotate(nums2);
//        m.rotate(nums);
        m.print(nums2);
    }
}
