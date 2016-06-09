/**
 * Created by jinhuawang on 6/9/16.
 * Solution to https://leetcode.com/problems/word-search/
 */
public class WordSearchI {

    public static void main(String[] args){
        char[][]board=new char[1][1];
        board[0][0]='a';
        String word="ab";
        System.out.print(exist(board, word));
    }

    static boolean [][]visited;

    public static boolean exist(char[][] board, String word) {
        if(board.length<1||board[0].length<1||word.length()<1) return false;
        visited=new boolean[board.length][board[0].length];
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(contains(board, word, i,j,0)){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean contains(char[][]board, String word, int board_m, int board_n, int word_i){
        if(word_i>=word.length()) return true;
        if(board_m>=board.length||board_n>=board[0].length)
            return false;
        if(board_m<0||board_n<0||word_i<0) return false;
        if(visited[board_m][board_n]) return false;
        if(word.charAt(word_i)!=board[board_m][board_n]) return false;
        visited[board_m][board_n]=true;
        boolean result= contains(board,word, board_m+1, board_n, word_i+1)||contains(board,word,board_m,board_n+1,word_i+1)
                    ||contains(board,word, board_m-1, board_n, word_i+1)||contains(board,word,board_m,board_n-1,word_i+1);
        visited[board_m][board_n]=false;
        return result;
    }
}
