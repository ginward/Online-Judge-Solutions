/**
 * Created by jinhuawang on 6/2/16.
 * Solution to https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 */
public class BuySellStockII {
    /*
    public static int maxProfit(int[] prices) {
        if(prices.length<2) return 0;
        int profit=0;
        for(int i=1;i<prices.length;i++){
            int tmp=prices[i]-prices[i-1];
            if(tmp>0) profit+=tmp;
        }
        return profit;
    }
    */
    public static int maxProfit(int[] prices){
        if(prices.length<2) return 0;
        int profit=0;
        int tmp_profit=0;
        int min=prices[0];
        for(int i=1;i<prices.length-1;i++){
            if(prices[i]>min&&prices[i]>prices[i-1]&&prices[i+1]<=prices[i]){
                profit+=prices[i]-min;
                min=prices[i+1];
            }
            if(min>prices[i]) min=prices[i];
        }
        if (prices[prices.length-1]>prices[prices.length-2]){
            profit+=prices[prices.length-1]-min;
        }
        return profit;
    }
}
