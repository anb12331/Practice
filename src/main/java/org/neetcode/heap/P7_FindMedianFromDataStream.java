package org.neetcode.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

class P7_FindMedianFromDataStream {

    public P7_FindMedianFromDataStream() {

    }
    Integer mid = null;
    PriorityQueue<Integer> leftMaxHeap = new PriorityQueue<>(Comparator.comparingInt(o -> -o));
    PriorityQueue<Integer> rightMinHeap = new PriorityQueue<>();

    public void addNum(int num) {
        if(mid == null) { //previously even, now odd
            if(leftMaxHeap.isEmpty() && rightMinHeap.isEmpty()) { //only first element
                mid = num;
            } else {
                int newMid = 0;
                if(num >= leftMaxHeap.peek() && num <= rightMinHeap.peek()) {
                    newMid = num;
                } else if (num < leftMaxHeap.peek()) {
                    newMid = leftMaxHeap.poll();
                    leftMaxHeap.add(num);
                } else if (num > rightMinHeap.peek()) {
                    newMid = rightMinHeap.poll();
                    rightMinHeap.add(num);
                }
                mid = newMid;
            }
        } else { ////previously odd, now even
            if(num >= mid) {
                rightMinHeap.add(num);
                leftMaxHeap.add(mid);
                mid = null;
            } else { //num < mid
                leftMaxHeap.add(num);
                rightMinHeap.add(mid);
                mid = null;
            }
        }

    }

    public double findMedian() {
        if(mid != null) {
            return mid;
        } else {
            return (double) (leftMaxHeap.peek() + rightMinHeap.peek())/2;
        }
    }

    public static void main(String[] args) {
        P7_FindMedianFromDataStream medianFinder = new P7_FindMedianFromDataStream();
        medianFinder.addNum(1);    // arr = [1]
        medianFinder.addNum(2);    // arr = [1, 2]
        System.out.println(medianFinder.findMedian()); // return 1.5 (i.e., (1 + 2) / 2)
        medianFinder.addNum(3);    // arr[1, 2, 3]
        medianFinder.addNum(3);    // arr[1, 2, 3]
        medianFinder.addNum(3);    // arr[1, 2, 3]
        System.out.println(medianFinder.findMedian()); // return 2.0
    }

}

class MedianWith100UniqueDataPoints {
    int[] data = new int[101];
    int size = 0;

    public void addNum(int num) {
        if(num <= 100) {
            data[num]++;
        }

        size++;
    }

    public double findMedian() {
        int sum = 0;
        double halfSize = (double) size/2;
        for(int i = 0; i < data.length; i++) {
            if(sum == halfSize) {
                return (double) (i - 1 + i)/2;
            } else if (sum > halfSize) {
                return i - 1;
            }
            sum += data[i];
        }

        throw new IllegalStateException();
    }
}
