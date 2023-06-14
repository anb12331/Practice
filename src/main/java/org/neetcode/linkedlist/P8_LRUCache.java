package org.neetcode.linkedlist;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class P8_LRUCache {
    class LRUCache {

        Map<Integer, ListNode> map = new HashMap<>();
        ListNode head = new ListNode(0);
        ListNode tail = new ListNode(0);
        int capacity;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            if(map.containsKey(key)) {
                ListNode node = map.get(key);
                bringToFront(node);
                return node.val2;
            }
            return -1;
        }

        public void put(int key, int value) {
            if(!map.containsKey(key)) {
                ListNode newNode = new ListNode(key);
                newNode.val2 = value;
                bringToFront(newNode);
                map.put(key, newNode);

                //Remove
                if(map.size() > capacity) {
                    int lastKey = tail.prev.val;
                    tail.prev = tail.prev.prev;
                    tail.prev.next = tail;
                    map.remove(lastKey);
                }
            } else {
                ListNode updatedNode = map.get(key);
                updatedNode.val2 = value;
                bringToFront(updatedNode);
            }
        }

        private void bringToFront(ListNode node) {
            if(node.next != null) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }

            node.next = head.next;
            head.next.prev = node;
            head.next = node;
            node.prev = head;
        }
    }
}
