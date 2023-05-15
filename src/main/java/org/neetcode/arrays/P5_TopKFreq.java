package org.neetcode.arrays;

import java.util.*;

public class P5_TopKFreq {
    public int[] topKFrequent0(int[] nums, int k) {
        int[] freqs = new int[nums.length + 1];
        Map<Integer, Integer> wordCounts = new HashMap<>();

        for(int num: nums) {
            Integer prevCount = wordCounts.get(num);
            if(prevCount == null) {
                wordCounts.put(num, 1);
            } else {
                wordCounts.put(num, prevCount + 1);
            }
        }

        for(Integer num: wordCounts.keySet()) {
            int freq = wordCounts.get(num);
            if(freqs[freq] == 0) {
                if(num >= 0) {
                    num++;
                } else {
                    num--;
                }
                freqs[freq] = num;
            }
        }

        List<Integer> freqNums = new ArrayList<>();
        for(int i = freqs.length - 1; i > 0; i--) {
            if(freqs[i] != 0) {
                Integer num = freqs[i];
                if(num >= 0) {
                    num--;
                } else {
                    num++;
                }
                freqNums.add(num);
            }
            if(freqNums.size() >= k) break;
        }

        int[] res = new int[freqNums.size()];
        for(int i = 0; i < freqNums.size(); i++) res[i] = freqNums.get(i);

        return res;
    }

    public int[] topKFrequent1(int[] nums, int k) {
        String[] freqs = new String[nums.length + 1];
        Map<Integer, Integer> wordCounts = new HashMap<>();

        for(int num: nums) {
            Integer prevCount = wordCounts.get(num);
            if(prevCount == null) {
                wordCounts.put(num, 1);
            } else {
                wordCounts.put(num, prevCount + 1);
            }
        }

        for(Integer num: wordCounts.keySet()) {
            int freq = wordCounts.get(num);
            if(freqs[freq] == null) {
                freqs[freq] = num.toString();
            } else {
                freqs[freq] += "," + num.toString();
            }
        }

        List<Integer> freqNums = new ArrayList<>(k);
        for(int i = freqs.length - 1; i > 0; i--) {
            if(freqs[i] != null) {
                String num = freqs[i];
                if(num.contains(",")) {
                    String[] splitNums = num.split(",");
                    for(int j = 0; j < Math.min(k, splitNums.length); j++) {
                        freqNums.add(Integer.valueOf(splitNums[j]));
                    }
                } else {
                    freqNums.add(Integer.valueOf(num));
                }
            }
            if(freqNums.size() >= k) break;
        }

        int[] res = new int[freqNums.size()];
        for(int i = 0; i < freqNums.size(); i++) res[i] = freqNums.get(i);

        return res;
    }

    public int[] topKFrequent(int[] nums, int k) {
        Object[] freqs = new Object[nums.length + 1];
        Map<Integer, Integer> wordCounts = new HashMap<>();

        for(int num: nums) {
            Integer prevCount = wordCounts.get(num);
            if(prevCount == null) {
                wordCounts.put(num, 1);
            } else {
                wordCounts.put(num, prevCount + 1);
            }
        }

        for(Integer num: wordCounts.keySet()) {
            int freq = wordCounts.get(num);
            Object freqObj = freqs[freq];
            if(freqObj == null) {
                freqs[freq] = num;
            } else if (freqObj instanceof Integer) {
                freqs[freq] = new ArrayList<>(Arrays.asList(freqObj, num));
            } else {
                ((List<Integer>)freqObj).add(num);
            }
        }

        List<Integer> freqNums = new ArrayList<>(k);
        for(int i = freqs.length - 1; i > 0; i--) {
            if(freqs[i] != null) {

                Object freqObj = freqs[i];
                if(freqObj instanceof Integer) {
                    freqNums.add((Integer) freqObj);
                } else {
                    List<Integer> freqList = (List<Integer>)freqObj;

                    freqNums.addAll(
                            freqList.subList(0, Math.min(freqList.size(), k - freqNums.size()))
                    );
                }
            }
            if(freqNums.size() >= k) break;
        }

        int[] res = new int[freqNums.size()];
        for(int i = 0; i < freqNums.size(); i++) res[i] = freqNums.get(i);

        return res;
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(new P5_TopKFreq()
                .topKFrequent(new int[]{1, 2}, 2)
        ));
    }
}
