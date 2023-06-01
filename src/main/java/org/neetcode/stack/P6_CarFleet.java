package org.neetcode.stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class P6_CarFleet {
    public int carFleet(int target, int[] position, int[] speed) {
        this.target = target;
        speedMap = new HashMap<>();
        for(int i = 0; i < position.length; i++) {
            speedMap.put(position[i], speed[i]);
        }

        Arrays.sort(position);
        int fleetCount = 1;
        int currFleetLeader = position.length - 1;

        for(int i = position.length - 2; i >= 0; i--) {
            if(canOvertake(position[i], position[currFleetLeader])) {
                //merge into curr fleet
            } else {
                currFleetLeader = i;
                fleetCount++;
            }
        }

        return fleetCount;
    }

    int target;
    Map<Integer, Integer> speedMap;

    //t = dist/speed
    private boolean canOvertake(int newCar, int leader) {
        double timeLeader = (double)(target - leader)/speedMap.get(leader);
        double timeNewCar = (double)(target - newCar)/speedMap.get(newCar);
        return timeNewCar <= timeLeader;
    }

    public static void main(String[] args) {
        int[] position = {10,8,0,5,3};
        position = new int[] {0, 3, 5, 8, 10};
        position = new int[] {10,2,5,7,4,6,11};
        int[] speed = {2,4,1,1,3}; //12
        speed = new int[]    {7,5,10,5,9,4,1};
        System.out.println(new P6_CarFleet().carFleet(13, position, speed));
    }
}
