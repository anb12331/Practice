package org.neetcode.binarysearch;

class P2_2DMatrixSearch {
    public boolean searchMatrix(int[][] matrix, int target) {
        rowLen = matrix[0].length;
        return search(matrix, target, 0, matrix.length*rowLen) >= 0;
    }

    private int search(int[][] nums, int target, int start, int endEx) {
        if(get(nums, start) == target) return start;
        else if(get(nums,endEx - 1) == target) return endEx - 1;
        else if(start == endEx - 1 || start == endEx - 2)
            return -1; //exit if length is one or 2 and neither first or last elem matches

        int mid = (start + endEx - 1) / 2;
        if(target == get(nums,mid)) {
            return mid;
        } else if(target > get(nums,mid)) {
            return search(nums, target, mid, endEx);
        } else /*if(target < nums[mid])*/ {
            return search(nums, target, start, mid);
        }
    }

    private int rowLen;

    private int get(int[][] matrix, int flatIndex) {
        int row = flatIndex / rowLen;
        int col = flatIndex - row * rowLen;
        return matrix[row][col];
    }

    public static void main(String[] args) {
        System.out.println(new P2_2DMatrixSearch()
//                .searchMatrix(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}}, 13)
                .searchMatrix(new int[][]{{10}}, 13)
        );
    }
}
