package org.neetcode.binarysearch;

import org.helpers.Timer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class P3_KokoEatingBananas {
    public int minEatingSpeed(int[] piles, int h) {

        int max = piles[0];

        if(h==piles.length) {
            for(int p: piles) if(p > max) max = p;
            return max;
        }

        Arrays.sort(piles);
        max = piles[piles.length - 1];

        int lo = 1, hi = max;

        int mid = -1;
        double currHours;
        while(lo < hi) {
            mid = (lo + hi) >>> 1;
            int midNum = mid;
            currHours = findHoursToEat(piles, midNum);
            if(h == currHours) {
                break;
            } else if(h < currHours) { //takes too many hours, so increase k to decrease hours
                lo = ++mid;
            } else if(h > currHours) { //takes too few hours, so decrease k to increase hours
                hi = mid - 1;
            }
        }

        currHours = findHoursToEat(piles, mid);
        double target1 = findHoursToEat(piles, mid - 1);
        if(target1 < h) return --mid;
        if(currHours > h) return ++mid;


       return mid;
    }

    private double findHoursToEat(int[] piles, int k) {
        int hours = 0;
        double maxWastedTime = 0.0;
        for(int p: piles) {
            double calcHours = (double) p/(double) k;
            double ceilHours = Math.ceil(calcHours);
            double wastedTime = ceilHours - calcHours;
            if(wastedTime > maxWastedTime) maxWastedTime = wastedTime;
            hours += Math.ceil(calcHours);
        }

        return hours - maxWastedTime;
    }

    private static int binSearch(int[] sortedNums, int target) {
        int lo = 0, hi = sortedNums.length - 1;

        int mid = -1;
        while(lo <= hi) {
           mid = (lo + hi) >>> 1;
           int midNum = sortedNums[mid];
           if(midNum == target) {
               return mid;
           } else if(midNum < target) {
               lo = ++mid;
           } else if(midNum > target) {
               hi = mid - 1;
           }
        }

        while(mid > 0 && sortedNums[mid] > target)
            mid--;

        return mid;
    }


    public static void main(String[] args) {
//        System.out.println(new P7_KokoEatingBananas()
//                .minEatingSpeed(new int[] {3,6,7,11}, 8));
//        System.out.println(new P7_KokoEatingBananas()
//                .minEatingSpeed(new int[] {30,11,23,4,20}, 5));

//        System.out.println(new P7_KokoEatingBananas()
//                .minEatingSpeed(new int[] {332484035,524908576,855865114,632922376,222257295,690155293,112677673,
//                        679580077,337406589,290818316,877337160,901728858,679284947,688210097,692137887,
//                        718203285,629455728,941802184},
//                        823855818));

//        System.out.println(new P7_KokoEatingBananas()
//                .minEatingSpeed(new int[] {848219518,588431922,679783979,432047681,558963869,874763943,356682090,514921461,639400033,842625686,737707391,663203571,195860905,278665278,886595950,837136539,649029499,866615005,314734742,888429433,348422147,634905317,222096525,926976060,731794241,636342449,57112531,790567866,296797429,756486656,972612015,439134823,20840594,812172762,722611086,617646271,594776717,912921645,892159640,674448885,645585171,658634155,78508257,946709338,376077435,846624429,996828412,236496810,946919361,414390039,60947718,973801466,452874238,334628044,117140771,402855733,278663893,259369536,159585958,501119979,992853641,292301385,614052668,417035905,172421780,669155352,214329208,96847320,398325069,739265267,444152648,820224819,741012408,656370372,750877554,562792394,269958723,158621149,242126959,211043846,48918663,759208762,16593653,233539975},
//                        558918813));

//        System.out.println(new P7_KokoEatingBananas()
//                .minEatingSpeed(new int[] {1,1,1,999999999}, 10));

/*
        int len = 100;
        int searchCount = 20;

        int[] list = new int[len];

        for(int i=0; i < len; i++) {
            list[i] = (int)(Math.random() * 1000);
        }

        Arrays.sort(list);

        List<Integer> testSearch = new ArrayList<>();
        int count = 0;
        for(int i=0; i < len; i++) {
            if(i > 0 && list[i] - list[i - 1] > 1 && count <= searchCount) {
                testSearch.add(list[i] - 1);
                count++;
            }
        }

        System.out.println(Arrays.toString(list));
        for(int s: testSearch) {
            int bin1 = binSearch(list, s);
            System.out.println("search:" + s + " ret:" + list[bin1]);
        }
 */
        int[] nums = {1,2,4,5,6,7,9,10};
        int bin = binSearch(nums, -80);
        int bin2 = binSearch(nums, 3);

        System.out.println(nums[bin] + " " + nums[bin2]);


    }
}
