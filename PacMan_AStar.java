import java.util.*;

/**
 * Created by jinhuawang on 7/3/16.
 * Solution to https://www.hackerrank.com/challenges/pacman-astar
 */
public class PacMan_AStar {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str;
        while((str = scanner.nextLine())!=null){

            //parse the first line, the position of the pacman
            String[]nums = str.split("\\s+");
            int pac_x=Integer.valueOf(nums[0]);
            int pac_y=Integer.valueOf(nums[1]);

            //parse the second line, the position of food
            str = scanner.nextLine();
            nums=str.split("\\s+");
            int food_x=Integer.valueOf(nums[0]);
            int food_y=Integer.valueOf(nums[1]);
            //parse the third line, the dimension of the grid
            str = scanner.nextLine();
            nums=str.split("\\s+");
            int dimension_x=Integer.valueOf(nums[0]);
            int dimension_y=Integer.valueOf(nums[1]);

            //init the array that holds the grid
            char[][] map=new char[dimension_x][dimension_y];
            //parse the grid
            for(int i=0;i<dimension_x;i++){
                str = scanner.nextLine();
                for(int j=0;j<str.length();j++){
                    map[i][j]=str.charAt(j);
                }
            }

            //solve the game
            AS_search(map, pac_x, pac_y, food_x, food_y, dimension_x, dimension_y);
        }
    }

    public static void AS_search(char[][]map, int pac_x, int pac_y, int food_x, int food_y, int dimension_x, int dimension_y){
        boolean[][] visited=new boolean[dimension_x][dimension_y];
        boolean[][] added=new boolean[dimension_x][dimension_y];
        Vertex_PAC[][]parent = new Vertex_PAC[dimension_x][dimension_y];
        int count=0;//the number of nodes explored
        List<Vertex_PAC> explored=new LinkedList<Vertex_PAC>();
        List<Vertex_PAC> path=new LinkedList<Vertex_PAC>();
        Comparator<Vertex_PAC> comparator=new VertexCostComparator();
        Queue<Vertex_PAC> neighbors=new PriorityQueue<Vertex_PAC>(10, comparator);
        visited[pac_x][pac_y]=true;
        Vertex_PAC root = new Vertex_PAC(pac_x, pac_y);
        Vertex_PAC goal = new Vertex_PAC(food_x, food_y);//the goal
        neighbors.add(root);
        while(!neighbors.isEmpty()){
            Vertex_PAC vertex = neighbors.remove();
            explored.add(vertex);
            count++;
            visited[vertex.x][vertex.y]=true;
            //goal found
            if(vertex.x==food_x&&vertex.y==food_y){
                break;
            }
            //up direction
            if(map[vertex.x-1][vertex.y]!='%'&&!visited[vertex.x-1][vertex.y]&&!added[vertex.x-1][vertex.y]){
                Vertex_PAC node=new Vertex_PAC(vertex.x-1, vertex.y);
                node.cost=count+Math.abs(node.x-goal.x)+Math.abs(node.y-goal.y);
                neighbors.add(node);
                added[vertex.x-1][vertex.y]=true;
                parent[vertex.x-1][vertex.y]=vertex;
            }

            //left direction
            if(map[vertex.x][vertex.y-1]!='%'&&!visited[vertex.x][vertex.y-1]&&!added[vertex.x][vertex.y-1]){
                Vertex_PAC node=new Vertex_PAC(vertex.x, vertex.y-1);
                node.cost=count+Math.abs(node.x-goal.x)+Math.abs(node.y-goal.y);
                neighbors.add(node);
                added[vertex.x][vertex.y-1]=true;
                parent[vertex.x][vertex.y-1]=vertex;
            }
            //right direction
            if(map[vertex.x][vertex.y+1]!='%'&&!visited[vertex.x][vertex.y+1]&&!added[vertex.x][vertex.y+1]){
                Vertex_PAC node=new Vertex_PAC(vertex.x, vertex.y+1);
                node.cost=count+Math.abs(node.x-goal.x)+Math.abs(node.y-goal.y);
                neighbors.add(node);
                added[vertex.x][vertex.y+1]=true;
                parent[vertex.x][vertex.y+1]=vertex;
            }
            //down direction
            if(map[vertex.x+1][vertex.y]!='%'&&!visited[vertex.x+1][vertex.y]&&!added[vertex.x+1][vertex.y]){
                Vertex_PAC node=new Vertex_PAC(vertex.x+1, vertex.y);
                node.cost=count+Math.abs(node.x-goal.x)+Math.abs(node.y-goal.y);
                neighbors.add(node);
                added[vertex.x+1][vertex.y]=true;
                parent[vertex.x+1][vertex.y]=vertex;
            }
        }

        if(goal!=null){
            Vertex_PAC node = goal;
            int path_count=0;
            path.add(node);
            while(node.x!=pac_x||node.y!=pac_y){
                node=parent[node.x][node.y];
                path.add(node);
                path_count++;
            }
            System.out.println(path_count);
            for(int i=path.size()-1;i>=0;i--){
                System.out.print(path.get(i).x);
                System.out.print(" ");
                System.out.print(path.get(i).y);
                System.out.println();
            }
        }
    }
}


class Vertex_PAC{
    int x;
    int y;
    int cost;
    Vertex_PAC(int x, int y){
        this.x=x;
        this.y=y;
        this.cost=0;
    }
}

//the comparator for the priority queue
class VertexCostComparator implements Comparator<Vertex_PAC> {
    @Override
    public int compare(Vertex_PAC v1, Vertex_PAC v2){
        if(v1.cost<v2.cost) {
            return -1;
        }
        if(v1.cost>v2.cost){
            return 1;
        }
        return 0;
    }
}