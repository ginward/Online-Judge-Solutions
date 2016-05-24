import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by jinhuawang on 5/24/16.
 * Solution to https://leetcode.com/problems/sliding-window-maximum/
 */
public class SlidingWindowMax {

    public static int[] maxSlidingWindow(int[] nums, int k) {
        /*
         * The condition of the maxQueue:
         * maxQueue is the queue of array index
         * frontEnd: Max Value within the window
         * backEnd: pop the value from backend if it is less than the value being inserted
         * */
        Deque<Integer> maxQueue=new LinkedList<>();
        int[]result=new int[nums.length-k+1];
        if(nums.length==0){
            return new int[0];
        }
        for(int i=0;i< nums.length;i++){
            while(!maxQueue.isEmpty()&&nums[i]>nums[maxQueue.peekLast()]){
                maxQueue.removeLast();
            }
            maxQueue.addLast(i);
            if(maxQueue.peekFirst()<=i-k){
                maxQueue.removeFirst();
            }
            if(i>=k-1) {
                result[i-k+1]=nums[maxQueue.peekFirst()];
            }
        }
        return result;
    }

}
