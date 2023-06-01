package org.neetcode.dynamic2d;

import java.util.Arrays;

class P5_CoinChange2 {
    public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length][amount + 1];

        //Sort Desc
        for(int i = 0; i < coins.length; i++) coins[i] = -coins[i];
        Arrays.sort(coins);
        for(int i = 0; i < coins.length; i++) coins[i] = -coins[i];

        for(int j = 0; j <= amount; j++ ) {
            for(int i = 0; i < coins.length; i++) {
                if(j == 0) {
                    dp[i][j] = 1; continue;
                }
                int remAmount = j - coins[i];
                int remAmtResult = remAmount >= 0 ? dp[i][remAmount]: 0 ;
                int lessCoinsRes = i > 0 ? dp[i - 1][j] : 0;
                dp[i][j] = remAmtResult + lessCoinsRes;
            }
        }

        return dp[coins.length - 1][amount];
    }

    public static void main(String[] args) {
        int[] coins = {1,2,5}; //5
        System.out.println(new P5_CoinChange2().change(5, coins));
    }
}
