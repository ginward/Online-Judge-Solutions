import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by jinhuawang on 5/24/16.
 * Solution to https://leetcode.com/problems/minimum-window-substring/
 */
public class MinimumWindowSub {
    public static void main(String [ ] args){
        System.out.print(minWindow("ADOBECODEBANC", "ABC"));
    }
    public static String minWindow(String s, String t) {
        String result=new String();
        /* the table to map index to a linked list of Nodes
         * the first of deque should be the smallest index, the last of deque should be the largest index
         */
        Map<Character, Deque<Node>> table= new HashMap<>();
        minLinkedList<Node> list=new minLinkedList<Node>();
        int minLength=Integer.MAX_VALUE;
        int window_begin=-1;
        int window_end=-1;
        for(int i=0;i<t.length();i++){
            synchronized (table) {
                if(!table.containsKey(Character.valueOf(t.charAt(i)))) {
                    table.put(Character.valueOf(t.charAt(i)), new LinkedList<Node>());
                }
                Node node=new Node(i, t.charAt(i));
                table.get(Character.valueOf(t.charAt(i))).addLast(node);
            }
        }
        for(int i=0;i<s.length();i++){
            synchronized (table){
                if(table.containsKey(Character.valueOf(s.charAt(i)))){
                    Deque<Node> nodes=  table.get(Character.valueOf(s.charAt(i)));
                    //the list is already full
                    Node garbage= nodes.pollFirst();
                    //delete the entry from the list
                    if(garbage.entry!=null){
                        list.remove(garbage.entry);
                    }
                    Node freshNode = new Node(i,s.charAt(i));
                    //reference the entry to be deleted
                    freshNode.reference(list.addLast(freshNode));
                    nodes.addLast(freshNode);
                    if(minLength<=list.getLast().index-list.getFirst().index){
                        continue;
                    }
                    if(list.size==t.length()) {
                        window_begin = list.getFirst().index;
                        window_end = list.getLast().index;
                        minLength = list.getLast().index - list.getFirst().index;
                    }
                }
            }
        }
        if(window_begin!=-1&&window_end!=-1&&minLength!=Integer.MAX_VALUE)
            result=s.substring(window_begin, window_end+1);
        else
            result="";
        return result;
    }
    static class Node {
        int index;
        char value;
        minLinkedList<Node>.Entry<Node> entry;//reference the entry inside the linkedlist
        Node(int v, char c){
            this.index=v;
            this.value=c;
            entry=null;
        }
        void reference(minLinkedList<Node>.Entry<Node> entry){
            this.entry=entry;
        }
    }
    /*
     * A reduced version of linked list class for Entry accessing only
     * */
    static class minLinkedList<E>{
        private Entry<E> header=new Entry<E>(null, null, null);
        private int size=0;
        public minLinkedList(){
            header.next=header.previous=header;
        }
        public class Entry<E>{
            E element;
            Entry<E> next;
            Entry<E> previous;
            Entry(E element, Entry<E> next, Entry<E> previous){
                this.element = element;
                this.next=next;
                this.previous= previous;
            }
        }
        public E getFirst(){
            return header.next.element;
        }
        public E getLast(){
            return header.previous.element;
        }
        public Entry<E> addFirst(E e){
            return addBefore(e, header.next);
        }
        public Entry<E> addLast(E e){
            return addBefore(e, header);
        }
        public Entry<E> addBefore(E e, Entry<E> entry ){
            Entry<E> newEntry=new Entry<E>(e, entry, entry.previous);
            newEntry.previous.next=newEntry;
            newEntry.next.previous=newEntry;
            size++;
            return newEntry;
        }
        public E remove(Entry<E> e){
            E result=e.element;
            e.previous.next=e.next;
            e.next.previous=e.previous;
            e.next=e.previous=null;
            e.element=null;
            size--;
            return result;
        }
        public boolean contains(Object o){
            return indexOf(o)!=-1;
        }
        public int indexOf(Object o){
            int index=0;
            if(o==null){
                for(Entry e=header.next;e!=header;e=e.next){
                    if(e.element==null)
                        return index;
                    index++;
                }
            } else {
                for(Entry e=header.next;e!=header;e=e.next){
                    if(o.equals(e.element))
                        return index;
                    index++;
                }
            }
            return -1;
        }
    }

}
