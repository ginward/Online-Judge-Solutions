/**
 * Created by jinhuawang on 5/23/16.
 * Solution to https://leetcode.com/problems/self-crossing/
 */
public class SelfCrossing {

    public static Boolean isSelfCrossing(int[] x) {
        if(x.length<3) return false;
        for(int i=2;i<x.length-1;i++){
            if(x[i]<=x[(i-2)]&&x[i+1]>=x[(i-1)]){
                return true;
            }
            if(x[i]==x[(i-2)]){
                if(i>=3&&(x[i+1]+x[(i-3)])>=x[(i-1)]){
                    return true;
                }
            }
            if(i>=4&&x[i-1]<=x[i+1]+x[(i-3)]&&x[i-2]<=x[i]+x[i-4]&&x[i-3]<=x[i-1]&&x[i]<=x[i-2]){
                return  true;
            }
        }
        return false;
    }
}
