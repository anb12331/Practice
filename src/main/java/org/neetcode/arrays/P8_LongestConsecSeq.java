package org.neetcode.arrays;

import java.util.HashMap;
import java.util.Map;

class P8_LongestConsecSeq {
    public int longestConsecutive(int[] nums) {
        adjNums = new int[nums.length][3];
        Map<Integer, Integer> posnMap = new HashMap<>();

        for(int i = 0; i < nums.length; i++) {
            posnMap.put(nums[i], i);
            adjNums[i][2] = nums[i];
            Integer next = posnMap.get(nums[i] + 1);
            if(next != null) {
                adjNums[i][0] = next;
            }
            else adjNums[i][0] = -1;

            Integer prev = posnMap.get(nums[i] - 1);
            if(prev != null) {
                adjNums[prev][0] = i;
            }
        }

        for(int i = 0; i < adjNums.length; i++) {
            if(adjNums[i][1] == 0) {
                getSeqLen(i);
            }
        }

        return maxSeqLen;
    }

    int[][] adjNums;
    int maxSeqLen = 0;

    public int getSeqLen(int index) {
        int seqLen = 1;
        int nextIndex = adjNums[index][0];
        if(nextIndex > -1) {
            int nextIndexSeqLen = adjNums[nextIndex][1];
            if (nextIndexSeqLen == 0) {
                seqLen += getSeqLen(nextIndex);
            } else {
                seqLen += nextIndexSeqLen;
            }
        }
        adjNums[index][1] = seqLen;
        maxSeqLen = Math.max(seqLen, maxSeqLen);
        return seqLen;
    }

    public int longestConsecutive_usingLL_slower(int[] nums) {
        Map<Integer, ListNode> llMap = new HashMap<>();

        for(int i = 0; i < nums.length; i++) {
            int curr = nums[i];
            if(llMap.containsKey(curr)) continue;

            ListNode currNode = new ListNode();
            currNode.val = curr;

            if(llMap.containsKey(curr - 1)) {
                llMap.get(curr -1).next = currNode;
                currNode.hasPrev = true;
            }

            if(llMap.containsKey(curr + 1)) {
                ListNode nextNode = llMap.get(curr + 1);
                currNode.next = nextNode;
                nextNode.hasPrev = true;
            }

            llMap.put(curr, currNode);
        }
        int maxSeqLen = 0;
        for(ListNode node: llMap.values()) {
            if(!node.hasPrev) { //this is the first node of a sequence
                int seqLen = 1;
                System.out.print(node.val);
                while(node.next != null) {
                    seqLen++;
                    node = node.next;
                    System.out.print("->" + node.val);
                }
                System.out.print("\r\n");
                maxSeqLen = Math.max(seqLen, maxSeqLen);
            }
        }

        return maxSeqLen;
    }

    class ListNode {
        ListNode next;
        int val;
        boolean hasPrev = false;
    }

    public static void main(String[] args) {
        int[] nums = {100,4,200,1,3,2};
        nums = new int[] {0,3,7,2,5,8,4,6,0,1};
        System.out.println(new P8_LongestConsecSeq().longestConsecutive(nums));
    }
}
