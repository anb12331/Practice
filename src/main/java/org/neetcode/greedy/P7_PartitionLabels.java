package org.neetcode.greedy;

import java.util.ArrayList;
import java.util.List;

class P7_PartitionLabels {
    public List<Integer> partitionLabels(String s) {
        char[] chars = s.toCharArray();
        int[] lastPosn = new int[26];
        List<Integer> partitions = new ArrayList<>();

        for(int i = 0; i < chars.length; i++) {
            char c = chars[i];
            int charIndex = c - 'a';
            lastPosn[charIndex] = Math.max(i, lastPosn[charIndex]);
        }
        int lastPartitionEnd = -1;
        int currPartitionEnd = 0;
        for(int i = 0; i < chars.length; i++) {
            if(i > currPartitionEnd) {
                partitions.add(currPartitionEnd - lastPartitionEnd);
                lastPartitionEnd = currPartitionEnd;
            }
            char c = chars[i];
            int charIndex = c - 'a';
            if(lastPosn[charIndex] > currPartitionEnd) {
                currPartitionEnd = lastPosn[charIndex];
            }
        }

        partitions.add(chars.length - 1 - lastPartitionEnd);

        return partitions;
    }

    public static void main(String[] args) {
        String s = "ababcbacadefegdehijhklij";
        System.out.println(new P7_PartitionLabels().partitionLabels(s));
    }
}
