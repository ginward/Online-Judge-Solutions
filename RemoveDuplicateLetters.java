import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by jinhuawang on 8/8/16.
 * Solution to https://leetcode.com/problems/remove-duplicate-letters/
 */
public class RemoveDuplicateLetters {

    public static void main(String[] args){
        System.out.print(removeDuplicateLetters("bbcaac"));
    }

    public static String removeDuplicateLetters(String s) {
        String result="";
        Map<Character, Integer> counter = new HashMap<Character, Integer>();
        Set<Character> visited = new HashSet<Character>();
        StringBuilder sb = new StringBuilder();

        for(int i=0;i<s.length();i++){
            if(!counter.containsKey(s.charAt(i))){
                counter.put(s.charAt(i),0);
            }
            Integer tmp_count=counter.get(s.charAt(i));
            tmp_count++;
            counter.put(s.charAt(i), tmp_count);
        }

        for(int i=0;i<s.length();i++){
            Integer tmp_count=counter.get(s.charAt(i));
            tmp_count--;
            counter.put(s.charAt(i), tmp_count);
            if(visited.contains(s.charAt(i))){
                continue;
            }
            while(sb.length()>0&&s.charAt(i)<sb.charAt(sb.length()-1)&&counter.get(sb.charAt(sb.length()-1))>0){
                visited.remove(sb.charAt(sb.length()-1));
                sb.setLength(sb.length()-1);//delete the last character of the string builder
            }
            sb.append(s.charAt(i));
            visited.add(s.charAt(i));
        }
        result=sb.toString();
        return result;
    }

}
