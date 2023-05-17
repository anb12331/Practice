package org.neetcode.backtracking;

public class P6_WordSearch {
    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.targetChars = word.toCharArray();
        rows = board.length; cols = board[0].length;
        visited = new int[rows][cols];

        for(int i = 0; i < rows; i++)
            for(int j = 0; j < cols; j++) {
                boolean res = search(i, j, 0);
                if(res) return true;
            }
        return false;
    }

    char[][] board;

    char[] targetChars;
    int rows, cols;

    int[][] visited;

    private boolean search(int i, int j, int targetIndex) {
        if(i >= 0 && i < rows && j >= 0 && j < cols &&
            board[i][j] == targetChars[targetIndex] &&
            visited[i][j] != 1) {
            if(targetIndex == targetChars.length - 1)
                return true;

            visited[i][j] = 1;
            int t = targetIndex + 1;
            boolean res =
                    search(i, j + 1, t) ||
                    search(i + 1, j, t) ||
                    search(i, j - 1, t) ||
                    search(i - 1, j, t);
            visited[i][j] = 0;
            return res;
        }

        return false;
    }


    public static void main(String[] args) {
        char[][] board1 = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word1 = "ABCCED";
        String word = "SEE";
        System.out.println(new P6_WordSearch().exist(board, word));
    }
}
