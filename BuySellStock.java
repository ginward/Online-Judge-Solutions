/**
 * Created by jinhuawang on 6/1/16.
 * Solution to https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 */
public class BuySellStock {
    public int maxProfit(int[] prices) {
        int size=prices.length;
        if(size<2) return 0;
        int max=prices[size-1];
        int max_profit=0;
        for(int i=size-1;i>=0;i--){
            if(max<=prices[i]) {
                max = prices[i];
            }
            else {
                max_profit=Math.max(max_profit,max-prices[i]);
            }
        }
        return max_profit;
    }
}
