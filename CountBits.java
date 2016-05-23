
/**
 * Created by jinhuawang on 5/23/16.
 * Solution to https://leetcode.com/problems/counting-bits/
 */
public class CountBits {

    public static int[] countBits(int num) {
        //the array that holds the number
        int []arr= new int[num+1];
        if (num==0) {
            arr[0]=0;
            return arr;
        }
        arr[0]=0;
        arr[1]=1;
        for(int i=0;i<=num;i++){
            if(i%2==0)
                arr[i]=arr[i/2];
            else
                arr[i]=arr[i/2]+1;
        }
        return arr;
    }
}
