public class Segmentation {
    static class TrivialDictionary{
        // This class simulates the dictionary in order to test didYouMean()
        static String[] dictionary = {
                "apple", "pie", "morning", "hello", "good"
        };
        // Returns true if the word is in the dictionary
        public static boolean isInDictionary(String word){
            if(word.length() == 0){
                return true;
            }
            // A simple linear search, just for the sake of testing didYouMean().
            for(int i=0; i<dictionary.length; i++){
                if(dictionary[i].equals(word)){
                    return true;
                }
            }
            return false;
        }
    }
    // This function returns the input string with spaces between each words, given that the input is composed of
    // at most two words
    static String didYouMean2(String input){
        // There may be a case when the input is a valid word. The function may return the same word (because
        // that is a valid one) or an empty string (because there is nothing to suggest as "did you mean").
        // This depends on what kind of check will be done after this function is called.
        if(TrivialDictionary.isInDictionary(input)){
            return input;
        }
        int length = input.length();
        for(int i=1; i<length; i++){
            if(TrivialDictionary.isInDictionary(input.substring(0, i))
                    && TrivialDictionary.isInDictionary(input.substring(i, length))){
                return input.substring(0, i) + " " + input.substring(i, length);
            }
        }
        return "";
    }
    // This function returns the input string with spaces between each words, given that the input is composed of
    // an indefinite number of words
    static String didYouMean(String input){
        if(TrivialDictionary.isInDictionary(input)){
            return input;
        }
        int length = input.length();
        for (int i = 1; i < length; i++) {
            if(TrivialDictionary.isInDictionary(input.substring(0, i))){
                String str = didYouMean(input.substring(i, length));
                if(str.length() != 0){
                    return input.substring(0, i) + " " + str;
                }
            }
        }
        return "";
    }
    public static void main(String[] args){
        System.out.println("Did you mean "+ didYouMean2("applepie"));
        System.out.println("Did you mean "+ didYouMean2("hello"));
        System.out.println("Did you mean "+ didYouMean("helloapplepie"));
        System.out.println("Did you mean "+ didYouMean("goodmorning"));
        System.out.println("Did you mean "+ didYouMean("hello"));
        System.out.println("Did you mean "+ didYouMean(""));
        System.out.println("Did you mean "+ didYouMean("world"));
        System.out.println("Did you mean "+ didYouMean("appleworld"));
    }
}