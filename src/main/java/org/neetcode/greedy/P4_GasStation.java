package org.neetcode.greedy;

public class P4_GasStation {
    public int canCompleteCircuit0(int[] gas, int[] cost) {
        int surplusPrev = -1;
        int start = -1;
        int i = 0;

        int[] visited = new int[gas.length];
        while(i != start) {
            if(surplusPrev <= 0) {
                if(visited[i]== 1)
                    return -1;
                if(surplusPrev < 0) start = i;
                surplusPrev = 0;
            }
            int surplusCurr = gas[i] - cost[i] + surplusPrev;
            surplusPrev = surplusCurr;
            visited[i] = 1;
            if(i == gas.length - 1) {
                if(surplusPrev < 0)
                    return -1;
                i = 0;
            } else {
                i++;
            }
        }
        if(surplusPrev >= 0)
            return start;
        else
            return -1;
    }

    public static void main(String[] args) {
        int[] gas1 = {1,2,3,4,5};
        int[] cost1 = {3,4,5,1,2};
        int[] gas = {1,0,1,1,2};
        int[] cost = {1,1,1,1,1};
        System.out.println(new P4_GasStation().canCompleteCircuit(gas, cost));
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int surplusPrev = -1;
        int start = -1;
        int diffSum = 0;

        for(int i = 0; i < gas.length; i++) {
            if(surplusPrev < 0) { //equality not needed as solution is guaranteed to be unique
                start = i;
                surplusPrev = 0;
            }
            int diff = gas[i] - cost[i];
            diffSum += diff;

            int surplusCurr = diff + surplusPrev;
            surplusPrev = surplusCurr;
        }
        if(surplusPrev >= 0 && diffSum >= 0)
            return start;
        else
            return -1;
    }
}
