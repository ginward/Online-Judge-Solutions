import java.util.HashMap;
import java.util.Map;

/**
 * Created by jinhuawang on 6/6/16.
 * Solution to https://leetcode.com/problems/longest-consecutive-sequence/
 */
public class LongestConsecutiveSequence {
    public static void main(String[] args){
        int[]nums=new int[4];
        nums[0]=1;
        nums[1]=2;
        nums[2]=0;
        nums[3]=1;
        System.out.print(longestConsecutive(nums));
    }
    public static int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map=new HashMap<>();
        int max=1;
        //place the unordered integers into the hashmap
        for(int i=0;i<nums.length;i++){
            map.put(nums[i],i);
        }
        for(int i=0;i<nums.length;i++){
            int tmp=nums[i];
            int maxLen=1;
            while(map.containsKey(tmp-1)){
                map.remove(tmp);
                tmp--;
                map.remove(tmp);
                maxLen++;
            }
            tmp=nums[i];
            while(map.containsKey(tmp+1)){
                map.remove(tmp);
                tmp++;
                map.remove(tmp);
                maxLen++;
            }
            max=Math.max(max,maxLen);
        }
        return max;
    }
}
