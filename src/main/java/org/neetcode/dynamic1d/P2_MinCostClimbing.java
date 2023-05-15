package org.neetcode.dynamic1d;

import org.helpers.Timer;

public class P2_MinCostClimbing {
    public int minCostClimbingStairs(int[] cost) {
        len = cost.length;
        stepMinCost = new int[cost.length + 1]; //add one to skip if condition, minor opt
        for(int i = 0; i < cost.length; i++ ) stepMinCost[i] = -1;
        return minCost(cost, cost.length);
    }

    private int len;

    private int[] stepMinCost;

    private int minCost(int[] cost, int step) {
        if(step < cost.length && stepMinCost[step] > -1) //for top, go to next
            return stepMinCost[step]; //Cached

        int currStepCost = step < len && step >= 0 ? cost[step] : 0;
        if(step <= 1) return currStepCost;
        else {
            int min1 = minCost(cost, step - 1);
            int min2 = minCost(cost, step - 2);
            int result = currStepCost + Math.min(min1, min2);
            stepMinCost[step] = result;
            return result;
        }
    }


    public static void main(String[] args) {
        Timer t = new Timer();
        t.start();
        System.out.println(new P2_MinCostClimbing()
                .minCostClimbingStairs(new int[] {1,100,1,1,1,100,1,1,100,1}));
        System.out.println(t.stop());
    }
}
