package org.neetcode.trie;

import java.util.HashMap;
import java.util.Map;

class Trie {
    Node root;

    public Trie() {
        root = new Node('-');
    }

    public void insert(String word) {
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
        Node curr = root;
        for(Character c: word.toCharArray()) {
            Map<Character, Node> children = curr.childs;
            Node child = children.get(c);
            if(child == null) {
                return false;
            }
            curr = child;
        }
        return curr.isWord;
    }

    public boolean startsWith(String prefix) {
        Node curr = root;
        for(Character c: prefix.toCharArray()) {
            Map<Character, Node> children = curr.childs;
            Node child = children.get(c);
            if(child == null) {
                return false;
            }
            curr = child;
        }
        return true;
    }
}

class Node {
    char prefix;
    Map<Character, Node> childs;
    boolean isWord;

    Node(char prefix) {
        this.prefix = prefix; isWord = false;
        childs = new HashMap<>();
    }
}
