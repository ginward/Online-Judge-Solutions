import java.util.ArrayList;
import java.util.List;

/**
 * Created by jinhuawang on 6/9/16.
 * Solution to https://leetcode.com/problems/word-search-ii/
 * This solution uses recursion but it will exceed time limit
 */
public class WordSearchII_TimeLimitExceeded {

    public static void main(String[] args){
        char[][] board=new char[4][4];
        board[0][0]='o';
        board[0][1]='a';
        board[0][2]='a';
        board[0][3]='n';
        board[1][0]='e';
        board[1][1]='t';
        board[1][2]='a';
        board[1][3]='e';
        board[2][0]='i';
        board[2][1]='h';
        board[2][2]='k';
        board[2][3]='r';
        board[3][0]='i';
        board[3][1]='f';
        board[3][2]='l';
        board[3][3]='v';
        String[] words=new String[4];
        words[0]="oath";
        words[1]="pea";
        words[2]="eat";
        words[3]="rain";
        List<String> result=findWords(board, words);
        for (int i=0;i<result.size();i++){
            System.out.println(result.get(i));
        }
    }

    public static List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        if(board.length==0||board[0].length==0||words.length==0) return result;
        for(int i=0;i<words.length;i++){
            String word=words[i];
            if(contains(board, word, 0,0,0)){
                result.add(word);
            }
        }
        return result;
    }

    public static boolean contains(char[][]board, String word, int board_m, int board_n, int word_i){
        if(word_i>=word.length()) return true;
        if(board_m>=board.length||board_n>=board[0].length)
            return false;
        if(board_m<0||board_n<0||word_i<0) return false;
        if(word.charAt(word_i)==board[board_m][board_n]){
            return contains(board,word, board_m+1, board_n, word_i+1)||contains(board,word,board_m,board_n+1,word_i+1)
                    ||contains(board,word, board_m-1, board_n, word_i+1)||contains(board,word,board_m,board_n-1,word_i+1);
        } else {
            return contains(board,word,board_m+1,board_n,word_i)||contains(board,word,board_m,board_n+1,word_i);
        }
    }
}