package org.neetcode.arrays;

import java.util.Arrays;

public class P6_ArrayProduct {
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] leftProds = new int[len];
        int[] rightProds = new int[len];

        leftProds[0] = nums[0];
        rightProds[len - 1] = nums[len - 1];

        boolean leftZero, rightZero;
        leftZero = rightZero = false;

        for(int i = 1; i < len; i++) {

            if(!leftZero) {
                if(nums[i] != 0) {
                    leftProds[i] = nums[i] * leftProds[i - 1];
                } else {
                    leftZero = true;
                }
            }


            if(!rightZero) {
                if(nums[len - i - 1] != 0) {
                    rightProds[len - i - 1] = nums[len - i - 1] * rightProds[len - i];
                } else {
                    rightZero = true;
                }
            }
        }

        int[] productExSelf = new int[len];
        productExSelf[0] = rightProds[1];
        productExSelf[len - 1] = leftProds[len - 2];

        for(int i = 1; i <= len - 2; i++) {
            productExSelf[i] = leftProds[i - 1] * rightProds[i + 1];
        }

        return productExSelf;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new P6_ArrayProduct()
                .productExceptSelf(new int[]{1,2,0,3,4})
        ));
    }
}
