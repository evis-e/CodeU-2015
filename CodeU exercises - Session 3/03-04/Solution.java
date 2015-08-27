import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

class QueryStream{
    class Query{
        long timeInMilliSeconds;
        String words;
        public Query(String words){
            this.words = words;
            timeInMilliSeconds = System.currentTimeMillis();
        }
    }

    List<Query> list = new ArrayList<Query>();

    public QueryStream(){
        list.add(new Query("hello world"));
        list.add(new Query("hi"));
        list.add(new Query("keep calm and code on"));
        list.add(new Query("hey"));
        list.add(new Query("OK Google"));
    }
    Iterator iterator = new Iterator() {
        int queryIndex = 0;        // the index of the query that contains the new word
        int wordIndex = 0;         // the starting index of the next word
        boolean newQuery = false;  // tells whether the next string to be printed is <NEWQUERY>

        @Override
        public boolean hasNext() {
            if(queryIndex >= list.size()) {
                return false;
            }
            return true;
        }

        @Override
        public Object next() {
            if(newQuery){
                newQuery = false;
                // calculate the time difference between the two queries
                long a = list.get(queryIndex - 1).timeInMilliSeconds;
                long b = list.get(queryIndex).timeInMilliSeconds;
                return "<NEWQUERY> " + (b-a);
            }
            String currStr;
            currStr = list.get(queryIndex).words + " ";  // I added an empty character at the end to make the splitting easier

            int newIndex = currStr.indexOf(" ", wordIndex);
            String res = currStr.substring(wordIndex, newIndex);   // inclusive, exclusive
            wordIndex = newIndex+1;
            if(wordIndex >= list.get(queryIndex).words.length()){
                queryIndex++;
                wordIndex = 0;
                newQuery = true;
            }
            return res;
        }
    };
}
public class Solution {
    public static void main(String[] str){
        QueryStream queryStream = new QueryStream();
        Iterator iterator = queryStream.iterator;
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
