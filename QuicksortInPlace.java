import java.util.Scanner;

/**
 * Created by jinhuawang on 5/16/16.
 * Solution to https://www.hackerrank.com/challenges/quicksort3
 */
public class QuicksortInPlace {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str;
        while((str = scanner.nextLine())!=null){
            String[]nums = str.split("\\s+");
            int num = Integer.valueOf(nums[0]);
            int[]arr=new int[num];
            str = scanner.nextLine();
            nums = str.split("\\s+");
            for(int i=0;i<arr.length;i++){
                arr[i]=Integer.valueOf(nums[i]);
            }
            quickSort(arr,0,arr.length-1);
        }
    }

    //the quick sort method
    public static void quickSort(int[]A,int p, int r){
        if(p<r) {
            int q=partition(A,p,r);
            quickSort(A,p,q-1);
            quickSort(A,q+1,r);
        }
    }

    //the method that partitions the array
    public static int partition(int[]A, int p, int r){
        int pivot=A[r];//select the last element as the pivot
        int i=p-1;
        for(int j=p;j<r;j++){
            if(A[j]<=pivot){
                i++;
                exchange(A,i,j);
            }
        }
        exchange(A,i+1,r);
        //print the array elements
        for(int m=0;m<A.length;m++){
            System.out.print(A[m]);
            if(m!=A.length-1) System.out.print(" ");
        }
        System.out.println();
        return i+1;
    }

    //exchange two elements in the array indexed by i and j
    public static void exchange(int[]A, int i, int j){
        int tmp=A[i];
        A[i]=A[j];
        A[j]=tmp;
    }
}
