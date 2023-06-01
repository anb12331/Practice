package org.neetcode.arrays;

class P7_ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        int[][] rowTracker = new int[9][9];
        int[][] colTracker = new int[9][9];
        int[][] subBoxTracker = new int[9][9];

        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                char currChar = board[i][j];
                if(currChar == '.') continue;

                int curr = currChar - '0' - 1; //0 based index
                if(rowTracker[i][curr] > 0) {
                    return false;
                } else {
                    rowTracker[i][curr] = 1;
                }

                if(colTracker[j][curr] > 0) {
                    return false;
                } else {
                    colTracker[j][curr] = 1;
                }

                int subBox = 3 * (i/3) + j/3;
                if(subBoxTracker[subBox][curr] > 0) {
                    return false;
                } else {
                    subBoxTracker[subBox][curr] = 1;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {

    }
}
