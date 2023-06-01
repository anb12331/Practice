package org.neetcode.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class P5_DailyTemp {
    public int[] dailyTemperatures(int[] temperatures) {
        Deque<int[]> stack = new ArrayDeque<>();
        int[] res = new int[temperatures.length];

        for(int i = 0; i < temperatures.length; i++) {
            int curr = temperatures[i];
            if(stack.isEmpty()) {
                stack.addLast(new int[] {curr, 0, i});
            } else {
                int[] head = stack.peekLast();

                if(curr <= head[0]) {
                    stack.addLast(new int[]{curr, 0, i});
                } else { // curr > head[0]
                    while(head != null && curr > head[0]) {
                        int[] popped = stack.pollLast();
                        int poppedBefore = popped[1];
                        res[popped[2]] = poppedBefore + 1;
                        if(!stack.isEmpty()) {
                            head = stack.peekLast();
                            head[1] += poppedBefore + 1;
                        } else {
                            head = null;
                        }
                    }
                    stack.addLast(new int[] {curr, 0, i});
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] temp = {73,74,75,71,69,72,76,73};
        temp = new int[] {30,40,50,60};

        System.out.println(Arrays.toString(
                new P5_DailyTemp().dailyTemperatures(temp)
        ));
    }
}
