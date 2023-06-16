package org.neetcode.binarysearch;

import java.util.*;

class P7_MedianOf2SortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        ///Not working. Too tedious.
        int totalLen = nums1.length + nums2.length;
        boolean isEven = totalLen%2 == 0;
        int halfLen = totalLen/2;

//        int[] nums1 = {1,2,4,7,9};
//        int[] nums2 = {3,5,8,10,12};

        int first1 = 0; int last1 = nums1.length - 1;
        int first2 = 0; int last2 = nums2.length - 1;

        double[] m1 = getMedian(nums1, first1, last1);
        double[] m2 = getMedian(nums2, first2, last2);

        while(true) {

            if (medianFound(m1, m2, nums1, nums2, halfLen, isEven, totalLen)) {
                //return
                break;
            } else if (m1[1] > m2[1]) {
                //push m1 to left
                last1 = (int) m1[0];
                m1 = getMedian(nums1, first1, last1);
                int newM2 = (int)(halfLen - m1[0] - 2);
                if(newM2 >= nums2.length) newM2 = nums2.length - 1;
                if(newM2 < 0) newM2 = 0;
                m2[0] = m2[2] = newM2; m2[1] = nums2[newM2];
                first2 = (int) m2[0];
            } else {
                //push m2 to left
                last2 = (int) m2[0];
                m2 = getMedian(nums2, first2, last2);
                int newM1 = (int)(halfLen - m2[0] - 2);
                if(newM1 >= nums1.length) newM1 = nums1.length - 1;
                if(newM1 < 0) newM1 = 0;
                m1[0] = m1[2] = newM1; m1[1] = nums1[newM1];
                first1 = (int) m1[0];
            }
        }

        System.out.println(Arrays.toString(m1));
        System.out.println(Arrays.toString(m2));

        if(m1[0] == m2[0]) {
            return m1[0];
        } else if(m1[0] > m2[0]) {
            return (m2[1] + m1[0])/2;
        } else { //m1[0] < m2[0]
            return (m1[1] + m2[0])/2;
        }
    }

    double resNum;

    private boolean medianFound(double[] tempM1, double[] tempM2, int[] nums1, int[] nums2, int halfLen, boolean isEven,
                                int totalLen) {
        boolean res = false;
        double[] m1 = Arrays.copyOf(tempM1, 3);
        double[] m2 = Arrays.copyOf(tempM2, 3);

        if(m1[0] >= 0 && m1[2] < nums1.length && m2[0] >= 0 && m2[2] < nums2.length) {
            int prevM10 = (int)m1[0];
            if(m1[0] == m1[2]) {
                m1[0] = Math.max(0, m1[0] - 1);
                m1[2] = Math.min(nums1.length - 1, m1[2] + 1);
            }
            int prevM20 = (int)m2[0];
            if(m2[0] == m2[2]) {
                m2[0] = Math.max(0, m2[0] - 1);
                m2[2] = Math.min(nums1.length - 1, m2[2] + 1);
            }
            if(isEven) {
                if(halfLen == prevM10 + 1 + prevM20 + 1) {
                    if(nums1[(int)m1[0]] <= nums2[(int)m2[2]] && nums1[(int)m1[2]] >= nums2[(int)m2[0]]) {

                        return true;
                    } else if (nums1[(int)m1[0]] >= nums2[(int)m2[2]] && nums1[(int)m1[2]] <= nums2[(int)m2[0]]) {

                        return true;
                    }
                }
            } else {
                if(Math.abs(halfLen - prevM10 - prevM20- 2) <= 1) {
                    if(nums1[(int)m1[0]] <= nums2[(int)m2[2]] && nums1[(int)m1[2]] >= nums2[(int)m2[0]]) {

                        return true;
                    } else if (nums1[(int)m1[0]] >= nums2[(int)m2[2]] && nums1[(int)m1[2]] <= nums2[(int)m2[0]]) {

                        return true;
                    }
                }
            }
        } else {
            if(m1[1] > m2[1]) {
                if(isEven) {
                    return halfLen == m1[0] + 1 || halfLen == totalLen - m2[1];
                } else {
                    return Math.abs(halfLen - m1[0] - 1) <= 1 || Math.abs(halfLen - totalLen + m2[1]) <= 1;
                }
            } else {
                if(isEven) {
                    return halfLen == m2[0] + 1 || halfLen == totalLen - m1[1];
                } else {
                    return Math.abs(halfLen - m2[0] - 1) <= 1 || Math.abs(halfLen - totalLen + m1[1]) <= 1;
                }
            }
        }

        return res;
    }

    private boolean between(double lesser, double valMid, double greater) {
        return lesser <= valMid && greater >= valMid;
    }


    public static void main(String[] args) {
        int[] nums1 = {1,2,4,7,9};
        int[] nums2 = {3,5,8,10,12};

        System.out.println(new P7_MedianOf2SortedArrays().findMedianSortedArrays(nums1, nums2));
    }

    private double[] getMedian(int[] nums, int first, int last) {
        int len = last - first + 1;
        double[] res = new double[3];
        if(len > 1) {
            if (len % 2 == 1) { //odd
                int midIndex = len/2 + first;
                res[1] = nums[midIndex];
                res[0] = res[2] = midIndex;
            } else { //even
                res[2] = first + len / 2;
                res[0] = res[2] - 1;
                res[1] = (nums[(int)res[0]] + nums[(int)res[2]]) / 2;
            }
        } else {
            res[0] = res[1] = res[2] = nums[first];
        }

        return res;
    }


    private int binSearch(int first, int last, int[] nums, double target) {
//        int first = 0;
//        int last = nums.length - 1;

        int mid = 0;
        while(first <= last) {
            mid = (first + last) >>> 1;
            int midNum = nums[mid];

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
}
