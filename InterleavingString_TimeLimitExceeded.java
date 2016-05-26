/**
 * Created by jinhuawang on 5/25/16.
 * Solution to https://leetcode.com/problems/interleaving-string/
 * This solution will exceed the time limit, but it demonstrates the idea or recursion
 * Algorithm is adopted from Peiyi Li
 */
public class InterleavingString_TimeLimitExceeded {
    public static void main(String [ ] args){
        System.out.print(isInterleave("abac","bbb","abbbbac"));
    }
    public static boolean isInterleave(String s1, String s2, String s3) {
        if(s3.length()!=s1.length()+s2.length()) return false;
        if(s1.length()==0&&s2.length()==0&&s3.length()==0) return true;
        if(s1.length()==0){
            if(s3.equals(s2)) return true;
            else return false;

        }
        if(s2.length()==0) {
            if(s3.equals(s1)) return true;
            else return false;
        }

        return interLeaveHelper(s1, 0, s2, 0, s3, 0);
    }
    public static boolean interLeaveHelper(String s1, int n1, String s2, int n2, String s3, int n3){
        if(n3==n1+n2&&n3==s3.length()-1){
            return true;
        }
        else if(n2>=s2.length()){
            if (s3.charAt(n3)==s1.charAt(n1))
            return interLeaveHelper(s1,n1+1,s2,n2,s3,n3+1);
            else return false;
        }
        else if(n1>=s1.length()){
            if(s3.charAt(n3)==s2.charAt(n2))
            return interLeaveHelper(s1,n1,s2,n2+1,s3,n3+1);
            else return false;
        }
        else if(s3.charAt(n3) ==s1.charAt(n1)&&s3.charAt(n3)==s2.charAt(n2)){
            return interLeaveHelper(s1, n1, s2, n2+1, s3, n3+1)||interLeaveHelper(s1, n1+1, s2, n2, s3, n3+1);
        }
        else if (s3.charAt(n3)==s1.charAt(n1)){
            return interLeaveHelper(s1, n1+1, s2, n2, s3, n3+1);
        }
        else if(s3.charAt(n3)==s2.charAt(n2)){
            return interLeaveHelper(s1, n1, s2, n2+1, s3, n3+1);
        }
        else return false;
    }
}
