public class PalindromeBits {
    /*
     * Zero in my opinion would be considered a palindrome, but since the
     * question says positive integers that should be ignored.
     */

    // This function finds the kth Palindrome by using either
    // isPalindromeBit1 (for the case 1A)
    // or isPalindromeBit2 (for the case 1B)
    static int kthPalindromeBit(int k){
        if(k <= 0){  // not a valid k value
            return -1;   // instead of returning -1, this could throw an exception
        }
        int cnt = 0;
        int i = 1;
        for(; cnt<k; i++){
            if(isPalindromeBit2(i)){
                cnt ++;
            }
        }
        return i-1;
    }
    // returns true if n is a palindrome in the binary representation
    // by ignoring the leading zeros
    static Boolean isPalindromeBit1(int n){
        // Even numbers end with zero in their binary representation.
        // Since we are ignoring the leading zeros, we know for sure that
        // the number starts with 1, which implies that an even number can not
        // be a palindrome.
        if(n % 2 == 0){
            return false;
        }
        // Convert the integer into the binary form
        String bin = "";
        while(n >= 1){
            bin += n % 2;
            n /= 2;
        }
        // Check if the binary string is a palindrome
        for(int i=0; i<bin.length()/2; i++){
            if(bin.charAt(i) != bin.charAt(bin.length()-i-1)){
                return false;
            }
        }
        return true;
    }
    // returns true if n is a palindrome in the binary representation
    // by adding as many leading zeros as we want.
    static Boolean isPalindromeBit2(int n){
        // Convert the integer into the binary form
        String bin = "";
        while(n >= 1){
            bin = n % 2 + bin;
            n /= 2;
        }
        // Find the index of the beginning of the zero sequence that continues until
        // the end of the string (for example, it the string is 1011000, the index will be 4).
        // The part after the index will be ignored, since we can add as many leading zeros
        // as we want to compensate for that.
        int index = bin.length();
        for(int i= bin.length()-1; i>=0; i--){
            if(bin.charAt(i) != '0'){
                break;
            }index--;
        }
        // Check if the binary string (the ending zeros ignored) is a palindrome
        for(int i=0; i<index/2; i++){
            if(bin.charAt(i) != bin.charAt(index-i-1)){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args){
        System.out.println(kthPalindromeBit(1));
        System.out.println(kthPalindromeBit(2));
        System.out.println(kthPalindromeBit(3));
        System.out.println(kthPalindromeBit(100));
    }
}
