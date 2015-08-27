import java.util.HashMap;
import java.util.Map;

public class Solution {
    /* Questions I would ask to the interviewer:
     * - Are the words in the dictionary ordered? (I assumed that yes, since we are talking about a dictionary)
     * - Are there words in uppercase letters? (I assumed that the answer is no)
     */
    static class TrivialDictionary{

        // This class simulates the dictionary in order to test isInDictionary().
        // I assumed that since we are talking about a dictionary, the words are
        // ordered in alphabetical order.
        // I used a simple array with some strings just for testing, but I guess
        // TrivialDictionary is supposed to store the words internally by
        // using a binary tree data structure.

        static int size = 10;
        static String[] dictionary = {
                "apple", "bunny", "carrot", "dolphin", "elephant",
                "flower", "girl", "hello", "island", "june"
        };
        public static String wordAt(int i){
            if(i<0 || i>=size){
                return null;
            }
            return dictionary[i];
        }
    }

    // I assumed that the words in the dictionary are sorted in alphabetical order,
    // so we know for sure that word[i-1] < word[i]. In this case we can perform a
    // binary search O(log n). If the words are not sorted, then a linear search
    // can work O(n).

    static Map<String, Boolean> hashMap = new HashMap<String, Boolean>();
    static boolean isInDictionary(String word){
        // binary search the dictionary
        int start = 0;
        int end = TrivialDictionary.size-1;
        int mid;

        // We can use a hash table to keep track
        // of the previous results of isInDictionary().
        // I think this makes sense only if we assume that no more words are added
        // to the dictionary.
        if(hashMap.containsKey(word)){
            return hashMap.get(word);
        }

        while(start <= end){
            mid = (start + end )/2;
            int cmp = TrivialDictionary.wordAt(mid).compareTo(word);
            if(cmp == 0){       // the word was found
                hashMap.put(word, true);
                return true;
            }else if (cmp < 0){ // search in the right
                start = mid+1;
            }else{              // search in the left
                end = mid-1;
            }
        }
        hashMap.put(word, false);
        return false;
    }
    public static void main(String[] str){
        System.out.println(isInDictionary("hello"));
        System.out.println(isInDictionary("www"));
        System.out.println(isInDictionary("apple"));
        System.out.println(isInDictionary("june"));
    }
}
