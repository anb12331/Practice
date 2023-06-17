package org.neetcode.trie;

import java.util.Map;

class P2_WordDictionary {
    Node root;
    public P2_WordDictionary() {
        root = new Node('-');
    }

    public void addWord(String word) {
        Node curr = root;
        for(Character c: word.toCharArray()) {
            Map<Character, Node> children = curr.childs;
            Node child = children.get(c);
            if(child == null) {
                child = new Node(c);
                children.put(c, child);
            }
            curr = child;
        }
        curr.isWord = true;
    }

    public boolean search(String word) {
        return search(word, root);
    }

    private boolean search(String word, Node root) {
        Node curr = root;
        char[] chars = word.toCharArray();
        for(int i = 0; i < chars.length; i++) {
            Character c = chars[i];
            Map<Character, Node> children = curr.childs;
            if(c == '.') {
//                if(i == chars.length - 1) return children.size() > 0;
                String remainStr = word.substring(i + 1);
                boolean found = false;
                for(Node child: children.values()) {
                    found = search(remainStr, child);
                    if(found) return true;
                }
                return false;
            } else {
                Node child = children.get(c);
                if(child == null) {
                    return false;
                }
                curr = child;
            }
        }
        return curr.isWord;
    }
    public static void main(String[] args) {

        P2_WordDictionary w = new P2_WordDictionary();
        /*
        String[] toAdd = new String[] {"at","and","an","add","bat"};
        
        String[] toSearch = new String[] {".at","a",".at","an.","a.d.","b.","a.d","."};

        for(String word: toAdd)
            wordDictionary.addWord(word);

        for(String word: toSearch)
            System.out.println(word + " " + wordDictionary.search(word));

         */
        w.addWord("and");
        System.out.println(w.search("..."));

    }
}


