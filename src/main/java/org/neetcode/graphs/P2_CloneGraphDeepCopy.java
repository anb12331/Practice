package org.neetcode.graphs;

import java.util.*;

public class P2_CloneGraphDeepCopy {
    public Node cloneGraph(Node node) {
        if(node == null) return node;

        Map<Node, Node> copies = new HashMap<>();
        Set<Node> visited = new HashSet<>();

        Deque<Node> q = new ArrayDeque<>();
        q.addLast(node);
        copies.put(node, new Node(node.val));

        while(!q.isEmpty()) {
            Node curr = q.pollFirst();
            if(visited.contains(curr))
                continue;

            visited.add(curr);

            for(Node nbor: curr.neighbors) {
                q.addLast(nbor);
                Node nborCopy = copies.get(nbor);
                if(nborCopy == null) {
                    nborCopy = new Node(nbor.val);
                    copies.put(nbor, nborCopy);
                }
                copies.get(curr).neighbors.add(nborCopy);
            }
        }
        return copies.get(node);
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);

        n1.neighbors.add(n2);
        n1.neighbors.add(n4);

        n2.neighbors.add(n3);
        n2.neighbors.add(n1);

        n3.neighbors.add(n4);
        n3.neighbors.add(n2);

        n4.neighbors.add(n1);
        n4.neighbors.add(n3);

        Node copy = new P2_CloneGraphDeepCopy()
                .cloneGraph(n1);

        System.out.println(copy);
    }
}
