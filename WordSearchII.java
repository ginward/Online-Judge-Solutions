import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by jinhuawang on 6/9/16.
 * Solution to https://leetcode.com/problems/word-search-ii/
 */
public class WordSearchII {

    //the trie tree that holds the prefixes
    static Trie trie;

    //the list that holds the result
    static Set<String> result;

    //mark the node as visited
    static boolean [][]visited;

    public List<String> findWords(char[][] board, String[] words) {
        result = new HashSet<>();
        if(board.length==0||board[0].length==0||words.length==0) return new ArrayList<>(result);
        visited=new boolean[board.length][board[0].length];
        //construct the Trie
        trie=new Trie();
        for(int i=0;i<words.length;i++){
            trie.insert(words[i]);
        }
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                contains(board,"",i,j);
            }
        }
        return new ArrayList<>(result);
    }

    public static void contains(char[][]board, String str, int board_m, int board_n){
        if(board_m>=board.length||board_n>=board[0].length||board_m<0||board_n<0)
            return;
        if(visited[board_m][board_n]) return;
        str+=board[board_m][board_n];
        //pruning
        if(!trie.startsWith(str)) return;
        if(trie.search(str)) result.add(str);
        visited[board_m][board_n]=true;
        contains(board,str, board_m+1, board_n);
        contains(board,str,board_m,board_n+1);
        contains(board,str, board_m-1, board_n);
        contains(board,str,board_m,board_n-1);
        visited[board_m][board_n]=false;
    }

    class Trie {

        final public static int ALPHABET_SIZE=26;

        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        // Inserts a word into the trie.
        public void insert(String word) {
            int level=word.length();
            TrieNode node = root;
            for(int i=0;i<level;i++){
                if(node.children[getAlphabetIndex(word.charAt(i))]==null){
                    node.children[getAlphabetIndex(word.charAt(i))]=new TrieNode();
                }
                node=node.children[getAlphabetIndex(word.charAt(i))];
            }
            node.isLeaf=true;
        }

        // Returns if the word is in the trie.
        public boolean search(String word) {
            TrieNode node=root;
            for(int i=0;i<word.length();i++){
                if(node.children[getAlphabetIndex(word.charAt(i))]==null){
                    return false;
                }
                node=node.children[getAlphabetIndex(word.charAt(i))];
            }
            if(node.isLeaf==true) return true;
            else return false;
        }

        // Returns if there is any word in the trie
        // that starts with the given prefix.
        public boolean startsWith(String prefix) {
            TrieNode node=root;
            for(int i=0;i<prefix.length();i++){
                if(node.children[getAlphabetIndex(prefix.charAt(i))]==null){
                    return false;
                }
                node=node.children[getAlphabetIndex(prefix.charAt(i))];
            }
            return true;
        }

        public int getAlphabetIndex(char letter){
            return letter-'a';
        }
    }

    class TrieNode {
        TrieNode children[];
        boolean isLeaf;
        // Initialize your data structure here.
        public TrieNode() {
            //initialize new TrieNode
            children=new TrieNode[Trie.ALPHABET_SIZE];
            isLeaf=false;
        }
    }

}
