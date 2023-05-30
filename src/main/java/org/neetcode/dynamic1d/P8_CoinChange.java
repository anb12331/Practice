package org.neetcode.dynamic1d;

import java.util.*;

class P8_CoinChange {
    public int coinChange(int[] coins, int amount) {
        if(amount == 0) return 0;
        this.coins = coins;
        this.len = coins.length;
        dp = new int[amount + 1];
        dp[0] = 0;
        Arrays.sort(coins);
//        for(int i = 0; i < len/2; i++) {
//            int temp = coins[i];
//            coins[i] = coins[len - 1 - i];
//            coins[len - 1 - i] = temp;
//        }

        for(int i = 1; i <= amount; i++) {
            buildDp(i);
        }

        return dp[amount] == 0 ? -1 : dp[amount];
    }

    int[] coins;
    int len;
    Map<Integer, Integer> resMap = new HashMap<>();

    int[] dp;

    private void buildDp(int amount) {
        for(int c: coins) {
            int remAmount = amount - c;
            if(remAmount > 0) {
                if(dp[remAmount] > 0) {
                    if(dp[amount] == 0) dp[amount] = 1 + dp[remAmount];
                    else dp[amount] = Math.min(dp[amount], 1 + dp[remAmount]);
                }
            } else if (remAmount == 0) {
                dp[amount] = 1;
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,2,5};
//        arr = new int[] {2};
//        arr = new int[] {1};
        arr = new int[] {411,412,413,414,415,416,417,418,419,420,421,422}; //9864
        System.out.println(new P8_CoinChange().coinChange(arr, 9864));
    }
}
