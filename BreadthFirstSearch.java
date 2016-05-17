
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by jinhuawang on 5/17/16.
 * Solution to https://www.hackerrank.com/challenges/bfsshortreach
 */
public class BreadthFirstSearch {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String str;
        while((str = scanner.nextLine())!=null){
            String[]nums = str.split("\\s+");
            int times=Integer.valueOf(nums[0]);//number of test cases
            for(int i=0;i<times;i++){
                str = scanner.nextLine();
                nums = str.split("\\s+");
                int node_num=Integer.valueOf(nums[0]);
                int edge_num=Integer.valueOf(nums[1]);
                Graph g=new Graph(node_num,edge_num);
                for(int j=0;j<edge_num;j++) {
                    //g.insertEdge();
                    str = scanner.nextLine();
                    nums = str.split("\\s+");
                    int v1=Integer.valueOf(nums[0]);
                    int v2=Integer.valueOf(nums[1]);
                    g.insertEdge(v1-1,v2-1);
                }
                str = scanner.nextLine();
                nums = str.split("\\s+");
                int source=Integer.valueOf(nums[0])-1;
                g.BFS(g,source);
                for(int m=0;m<g.vertices.length;m++){
                    if(m!=source) {
                        System.out.print(g.vertices[m].distance);
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        }
    }
}

/*
 * The class that represents the Graph
 * Adjacency-list representation is used
 * */
class Graph{
    final public static int EDGE_LENGTH=6;
    LinkedList<Integer>[]adj;//the adjacency array that holds the lists
    Queue<Integer> queue = new LinkedList<Integer>();//the queue that holds the next element to be visited
    Vertex []vertices;
    /*
     *  @param V: the number of vertices
     *  @param E: the number of edges
     * */
    public Graph(int V, int E){
        adj = new LinkedList[V];
        for(int i=0;i<V;i++){
            adj[i]=new LinkedList<Integer>();
        }
        vertices=new Vertex[V];
        for(int i=0;i<V;i++){
            vertices[i]=new Vertex(i);
        }
    }

    //insert the edge into the graph
    /*
     * @params  source: the source node
      *         destination: the destination node
      *         flag: if the flag is 1, call insert(destination, source)
     * */
    public void insertEdge(int source, int destination, int ...flags){
        if(adj==null) return;//the array has not been initialized
        if(source>=adj.length||destination>=adj.length){
            return;
        }
        //if the edge is already in the graph
        if(adj[source].contains(destination)){
            return;
        }
        adj[source].add(destination);
        //undirected graph
        if(flags.length==0)
        insertEdge(destination, source, 1);
    }

    /*
     * the actual breadth first search algorithm
     * @params: source the source Vertex
     *          target the target Vertex
     */
    public void BFS(Graph g, int source){
        vertices[source].color = Vertex.VERTEX_GREY;
        vertices[source].distance=0;
        vertices[source].parent=Vertex.PARENT_NULL;
        queue.add(source);
        while (!queue.isEmpty()){
            int u=queue.poll();
            for(int v : adj[u]){
                if(vertices[v].color==Vertex.VERTEX_WHITE){
                    vertices[v].color=Vertex.VERTEX_GREY;
                    vertices[v].distance=vertices[u].distance+EDGE_LENGTH;
                    vertices[v].parent=vertices[u].index;
                    queue.add(v);
                }
            }
            vertices[u].color=Vertex.VERTEX_BLACK;
        }
    }
}

/*
 * The class that represents the vertex of the Graph
 * */
class Vertex {
    final public static int INFINITY=-1;//show that the note is not reachable
    final public static int PARENT_NULL=-2;
    final public static int VERTEX_WHITE=0;
    final public static int VERTEX_GREY=1;
    final public static int VERTEX_BLACK=2;

    int index;//the index of the vertex
    int color;//the color of the vertex
    int parent; //the parent of the vertex
    int distance;//the distance of the vertex

    //initialize the vertex
    public Vertex(int index) {
        this.index = index;
        this.color=VERTEX_WHITE;
        this.parent=PARENT_NULL;
        this.distance= INFINITY;
    }

}