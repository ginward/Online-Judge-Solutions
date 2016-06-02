import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jinhuawang on 6/1/16.
 * Solution to https://leetcode.com/problems/flatten-nested-list-iterator/
 */

interface NestedInteger {
    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    List<NestedInteger> getList();
}


public class NestedIterator implements Iterator<Integer> {

        ArrayList<Integer> list = new ArrayList<>();

        int cursor;

        public NestedIterator(List<NestedInteger> nestedList) {
            flattenHelper(nestedList);
            cursor=0;
        }

        public void flattenHelper(List<NestedInteger> nestedList){
            for(int i=0;i<nestedList.size();i++){
                NestedInteger integer=nestedList.get(i);
                if(integer.isInteger()){
                    list.add(integer.getInteger());
                } else {
                    flattenHelper(integer.getList());
                }
            }
        }

        @Override
        public Integer next() {
            if(cursor<list.size()) {
                int result=list.get(cursor);
                cursor++;
                return result;
            } else {
                return null;
            }
        }

        @Override
        public boolean hasNext() {
            return cursor<list.size();
        }

        //optional method
        @Override
        public void remove() {

        }
}
