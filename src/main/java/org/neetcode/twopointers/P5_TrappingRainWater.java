package org.neetcode.twopointers;

class P5_TrappingRainWater {
    public int trap(int[] height) {
        int[] maxHeightFromRight = new int[height.length];

        loadMaxHeights(maxHeightFromRight, height);
        int leftWall = 0;
        int water = 0;

        for(int i = 0; i < height.length; i++) {
            if(height[i] >= leftWall) {
                leftWall = height[i]; continue;
            }

            if(height[i] < leftWall) {
                int nextMaxHeight = maxHeightFromRight[i];
                if(nextMaxHeight >= leftWall) {
                    water += (leftWall - height[i]);
                } else {
                    water += Math.max(nextMaxHeight - height[i], 0);
                }
            }
        }

        return water;
    }

    private boolean loadMaxHeights(int[] maxHeightFromRight, int[] height) {
        int maxHeight = 0;
        int prev = 0;
        for(int i = height.length - 1; i >= 0; i--) {
            maxHeight = Math.max(maxHeight, prev);
            prev = height[i];
            maxHeightFromRight[i] = maxHeight;
        }

        return true;
    }

    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        height = new int[] {4,2,0,3,2,5};

        System.out.println(new P5_TrappingRainWater().trap(height));
    }
}
