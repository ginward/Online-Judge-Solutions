/**
 * Created by jinhuawang on 6/9/16.
 * Solution to https://leetcode.com/problems/implement-trie-prefix-tree/
 */

public class Trie {

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