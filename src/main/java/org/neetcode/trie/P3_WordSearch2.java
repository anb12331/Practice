package org.neetcode.trie;

import java.util.*;

class P3_WordSearch2 {
    public List<String> findWords(char[][] board, String[] words) {
        this.board = board;
        rows = board.length; cols = board[0].length;
        Trie root = buildTrie(words);
        visited = new boolean[rows][cols];
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                char curr = board[i][j];
                if(root.map.containsKey(curr)) {
                    if(dfs(i, j, root.map.get(curr))) {
                        root.map.remove(curr);
                    }
                }
                if(foundWords.size() == words.length) break;
            }
        }
        return new ArrayList<>(foundWords);
    }

    boolean[][] visited;
    char[][] board;
    int[][] nbors = {{0,1}, {1,0}, {-1,0}, {0,-1}};
    List<Character> currWord = new ArrayList<>(10);
    Set<String> foundWords = new HashSet<>();

    private boolean dfs(int i, int j, Trie node) {
        if(visited[i][j]) return false;
        currWord.add(board[i][j]);
        visited[i][j] = true;
        boolean foundWord = false;
        if(node.isLeaf) {
            foundWords.add(copyToString(currWord));
            foundWord = true;
        }

        if(!node.map.isEmpty()) {
            for (int[] nb : nbors) {
                int i1 = i + nb[0];
                int j1 = j + nb[1];
                if (i1 >= 0 && i1 < rows &&
                        j1 >= 0 && j1 < cols) {
                    char nbChar = board[i1][j1];
                    if (node.map.containsKey(nbChar)) {
                        if(dfs(i1, j1, node.map.get(nbChar))) {
                            foundWord = true;
                            node.map.remove(nbChar);
                        }
                    }
                }
            }
        }

        visited[i][j] = false;
        currWord.remove(currWord.size() - 1);
        if(foundWord && node.map.isEmpty())
            return true; //tell parent to delete me

        return false;
    }

    private String copyToString(List<Character> currWord) {
        char[] chars = new char[currWord.size()];
        for(int i = 0; i < currWord.size(); i++) chars[i] = currWord.get(i);
        return new String(chars);
    }

    int rows;
    int cols;

    private Trie buildTrie(String[] words) {
        Trie root = new Trie();
        Trie curr = root;
        for(String word: words) {
            char[] wc = word.toCharArray();
            for(int i = 0; i < wc.length; i++) {
                char c = wc[i];
                if(!curr.map.containsKey(c)) {
                    curr.map.put(c, new Trie());
                }
                curr = curr.map.get(c);
            }
            curr.isLeaf = true;
            curr = root;
        }

        return root;
    }

    class Trie {
        boolean isLeaf = false;
        Map<Character, Trie> map = new HashMap<>();
    }

    public static void main(String[] args) {
        char[][] board = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        String[] words = {"oath","pea","eat","rain", "oat"};
        System.out.println(new P3_WordSearch2().findWords(board, words));
    }


}
