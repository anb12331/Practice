package org.neetcode.twopointers;

class P4_ContainerWithMostWater {
    public int maxArea(int[] height) {
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;
        boolean leftMinHeight;

        while(left < right) {
            leftMinHeight = height[left] < height[right];
            int currArea = (right - left)*(leftMinHeight ? height[left] : height[right]);
            maxArea = Math.max(maxArea, currArea);
            if(leftMinHeight) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};

        System.out.println(new P4_ContainerWithMostWater().maxArea(height));
    }
}
