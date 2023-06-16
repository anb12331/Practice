package org.neetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

class P7_LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        int[][] stack = new int[heights.length][2];
        stack[0][0] = heights[0]; stack[0][1] = 0;
        int stackIndex = 0;
        int maxRectSize = 0;
        for(int i = 1; i < heights.length; i++) {
            if(heights[i] > stack[stackIndex][0]) {
                stackIndex++;
                stack[stackIndex][0] = heights[i]; stack[stackIndex][1] = 0;
            } else if(heights[i] == stack[stackIndex][0]) {
                stack[stackIndex][1]++;
            } else { //heights[i] < stack.peekFirst()[0]
                int[] lastPopped = null;
                int[] newStackElem = new int[] {heights[i], 0};
                while(stackIndex >= 0 && heights[i] < stack[stackIndex][0]) {
                    int[] currPopped = stack[stackIndex];
                    stackIndex--;
                    newStackElem[1] += 1 + currPopped[1];
                    currPopped[1] += lastPopped != null ? 1 + lastPopped[1] : 0;
                    int rectSize = currPopped[0] * (1 + currPopped[1]);
                    maxRectSize = Math.max(rectSize, maxRectSize);
                    lastPopped = currPopped;
                }
                if(stackIndex >= 0 && heights[i] == stack[stackIndex][0]) {
                    stack[stackIndex][1] += 1 + newStackElem[1];
                } else {
                    stackIndex++;
                    stack[stackIndex] = newStackElem;
                }
            }
        }

        int[] lastPopped = null;

        while(stackIndex >= 0) {
            int[] currPopped = stack[stackIndex];
            stackIndex--;
            currPopped[1] += lastPopped != null ? 1 + lastPopped[1] : 0;
            int rectSize = currPopped[0] * (1 + currPopped[1]);
            maxRectSize = Math.max(rectSize, maxRectSize);
            lastPopped = currPopped;
        }

        return maxRectSize;
    }

    public int largestRectangleArea_deque(int[] heights) {
        Deque<int[]> stack = new ArrayDeque<>(heights.length);
        stack.addFirst(new int[] {heights[0], 0});
        int maxRectSize = 0;
        for(int i = 1; i < heights.length; i++) {
            if(heights[i] > stack.peekFirst()[0]) {
                stack.addFirst(new int[] {heights[i], 0});
            } else if(heights[i] == stack.peekFirst()[0]) {
                stack.peekFirst()[1]++;
            } else { //heights[i] < stack.peekFirst()[0]
                int[] lastPopped = null;
                int[] newStackElem = new int[] {heights[i], 0};
                while(!stack.isEmpty() && heights[i] < stack.peekFirst()[0]) {
                    int[] currPopped = stack.pollFirst();
                    newStackElem[1] += 1 + currPopped[1];
                    currPopped[1] += lastPopped != null ? 1 + lastPopped[1] : 0;
                    int rectSize = currPopped[0] * (1 + currPopped[1]);
//                    System.out.println(currPopped[0] + " -> " + rectSize);
                    maxRectSize = Math.max(rectSize, maxRectSize);
                    lastPopped = currPopped;
                }
                if(!stack.isEmpty() && heights[i] == stack.peekFirst()[0]) {
                    stack.peekFirst()[1] += 1 + newStackElem[1];
                } else {
                    stack.addFirst(newStackElem);
                }
            }
        }

        int[] lastPopped = null;

        while(!stack.isEmpty()) {
            int[] currPopped = stack.pollFirst();
            currPopped[1] += lastPopped != null ? 1 + lastPopped[1] : 0;
            int rectSize = currPopped[0] * (1 + currPopped[1]);
//            System.out.println(currPopped[0] + " -> " + rectSize);
            maxRectSize = Math.max(rectSize, maxRectSize);
            lastPopped = currPopped;
        }

        return maxRectSize;
    }

    public static void main(String[] args) {
        int[] heights = {2,1,5,6,2,3};
        heights = new int[] {2,4};
        heights = new int[] {3,6,5,7,4,8,1,0};
        System.out.println(new P7_LargestRectangleInHistogram().largestRectangleArea(heights));
    }
}
