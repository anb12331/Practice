package org.neetcode.twopointers;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P3_3Sum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length < 3) return res;
        Arrays.sort(nums);
        int index = 0;
        int currSum;
        int prevNum = nums[0] - 1;
        for(int num: nums) {
            if(prevNum == num) {
                index++;
                continue;
            }
            List<Integer> currTriplet = new ArrayList<>();
            int p1 = index  + 1;
            int p2 = nums.length - 1;
            while (nums.length - index >= 3 && p1 < p2) {
                currSum = nums[p1] + nums[p2] + num;
                if (currSum > 0) {
                    p2--;
                } else if (currSum < 0) {
                    p1++;
                } else if (currSum == 0) {
                    currTriplet.addAll(List.of(num, nums[p1], nums[p2]));
                    res.add(currTriplet);
                    currTriplet = new ArrayList<>();
                    int newp1 = p1 + 1;
                    prevNum = num;
                    while(nums[newp1] == nums[p1] && newp1 < p2) {
                        newp1++;
                    }
                    p1 = newp1;
                }
            }
            index++;
        }


        return res;
    }

    private void findZero() {
    }

    public static void main(String[] args) {
        System.out.println(new P3_3Sum()
                .threeSum(new int[] {0,0,0})
        );
        // -4,-1,-1,0,1,2
    }

    private List <List<Integer>> list;

    public List <List<Integer>> threeSum_fastestButComplicated(int[] nums) {
        return new AbstractList < List < Integer >> () {
            public List <Integer> get(int index) {
                initialize();
                return list.get(index);
            }

            public int size() {
                initialize();
                return list.size();
            }

            private void initialize() {
                if (list != null) return;
                Arrays.sort(nums);
                list = new ArrayList < > ();

                int l, h, sum;

                for (int i = 0; i < nums.length; i++) {
                    if (i != 0 && nums[i] == nums[i - 1])
                        continue;

                    l = i + 1;
                    h = nums.length - 1;

                    while (l < h) {
                        sum = nums[i] + nums[l] + nums[h];
                        if (sum == 0) {
                            list.add(new Triple(nums[i], nums[l], nums[h]));
                            l++;
                            h--;
                            while (l < h && nums[l] == nums[l - 1]) l++;
                            while (l < h && nums[h] == nums[h + 1]) h--;
                        } else if (sum < 0) {
                            l++;
                        } else {
                            h--;
                        }
                    }
                }

            }
        };
    }

    public static class Triple extends AbstractList< Integer > {
        private int[] data;

        public Triple(int i, int j, int k) {
            data = new int[] {
                    i,
                    j,
                    k
            };
        }

        public Integer get(int index) {
            return data[index];
        }

        public int size() {
            return 3;
        }
    }
}
