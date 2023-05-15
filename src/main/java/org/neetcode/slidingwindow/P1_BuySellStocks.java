package org.neetcode.slidingwindow;

public class P1_BuySellStocks {
    public int maxProfit(int[] prices) {
        int profit = 0;
        if(prices.length > 1) {
            int buyIndex = 0;

            for (int i = 1; i < prices.length; i++) {
                if(prices[i] < prices[buyIndex]) {
                    buyIndex = i;
                } else if(prices[i] - prices[buyIndex] > profit){
                    profit = prices[i] - prices[buyIndex];
                }
            }
        }
        return profit;
    }

    public static void main(String[] args) {
        System.out.println(new P1_BuySellStocks()
                .maxProfit(new int[] {7,6,5,2,1,1})
        );
    }
}
