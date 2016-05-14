import java.util.Scanner;

/**
 * Created by jinhuawang on 5/14/16.
 * Solution to problem: https://www.hackerrank.com/challenges/jesse-and-cookies
 */
public class JesseCookies {

    public static int HEAPSIZE = 0;
    public static int DEPRECATED = -1;
    public static int MIN_SWEET = -1;//minimum amount of sweetness
    public static int IMPOSSIBLE = -1;

    public static void main(String[] args) throws HeapLengthOutOfBound {
        Scanner scanner = new Scanner(System.in);
        String str;
        while((str = scanner.nextLine())!=null){
            String[]nums = str.split("\\s+");
            int first_num = Integer.valueOf(nums[0]);
            int second_num = Integer.valueOf(nums[1]);
            HEAPSIZE = first_num;
            MIN_SWEET = second_num;
            int[]heap=new int[HEAPSIZE+1];//the cookies
            str = scanner.nextLine();
            String[]cookies = str.split("\\s+");
            heap[0]=DEPRECATED;
            for(int i=0;i<cookies.length;i++){
                heap[i+1]=Integer.valueOf(cookies[i]);
            }
            build_min_heap(heap);//build the heap
            cook(heap);
        }
    }

    //the actual function that solves the problem
    private static void cook(int[]A) throws HeapLengthOutOfBound {
        //examine if the cookies already satisfy the criteria
        boolean satisfy = false;
        int count=0;
        satisfy=checkSatisfaction(A);
        if(satisfy == true) {
            System.out.println(count);
            return;
        }
        //when the criteria is still not satisfied
        while (satisfy==false){
            if(HEAPSIZE<=1){
                System.out.println(IMPOSSIBLE);
                break;//avoid dead-loop
            }
            count++;
            int x=heap_extract_min(A);
            int y=heap_extract_min(A);
            int z=sweet_formula(x,y);
            heap_insert_key(A,z);
            satisfy=checkSatisfaction(A);
        }
        if(satisfy==true){
            System.out.println(count);
        }

    }

    private static boolean checkSatisfaction(int[] A){
        //Note that A[0] is not used
        for(int i=1;i<=HEAPSIZE;i++){
            if(A[i]<MIN_SWEET) {
                return false;
            };
        }
        return true;
    }

    //formula to calculate the sweetness
    private static int sweet_formula (int last,int second_last){
        return (last+2*second_last);
    }

    /*
    * Build the basic heap
    * */
    private static void build_min_heap(int[]A){
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
    private static void minHeapify(int[]A, int i) throws HeapLengthOutOfBound {
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
    private static int parent(int i){
        return i/2;
    }

    //get the left node
    private static int left(int i){
        return 2*i;
    }

    //get the right node
    private static int right(int i){
        return 2*i+1;
    }

    private static void exchange(int []arr, int m, int n){
        int tmp=arr[m];
        arr[m]=arr[n];
        arr[n]=tmp;
    }


    //test if the heap is correctly built
    private static void testHeap(){
        int[]test = new int[10];
        for(int i=9;i>=0;i--){
            test[9-i]=i;
        }
        for(int i=0;i<test.length;i++){
            System.out.print(test[i]);
        }
        System.out.println();
        build_min_heap(test);
        for(int i=0;i<test.length;i++){
            System.out.print(test[i]);
        }
    }

    /*
    * Build the priority queue
    * */
    private static int heap_min(int[] A){
        return A[1];
    }

    private static int heap_extract_min(int[]A) throws HeapLengthOutOfBound {
        if (HEAPSIZE<1||A.length<2) throw new HeapLengthOutOfBound("Heap length too short");
        int min=A[1];
        A[1]=A[HEAPSIZE];
        HEAPSIZE=HEAPSIZE-1;
        minHeapify(A,1);
        return min;
    }

    //increase a key in the heap
    private static void heap_insert_helper(int[] A, int i, int key){
        if(key>A[i]) return;
        A[i]=key;
        while(i>1&&A[parent(i)]>A[i]){
            exchange(A, i, parent(i));
            i=parent(i);
        }
    }

    //insert a key
    private static void heap_insert_key(int[]A, int key){
        //increase the heap size
        if(HEAPSIZE!=A.length)
        HEAPSIZE++;
        A[HEAPSIZE]= (int) Math.pow(10,10);
        heap_insert_helper(A, HEAPSIZE, key);
    }

}

class HeapLengthOutOfBound extends Exception {
    public HeapLengthOutOfBound(String msg){
        super(msg);
    }
}