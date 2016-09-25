import java.util.*;

/**
 * Created by jinhuawang on 8/15/16.
 * Solution to https://leetcode.com/problems/remove-invalid-parentheses/
 */

public class RemoveInvalidParentheses {

    public static List<String> removeInvalidParentheses(String s) {
        List<String> res = new LinkedList<String>();
        Set<String> visited = new HashSet<String>();
        Queue<String> queue=new LinkedList<String>();
        //if the original string is already valid
        if(validate(s)) {
            res.add(s);
            return res;
        };
        queue.add(s);
        visited.add(s);
        boolean found=false;
        while(!queue.isEmpty()){
            String stringToCheck=queue.poll();
            if(validate(stringToCheck)) {
                res.add(stringToCheck);
                found=true;
            }
            if(found) {
                continue;
            }
            for(int i=0;i<stringToCheck.length();i++){
                if (stringToCheck.charAt(i) != '(' && stringToCheck.charAt(i) != ')') continue;
                String tmp=stringToCheck.substring(0, i)+stringToCheck.substring(i+1);
                if(!visited.contains(tmp)) {
                    queue.add(tmp);
                    visited.add(tmp);
                }
            }
        }
        if(res.isEmpty()) res.add("");
        return res;
    }

    //to validate if the string has valid parentheses
    public static boolean validate(String s){
        int count=0;
        for (int i=0;i<s.length();i++) {
            if (count < 0) return false;
            if (s.charAt(i) == '(') {
                count++;
            } else if (s.charAt(i) == ')') {
                count--;
            } else {
                continue;
            }
        }
        if (count!=0) return false;
        return true;
    }

    public static void main(String[] args) {
        List list = removeInvalidParentheses("()(((((((()");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            System.out.println();
        }
    }
}
