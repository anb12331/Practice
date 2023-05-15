package org.neetcode.dynamic1d;

class P1_ClimbingStairs {
    public int climbStairs0(int n) {
        if(n <= 0) return 0;
        if(n == 1) return 1;
        else if(n == 2) return 2;
        else if(n > 2) {
            return climbStairs0(n - 2) + climbStairs0(n - 1);
        }
        return 0;
    }

    public int climbStairs(int n) {
        int res = 0;

        if(n <= 0) return 0;
        if(n == 1) return 1;
        else if(n == 2) return 2;
        else if(n > 2) {
            int prev = 2;
            int prev2 = 1;
            for(int i = 3; i <=n; i++) {
                res = prev + prev2;
                prev2 = prev;
                prev = res;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        long timer = System.currentTimeMillis();
        System.out.println(new P1_ClimbingStairs()
                .climbStairs0(45)
        );
        System.out.println(System.currentTimeMillis() - timer);
        System.out.println(new P1_ClimbingStairs()
                .climbStairs(45)
        );
        System.out.println(System.currentTimeMillis() - timer);
    }
}
