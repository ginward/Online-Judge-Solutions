import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Created by jinhuawang on 5/14/16.
 * Solution to https://www.hackerrank.com/challenges/find-median-1
 */

public class FindMedian {

    public static void main(String[] args) throws HeapLengthOutOfBound, InterruptedException {

        Scanner scanner = new Scanner(System.in);
        String str;
        while((str = scanner.nextLine())!=null){
            String[]nums = str.split("\\s+");
            int size=Integer.valueOf(nums[0]);
            int[]input=new int[size];
            //read the cases from the input line
            for(int i=0;i<size;i++){
                str = scanner.nextLine();
                input[i]=Integer.valueOf(str);
            }
            findMedian(input,input.length);
        }

    }
    //the class that actually solves the problem and prints it
    public static void findMedian(int []input, int end) throws HeapLengthOutOfBound, InterruptedException {
        /*
        MaxHeap heap = new MaxHeap();
        for(int i=0;i<end;i++) {
            heap.heap_insert_key(input[i]);
        }
        System.out.println(checkHeap(heap));
        */
        int size= end;
        MaxHeap maxH = new MaxHeap();
        MinHeap minH = new MinHeap();
        //build the max and min heap
        //min>max must be maintained
        for(int i=0;i<end;i++){
            if(maxH.HEAPSIZE==minH.HEAPSIZE){
                if(input[i]<=maxH.heap_max()){
                    maxH.heap_insert_key(input[i]);
                }
                else {
                    minH.heap_insert_key(input[i]);
                }
            }
            else {
                if(maxH.HEAPSIZE>minH.HEAPSIZE){
                    if(input[i]>=maxH.heap_max()){
                        minH.heap_insert_key(input[i]);
                    }
                    else if(input[i]<maxH.heap_max()) {
                            int tmp=maxH.heap_extract_max();
                            minH.heap_insert_key(tmp);
                            maxH.heap_insert_key(input[i]);
                    }
                }
                else if(maxH.HEAPSIZE<minH.HEAPSIZE) {
                    if(input[i]<=minH.heap_min()){
                        maxH.heap_insert_key(input[i]);
                    }
                    else if(input[i]>minH.heap_min()) {
                            int tmp=minH.heap_extract_min();
                            maxH.heap_insert_key(tmp);
                            minH.heap_insert_key(input[i]);
                    }
                }
            }
            printMedian(i,input,maxH,minH);
        }
    }

    private static void printMedian(int end, int[] input,MaxHeap maxH, MinHeap minH) throws InterruptedException {

        StringBuilder sb = new StringBuilder();
        final String NEW_LINE = System.getProperty("line.separator");
        DecimalFormat df = new DecimalFormat("0.0");//keep one decimal place
        if(end==0) {
            System.out.println((double)input[end]);
            return;
        }
        if (end==1){
            System.out.println(df.format(((double)input[0] + (double)input[1])/2));
            return;
        }
        //if the input size is even number
        if(end%2!=0){
            sb.append(df.format(((double)maxH.heap_max()+(double)minH.heap_min())/2));
        } else {
            if(maxH.HEAPSIZE>minH.HEAPSIZE) {
                sb.append((double) maxH.heap_max());
            } else {
                sb.append((double) minH.heap_min());

            }
        }
        sb.append(NEW_LINE);
        System.out.print(sb.toString());
    }

}


/*
* The max heap class
* */
class MaxHeap {

    public int HEAPSIZE=0;

    public static int DEPRECATED=-1;

    private int[]A;//the actual array that holds the heap

    //the constructor
    public MaxHeap(){
        max_queue_build();//build the priority queue
    }

    /*
    * Build the basic heap
    * */
    private void buildMaxHeap(int[]A){
        HEAPSIZE=A.length-1;
        A[0]=DEPRECATED;//A[0] should not be used
        for(int i=HEAPSIZE/2;i>=1;i--){
            try {
                maxHeapify(A,i);
            } catch (HeapLengthOutOfBound e){
                System.out.println("Heap length should be greater than 1");
            }
        }
    }

    private void maxHeapify(int[]A, int i) throws HeapLengthOutOfBound{
        int largest=0;
        if(A.length<2){
            throw new HeapLengthOutOfBound("Heap length should be greater than 1");
        }
        if(A.length<3) return;//already a heap
        int l=left(i);
        int r=right(i);
        if(l<=HEAPSIZE&&A[l]>A[i]){
            largest=l;
        }
        else largest=i;
        if(r<=HEAPSIZE&&A[r]>A[largest]){
            largest=r;
        }
        if(largest!=i){
            exchange(A, i, largest);
            maxHeapify(A, largest);
        }
    }

