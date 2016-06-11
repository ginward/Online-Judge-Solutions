/**
 * Created by jinhuawang on 6/11/16.
 * Solution to https://leetcode.com/problems/add-and-search-word-data-structure-design/
 */
public class WordDictionary {
    /*
    public static void main(String[] args){
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("a");
        System.out.print(wordDictionary.search("a"));
    }
    */

    Trie trie;

    WordDictionary(){
        trie=new Trie();
    }

    // Adds a word into the data structure.
    public void addWord(String word) {
        trie.insert(word);
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return trie.special_search(word, null, 0);
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

        public boolean special_search(String word, TrieNode root_node, int j){
            if(root_node==null) root_node=root;
            for(int i=j;i<word.length();i++){
                if(word.charAt(i)=='.'){
                    for (int m=0;m<ALPHABET_SIZE;m++){
                        if(root_node.children[m]!=null)
                             if(special_search(word,root_node.children[m],i+1)){
                                 return true;
                             }
                    }
                    return false;
                } else {
                    if(root_node.children[getAlphabetIndex(word.charAt(i))]==null){
                        return false;
                    }
                    root_node=root_node.children[getAlphabetIndex(word.charAt(i))];
                }
            }
            if(root_node.isLeaf) return true;
            return false;
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
