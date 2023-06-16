package org.neetcode.binarysearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class P6_TimeBasedKeyValueStore {
    private Map<String, List<int[][]>> cache;
    public P6_TimeBasedKeyValueStore() {
        cache = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        List<int[][]> vals = cache.get(key);
        if(vals == null) {
            vals = new ArrayList<>();
            cache.put(key, vals);
        }

        char[] val_c = value.toCharArray();
        int[] valInt = new int[val_c.length];
        for(int i = 0; i < val_c.length; i++) valInt[i] = val_c[i];

        vals.add(new int[][] {{timestamp}, valInt});
    }

    public String get(String key, int timestamp) {
        List<int[][]> vals = cache.get(key);
        if(vals != null && timestamp >= vals.get(0)[0][0]) {
            int prev_timeStm_index = binSearch(vals, timestamp);
            if(prev_timeStm_index < 0) prev_timeStm_index = -(prev_timeStm_index + 1);
            int[] valInt = vals.get(prev_timeStm_index)[1];
            char[] valChar = new char[valInt.length];
            for(int i = 0; i < valInt.length; i++) valChar[i] = (char)valInt[i];
            String res = new String(valChar);
            System.out.println(res);
            return res;
        }
        System.out.println("<blank>");
        return "";
    }

    private int binSearch(List<int[][]> nums, int target) {
        int first = 0;
        int last = nums.size() - 1;

        int mid = 0;
        while(first <= last) {
            mid = (first + last) >>> 1;
            int midNum = nums.get(mid)[0][0];

            if(target == midNum) {
                return mid;
            } else if (target < midNum) {
                last = --mid;
            } else { //target > midNum
                first = mid + 1;
            }
        }

        return -mid-1;
    }

    public static void main(String[] args) {
        P6_TimeBasedKeyValueStore timeMap = new P6_TimeBasedKeyValueStore();
        timeMap.set("foo", "bar", 1);  // store the key "foo" and value "bar" along with timestamp = 1.
        timeMap.get("foo", 1);         // return "bar"
        timeMap.get("foo", 3);         // return "bar", since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 is "bar".
        timeMap.set("foo", "bar2", 4); // store the key "foo" and value "bar2" along with timestamp = 4.
        timeMap.get("foo", 4);         // return "bar2"
        timeMap.get("foo", 5);         // return "bar2"
    }
}