    //get the parent node
    private int parent(int i){
        return i/2;
    }

    //get the left node
    private int left(int i){
        return 2*i;
    }

    //get the right node
    private int right(int i){
        return 2*i+1;
    }

    //exchange the two elements in the array
    private void exchange(int []arr, int m, int n){
        int tmp=arr[m];
        arr[m]=arr[n];
        arr[n]=tmp;
    }

    /*
    * Build the priority queue
    * */
    private void max_queue_build(){
        int size= (int)Math.pow(10,7)+1;
        A=new int[size];//allocate the array for the heap, note that A[0] is not used
    }

    public int heap_max(){
        return A[1];
    }

    public int heap_extract_max() throws HeapLengthOutOfBound {
        if (HEAPSIZE<1||A.length<2) throw new HeapLengthOutOfBound("Heap length too short");
        int max=A[1];
        A[1]=A[HEAPSIZE];
        HEAPSIZE=HEAPSIZE-1;
        maxHeapify(A, 1);
        return max;
    }

    //insert a key
    public void heap_insert_key(int key){
        //increase the heap size
        HEAPSIZE++;
        A[HEAPSIZE]= -1;
        heap_insert_helper(A, HEAPSIZE, key);
    }

    //insert a key in the heap, should only be called by heap_insert_key
    private void heap_insert_helper(int[] A, int i, int key){
        if(key<A[i]) return;//the key will be positive in this question
        A[i]=key;
        while(i>1&&A[parent(i)]<A[i]){
            exchange(A, i, parent(i));
            i=parent(i);
        }
    }

}

/*
* The min heap class
* */
class MinHeap{

    public int HEAPSIZE=0;

    public static int DEPRECATED = -1;

    private int[]A;//the actual array that holds the heap

    public MinHeap(){
        min_queue_build();
    }

    /*
    * Build the basic heap
    * */
    private void build_min_heap(int[]A){
        HEAPSIZE=A.length-1;
        A[0]=DEPRECATED;//A[0] should not be used
        for(int i=HEAPSIZE/2;i>=1;i--){
            try {
                minHeapify(A,i);
            } catch (HeapLengthOutOfBound e){
                System.out.println("Heap length should be greater than 1");
            }
        }
    }

    //Maintaining the heap property
    //Note that A[0] should not be used
    private void minHeapify(int[]A, int i) throws HeapLengthOutOfBound {
        int smallest=0;
        if(A.length<2) {
            throw  new HeapLengthOutOfBound("Heap length should be greater than 1");
        }
        if(A.length<3) return;
        int l=left(i);
        int r=right(i);
        if(l<=HEAPSIZE&&A[l]<A[i]){
            smallest=l;
        } else {
            smallest=i;
        }
        if(r<=HEAPSIZE && A[r]<A[smallest]){
            smallest=r;
        }
        if (smallest!=i){
            exchange(A,i,smallest);
            minHeapify(A,smallest);
        }
    }

    //get the parent node
    private int parent(int i){
        return i/2;
    }

    //get the left node
    private int left(int i){
        return 2*i;
    }

    //get the right node
    private int right(int i){
        return 2*i+1;
    }

    //exchange the two elements in the array
    private void exchange(int []arr, int m, int n){
        int tmp=arr[m];
        arr[m]=arr[n];
        arr[n]=tmp;
    }

    /*
    * Build the priority queue
    * */
    private void min_queue_build(){
        int size= (int)Math.pow(10,7)+1;
        A=new int[size];//allocate the array for the heap, note that A[0] is not used
    }

    public int heap_min(){
        return A[1];
    }

    public int heap_extract_min() throws HeapLengthOutOfBound {
        if (HEAPSIZE<1||A.length<2) throw new HeapLengthOutOfBound("Heap length too short");
        int min=A[1];
        A[1]=A[HEAPSIZE];
        HEAPSIZE=HEAPSIZE-1;
        minHeapify(A,1);
        return min;
    }

    //insert a key
    public void heap_insert_key(int key){
        //increase the heap size
        HEAPSIZE++;
        A[HEAPSIZE]= (int) Math.pow(10,10);
        heap_insert_helper(A, HEAPSIZE, key);
    }

    //insert a key in the heap
    private void heap_insert_helper(int[] A, int i, int key){
        if(key>A[i]) return;
        A[i]=key;
        while(i>1&&A[parent(i)]>A[i]){
            exchange(A, i, parent(i));
            i=parent(i);
        }
    }

}


class HeapLengthOutOfBound extends Exception {
    public HeapLengthOutOfBound(String msg){
        super(msg);
    }
}