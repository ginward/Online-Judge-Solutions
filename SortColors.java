/**
 * Created by jinhuawang on 5/25/16.
 * Solution to https://leetcode.com/problems/sort-colors/
 */
public class SortColors {
    /*
     * Separate the array into three sectors
     * 0----1----2
     * */
    public static void sortColors(int[] nums) {
        int i=-1;//left of i is 0, right of i is 1
        int j=nums.length; //left of j is 1, right of j is 2
        for(int m=0;m<nums.length;m++){
            if(m>=j) break;
            switch (nums[m]){
                case 0:
                    i++;
                    exchange(nums,i,m);
                    break;
                case 2:
                    j--;
                    exchange(nums,m,j);
                    m--;
                    break;
            }
        }
    }

    public static void exchange(int[]nums, int i, int j){
        int tmp=nums[i];
        nums[i]=nums[j];
        nums[j]=tmp;
    }
}
