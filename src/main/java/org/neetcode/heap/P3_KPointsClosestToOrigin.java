package org.neetcode.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class P3_KPointsClosestToOrigin {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<double[]> pq = new PriorityQueue<>(new Comparator<double[]>() {
            @Override
            public int compare(double[] o1, double[] o2) {
                return Double.compare(o2[0], o1[0]);
            }
        });

        for(int i = 0; i < points.length; i++) {
            int[] pt = points[i];
            double dist = pt[0]*pt[0] + pt[1]*pt[1];
            if(pq.size() < k) pq.add(new double[] {dist, i});
            else if (dist < pq.peek()[0]) {
                pq.poll();
                pq.add(new double[] {dist, i});
            }
        }

        int[][] res = new int[k][2];

        int i = 0;
        for(double[] node: pq) {
            res[i] = points[(int)node[1]];
            System.out.println(Arrays.toString(res[i]));
            i++;
        }

        return res;
    }

    public static void main(String[] args) {
        int[][] points = {{3,3},{5,-1},{-2,4}};
        int k = 2;
        System.out.println(new P3_KPointsClosestToOrigin().kClosest(points, k));
    }
}
