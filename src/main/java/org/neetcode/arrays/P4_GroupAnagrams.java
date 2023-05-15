package org.neetcode.arrays;

import java.util.*;

public class P4_GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();

        for(String str: strs) {
            char[] strArr = str.toCharArray();
            Arrays.sort(strArr);
            String sortedStr = new String(strArr);
            List<String> group = groups.get(sortedStr);
            if(group == null) {
                group = new ArrayList<>();
                groups.put(sortedStr, group);
            }
            group.add(str);
        }

        return new ArrayList<>(groups.values());
    }

    public static void main(String[] args) {
        System.out.println(new P4_GroupAnagrams()
                .groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"})
        );
    }

    //store only letterCounts as map keys instead of sorted
    public List<List<String>> groupAnagrams_bestSolution(String[] strs) {

        Map<String, List<String>> map = new HashMap<>();

        return new AbstractList<List<String>>(){

            List<List<String>> result;
            public List<String> get(int index) {
                if (result == null) init();
                return result.get(index);
            }

            public int size() {
                if (result == null) init();
                return result.size();
            }

            private void init() {
                for (String s: strs) {
                    char[] keys = new char[26];
                    for (int i = 0; i < s.length(); i++)
                        keys[s.charAt(i) - 'a']++;

                    String key = new String(keys);
                    System.out.println(key);
                    List<String> list = map.get(key);
                    if (list == null) map.put(key, new ArrayList<>());
                    map.get(key).add(s);
                }
                result = new ArrayList<>(map.values());
            }
        };

    }

    //store unique sum (using primes) of char ints as map keys
    static int[] primes = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
    public List<List<String>> groupAnagrams_2ndbestSolution(String[] strs) {
        HashMap<Long,ArrayList<String>> map = new HashMap<Long, ArrayList<String>>();
        for(String i : strs)
        {
            long hashValue = hashValue(i);
            if(map.containsKey(hashValue))
            {
                map.get(hashValue).add(i);
            }
            else
            {
                ArrayList<String> temp = new ArrayList<String>();
                temp.add(i);
                map.put(hashValue, temp);
            }
        }
        List<List<String>> answer = new ArrayList<List<String>>();
        Iterator<ArrayList<String>> i = map.values().iterator();
        while(i.hasNext())
        {
            answer.add(i.next());
        }
        return answer;
    }
    public long hashValue(String a)
    {
        long answer = 1;
        for(int i = 0; i<a.length(); i++)
        {
            int t = a.charAt(i)-'a';
            answer = (answer * primes[t])%1000000000007L;
        }
        return answer;
    }
}
