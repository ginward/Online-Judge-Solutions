/**
 * Created by jinhuawang on 8/12/16.
 * Solution to https://leetcode.com/problems/create-maximum-number/
 */
public class CreateMaximumNumber {

    public static int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[]result = new int[k];
        int length_nums1=nums1.length;
        int length_nums2=nums2.length;
        if(length_nums1==0) return maxIntegers(nums2,k);
        if(length_nums2==0) return maxIntegers(nums1,k);
        for(int i=0;i<k;i++){
            if(i+1<=length_nums1&&k-i-1<=length_nums2) {
                int m = i + 1;//numbers to be taken out of nums1
                int n = k - i - 1;//numbers to be taken out of nums2
                int[] tmp_1 = maxIntegers(nums1, m);
                int[] tmp_2 = maxIntegers(nums2, n);
                int[] max_arr = maxCombinedArray(tmp_1, tmp_2);
                if (compareArr(max_arr, result)) result = max_arr;
            }
        }
        return result;
    }

    //if arr1>arr2 return true, else return false
    public static boolean compareArr(int[]arr1, int[]arr2){
        if(arr1.length!=arr2.length) return false;
        for(int i=0;i<arr1.length;i++){
            if(arr1[i]<arr2[i]) return false;
            if(arr1[i]>arr2[i]) return true;
        }
        return true;
    }

    //return the max array combining arr1 and arr2
    public static int[]maxCombinedArray(int[]arr1, int[]arr2){
        int[]res=new int[arr1.length+arr2.length];
        int ptr1=0;
        int ptr2=0;
        while(ptr1<arr1.length&&ptr2<arr2.length){
            if(arr1[ptr1]>arr2[ptr2]){
                res[ptr1+ptr2]=arr1[ptr1];
                ptr1++;
            } else if(arr1[ptr1]<arr2[ptr2]) {
                res[ptr1+ptr2]=arr2[ptr2];
                ptr2++;
            } else {
                int x=ptr1;
                int y=ptr2;
                while(x<arr1.length&&y<arr2.length&&arr1[x]==arr2[y]){
                    x++;
                    y++;
                }
                if(x<arr1.length&&y<arr2.length){
                    if(arr1[x]>arr2[y]){
                        res[ptr1+ptr2]=arr1[ptr1];
                        ptr1++;
                    } else {
                        res[ptr1+ptr2]=arr2[ptr2];
                        ptr2++;
                    }
                } else if(x<arr1.length){
                    res[ptr1+ptr2]=arr1[ptr1];
                    ptr1++;
                } else{
                    res[ptr1+ptr2]=arr2[ptr2];
                    ptr2++;
                }
            }
        }
        if(ptr1<arr1.length){
            while(ptr1<arr1.length){
                res[ptr1+ptr2]=arr1[ptr1];
                ptr1++;
            }
        } else if(ptr2<arr2.length) {
            while(ptr2<arr2.length){
                res[ptr1+ptr2]=arr2[ptr2];
                ptr2++;
            }
        }
        return res;
    }

    //get the array of max integers preserving the original order
    public static int[] maxIntegers(int []arr, int k){
        int []res = new int[k];
        int len=0;
        for(int i=0;i<arr.length;i++){
            while(len>0&&len+arr.length-i>k&&arr[i]>res[len-1]){
                len--;
            }
            if(len<k){
                res[len++]=arr[i];
            }
        }
        return res;
    }
}
