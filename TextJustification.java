import java.util.LinkedList;
import java.util.List;

/**
 * Created by jinhuawang on 6/22/16.
 * Solution to https://leetcode.com/problems/text-justification/
 */
public class TextJustification {

    public static void main(String [ ] args){
        String[]words=new String[5];
        words[0]="What";
        words[1]="must";
        words[2]="be";
        words[3]="shall";
        words[4]="be.";
        Solution solution = new Solution();
        solution.fullJustify(words,12);
    }

    static class Solution {

        public List<String> result = new LinkedList<>();
        public int maxWidth = 0;

        public List<String> fullJustify(String[] words, int maxWidth) {
            int startIndex = 0;
            int length = words[0].length();
            this.maxWidth = maxWidth;
            if (words.length < 1) {
                result.add("");
                return result;
            }

            if (words.length == 1) {
                int numSpace = maxWidth - words[0].length();
                StringBuilder builder = new StringBuilder();
                builder.append(words[0]);
                for (int i = 0; i < numSpace; i++) {
                    builder.append(" ");
                }
                result.add(builder.toString());
                return result;
            }

            for (int i = 1; i < words.length; i++) {
                String str = words[i];
                length += str.length() + 1;
                if (length > maxWidth) {
                    result.add(constructStr(words, startIndex, i - 1));
                    startIndex = i;
                    length =words[i].length();
                }

                if (i == words.length - 1) {
                    result.add(constructStr(words, startIndex, i));
                }
            }
            return result;
        }

        public String constructStr(String[] words, int startIndex, int endIndex) {
            StringBuilder builder = new StringBuilder();
            int length = 0;
            if (startIndex == endIndex) {
                int num = maxWidth - words[startIndex].length();
                builder.append(words[startIndex]);
                for (int i = 0; i < num; i++) {
                    builder.append(" ");
                }
                return builder.toString();
            }
            //the last line
            if (endIndex==words.length-1){
                int len=0;
                for(int i=startIndex;i<=endIndex;i++){
                    len+=words[i].length();
                    builder.append(words[i]);
                    if(i!=endIndex){
                        builder.append(" ");
                    }
                }
                len+=endIndex-startIndex;
                int num=this.maxWidth-len;
                for(int i=0;i<num;i++) builder.append(" ");
                return builder.toString();
            }
            for (int i = startIndex; i <= endIndex; i++) {
                length += words[i].length();
            }
            int numSpace = maxWidth - length;
            int[] numArrSpace = new int[endIndex - startIndex];
            int m=0;
            while(numSpace>0){
                numSpace--;
                numArrSpace[m]++;
                m++;
                if(m==numArrSpace.length){
                    m=0;
                }
            }
            for (int i = startIndex; i <= endIndex; i++) {
                builder.append(words[i]);
                if (i != endIndex) {
                    for (int j = 0; j < numArrSpace[i-startIndex]; j++) {
                        builder.append(" ");
                    }
                }
            }
            return builder.toString();
        }
    }
}
