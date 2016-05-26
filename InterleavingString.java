/**
 * Created by jinhuawang on 5/26/16.
 * Solution to https://leetcode.com/problems/interleaving-string/
 */
public class InterleavingString {
    public static void main(String [ ] args){
        System.out.print(isInterleave("db","b","dbb"));
    }
    public static boolean isInterleave(String s1, String s2, String s3){
        //handles some base cases
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
        boolean[]table=new boolean[s2.length()+1];
        for(int i=1;i<=s2.length();i++){
            if(s2.charAt(i-1)==s3.charAt(i-1)){
                table[i]=true;
            }
        }
        table[0]=true;
        for(int j=1;j<=s1.length();j++){
            if(table[0]&&s1.charAt(j-1)==s3.charAt(j-1)) {
                table[0] = true;
            } else
                table[0]=false;
            for(int i=1;i<=s2.length();i++){
                table[i]=(table[i]&&s1.charAt(j-1)==s3.charAt(i+j-1)||table[i-1]&&s2.charAt(i-1)==s3.charAt(i+j-1));
            }
        }
        return table[s2.length()];
    }
}