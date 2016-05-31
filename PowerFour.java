/**
 * Created by jinhuawang on 5/31/16.
 * Solution to https://leetcode.com/problems/power-of-four/
 */
public class PowerFour {
    public static void main(String[] args){
        System.out.println(isPowerOfFour(16));
    }
    public static boolean isPowerOfFour(int num) {
        int remainder=0;
        int ori=num;
        while(num>1){
            remainder=num%4;
            num=num/4;
            if (remainder!=0) return false;
        }
        return ori>0&&remainder==0;
    }
}
