import java.util.Arrays;

public class Palindrome {
    /*
     * I tried to solve this problem by writing a recursive function
     * longestPalindrome(str, s, e) which returns the length of the longest
     * palindromic subsequence from s to e in str. This function uses prevCalc
     * to store the previously calculated subproblems.
     * The time complexity of this dynamic programming approach is
     * O(n^2) where n is the length of the string. It uses an extra memory
     * of O(n^2), which is the matrix prevCalc.
     * If we were not allowed to use extra space, then we would have to omit using
     * prevCalc. In that case, a simple algorithm would be to check all the substrings of
     * the string if they are palindromes. This would be a O(n^3) algorithm.
     */
    static int[][] prevCalc;
    // This function returns the length of the longest
    // palindromic subsequence in str with starting index s and
    // ending index e.
    static int longestPalindrome(String str, int s, int e){
        if(s == e){
            return 1;
        }
        if(s > e) {
            return 0;
        }
        // prevCalc stores the previously calculated subproblems
        if(prevCalc[s][e] != -1){
            return prevCalc[s][e];
        }
        int a=0, b, c;
        if(str.charAt(s) == str.charAt(e)){
            a = longestPalindrome(str, s+1, e-1);
            if(a == e-s-1){
                a+=2;
            }
        }
        b = longestPalindrome(str, s+1, e);
        c = longestPalindrome(str, s, e-1);
        prevCalc[s][e] = Math.max(Math.max(a, b), c);
        return prevCalc[s][e];
    }
    public static void main(String[] args){
        String s = "12111122221212121";
        prevCalc = new int[s.length()+1][s.length()+1];
        for(int i=0; i<prevCalc.length; i++){
            Arrays.fill(prevCalc[i], -1);
        }
        System.out.println(longestPalindrome(s, 0, s.length() - 1));
        s = "aba";
        prevCalc = new int[s.length()+1][s.length()+1];
        for(int i=0; i<prevCalc.length; i++){
            Arrays.fill(prevCalc[i], -1);
        }
        System.out.println(longestPalindrome(s, 0, s.length() - 1));
        s = "abaa";
        prevCalc = new int[s.length()+1][s.length()+1];
        for(int i=0; i<prevCalc.length; i++){
            Arrays.fill(prevCalc[i], -1);
        }
        System.out.println(longestPalindrome(s, 0, s.length() - 1));
        s = "abaaaa";
        prevCalc = new int[s.length()+1][s.length()+1];
        for(int i=0; i<prevCalc.length; i++){
            Arrays.fill(prevCalc[i], -1);
        }
        System.out.println(longestPalindrome(s, 0, s.length() - 1));
        s = "xyzqwe";
        prevCalc = new int[s.length()+1][s.length()+1];
        for(int i=0; i<prevCalc.length; i++){
            Arrays.fill(prevCalc[i], -1);
        }
        System.out.println(longestPalindrome(s, 0, s.length() - 1));
        s = "t";
        prevCalc = new int[s.length()+1][s.length()+1];
        for(int i=0; i<prevCalc.length; i++){
            Arrays.fill(prevCalc[i], -1);
        }
        System.out.println(longestPalindrome(s, 0, s.length() - 1));
        s = "";
        prevCalc = new int[s.length()+1][s.length()+1];
        for(int i=0; i<prevCalc.length; i++){
            Arrays.fill(prevCalc[i], -1);
        }
        System.out.println(longestPalindrome(s, 0, s.length() - 1));
    }
}