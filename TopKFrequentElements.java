import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by jinhuawang on 8/1/16.
 * Solution to https://leetcode.com/problems/top-k-frequent-elements/
 */
public class TopKFrequentElements {

    public static void main(String[] args){
        int[] nums=new int[6];
        nums[0]=1;
        nums[1]=1;
        nums[2]=1;
        nums[3]=2;
        nums[4]=2;
        nums[5]=3;
        List<Integer> list= topKFrequent(nums, 2);
        for(int i=0;i<list.size();i++){
            System.out.print(list.get(i));
        }
    }

    public static List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> result = new LinkedList<Integer>();
        Map<Integer, Integer>map=new HashMap<Integer, Integer>();
        //construct a hashmap consists of the frequencies
        for(int i=0;i<nums.length;i++){
            if(map.containsKey(nums[i])){
                map.put(nums[i],map.get(nums[i])+1);
            } else {
                map.put(nums[i], 1);
            }
        }
        FrenquentHeap heap=new FrenquentHeap();
        for(Integer key:map.keySet()){
            HeapElement element=new HeapElement();
            element.key=map.get(key);
            element.value=key;
            heap.heap_insert_key(element);
        }
        try {
            for (int i = 0; i < k; i++) {
                result.add(heap.heap_extract_max().value);
            }
        } catch (HeapLengthOutOfBound heapLengthOutOfBound) {
            heapLengthOutOfBound.printStackTrace();
        }
        return result;
    }
}

class HeapElement{
    boolean DEPRECATED=false;
    public int key;
    public int value;
}

class FrenquentHeap {

    public int HEAPSIZE=0;

    public static int DEPRECATED=-1;

    private HeapElement[]A;//the actual array that holds the heap

    //the constructor
    public FrenquentHeap(){
        max_queue_build();//build the priority queue
    }

    /*
    * Build the basic heap
    * */
    private void buildMaxHeap(HeapElement[]A){
        HEAPSIZE=A.length-1;
        A[0]=new HeapElement();
        A[0].DEPRECATED=true;//A[0] should not be used
        for(int i=HEAPSIZE/2;i>=1;i--){
            try {
                maxHeapify(A,i);
            } catch (HeapLengthOutOfBound e){
                System.out.println("Heap length should be greater than 1");
            }
        }
    }

    private void maxHeapify(HeapElement[]A, int i) throws HeapLengthOutOfBound{
        int largest=0;
        if(A.length<2){
            throw new HeapLengthOutOfBound("Heap length should be greater than 1");
        }
        if(A.length<3) return;//already a heap
        int l=left(i);
        int r=right(i);
        if(l<=HEAPSIZE&&A[l].key>A[i].key){
            largest=l;
        }
        else largest=i;
        if(r<=HEAPSIZE&&A[r].key>A[largest].key){
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
    private void exchange(HeapElement []arr, int m, int n){
        HeapElement tmp=arr[m];
        arr[m]=arr[n];
        arr[n]=tmp;
    }

    /*
    * Build the priority queue
    * */
    private void max_queue_build(){
        int size= 100000;
        A=new HeapElement[size];//allocate the array for the heap, note that A[0] is not used
    }

    public HeapElement heap_max(){
        return A[1];
    }

    public HeapElement heap_extract_max() throws HeapLengthOutOfBound {
        if (HEAPSIZE<1||A.length<2) throw new HeapLengthOutOfBound("Heap length too short");
        HeapElement max=A[1];
        A[1]=A[HEAPSIZE];
        HEAPSIZE=HEAPSIZE-1;
        maxHeapify(A, 1);
        return max;
    }

    //insert a key
    public void heap_insert_key(HeapElement key){
        //increase the heap size
        HEAPSIZE++;
        A[HEAPSIZE]=new HeapElement();
        A[HEAPSIZE].key= Integer.MIN_VALUE;
        heap_insert_helper(A, HEAPSIZE, key);
    }

    //insert a key in the heap, should only be called by heap_insert_key
    private void heap_insert_helper(HeapElement[] A, int i, HeapElement key){
        if(key.key<A[i].key) return;//the key will be positive in this question
        A[i]=key;
        while(i>1&&A[parent(i)].key<A[i].key){
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