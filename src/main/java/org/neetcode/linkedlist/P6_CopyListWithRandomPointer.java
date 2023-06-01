package org.neetcode.linkedlist;

import java.util.HashMap;
import java.util.Map;

class P6_CopyListWithRandomPointer {

    public Node copyRandomList(Node head) {

        Node ptr = head;
        Map<Node, Node> oldToNewMap = new HashMap<>();


        while(ptr != null) {
            Node newNode = new Node(ptr.val);
            oldToNewMap.put(ptr, newNode);
            ptr = ptr.next;
        }

        ptr = head;

        while(ptr != null) {
            Node next = ptr.next;
            Node random = ptr.random;
            Node newNode = oldToNewMap.get(ptr);
            newNode.next = oldToNewMap.get(next);
            newNode.random = oldToNewMap.get(random);
            ptr = ptr.next;
        }

        return oldToNewMap.get(head);
    }

    class Node extends ListNode {
        Node random;
        Node next;

        public Node(int val) {super(val);}
    }
}
