package org.neetcode.greedy;

import java.util.Arrays;

public class P5_HandOfStraights {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if(hand.length % groupSize != 0) return false;
        int len = hand.length;
        int n = len/groupSize;

        Arrays.sort(hand);

        int[][] groupData = new int[n][2]; //currsize, last element
//        groupData[0] = new int[] {hand[0], 1};
//        int currGroupSize = 1;
        int curr = 0;
        for(int i = 0; i < hand.length; i++) {
            if(groupData[curr][0] < groupSize) {
                if (groupData[curr][0] == 0 || hand[i] == 1 + groupData[curr][1]) {
                    groupData[curr][0]++;
                    groupData[curr][1] = hand[i];
                } else if(hand[i] == groupData[curr][1]) {
                    int next = curr + 1;
                    boolean added = false;
                    while(next < n && !added) {
                        if(groupData[next][0] == 0) {
                            groupData[next][1] = hand[i];
                            added = true;
                            groupData[next][0]++;
                        } else if (groupData[next][0] < groupSize
                                && groupData[next][1] + 1 == hand[i]) {
                            groupData[next][1] = hand[i];
                            added = true;
                            groupData[next][0]++;
                        } else {
                            next++;
                        }
                    }
                    if(!added) return false;
                } else {
                    return false;
                }
            } else {
                //add this card to start of next group
                if(curr < n - 1) {
                    curr++;
                    i--;
                }
            }
        }

        Arrays.stream(groupData).forEach(g -> System.out.println(Arrays.toString(g)));

        return curr == n - 1 && groupData[curr][0] == groupSize;

    }

    public static void main(String[] args) {
        int[] hand = {1,2,3,6,2,3,4,7,8};

        System.out.println(new P5_HandOfStraights().isNStraightHand(hand, 3));
    }
}
