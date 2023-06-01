package org.neetcode.greedy;

class P6_MergeTriplets {
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        for(int triplIndex = 0; triplIndex < 3; triplIndex++) {
            boolean foundValidTriplet = false;
            for (int[] triplet : triplets) {
                if (target[triplIndex] == triplet[triplIndex]) {
                    boolean isValidTriplet = true;
                    for (int i = 0; i < 3; i++) {
                        if (i != triplIndex) {
                            if (triplet[i] > target[i]) {
                                //Not possible to obtain target by merging,
                                //as curr triplet's value is higher
                                isValidTriplet = false;
                                break;
                            }
                        }
                    }
                    if (isValidTriplet) {
                        foundValidTriplet = true;
                    }
                }
                if(foundValidTriplet) break;
            }
            if(!foundValidTriplet) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        int[][] triplets = {{2,5,3},{1,8,4},{1,7,5}};
        int[] target = {2,7,5};

        System.out.println(new P6_MergeTriplets().mergeTriplets(triplets, target));
    }
}
