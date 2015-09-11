import java.util.Arrays;

public class ReturnChange {
    static int[][] prevCalc;

    /*
     * This function returns the number of ways to change a value of n given infinite
     * amount of each of the denominators d[] (I assumed all the denominators are unique).
     * It explores all the possible solutions by
     * using recursion, and stored the previously calculated subproblems in the matrix
     * prevCalc to avoid recalculating the same thing over and over. By using this
     * dynamic programming approach, the time complexity of the algorithm is
     * O(n*L) where n is the value we want to change and L is the number of different
     * denominators. Extra memory used is also n*L.
     *
     * If the problem was required to be solved in linear memory, then I think
     * I would go with just using the recursion without storing the precalculated
     * subproblems in prevCalc. This approach would be very expensive in time though.
     *
     */

    static int waysToChange(int n, int[] d, int p){
        // n is the value we want to change, d[] are the denominators and
        // p is the index in d[] where we are going to start changing
        
        if(n < 0){  // there is no way to change a value < 0
            return 0;
        }
        if(n == 0){  // there is 1 way to change a value == 0, which is by giving 0 change
            return 1;
        }
        if(prevCalc[n][p] != -1){  // check if it was previously calculated
            return prevCalc[n][p];
        }
        int res = 0;
        // Try all the possible combinations.
        for(int i = p; i < d.length; i++){
            res += waysToChange(n - d[i], d, i);
        }
        prevCalc[n][p] = res;
        return res;
    }
    public static void main(String[] args){
        // Test 1:
        int[] d = new int[]{2, 3, 5};
        int value = 10;
        prevCalc = new int[value+1][value+1];
        for(int i=0; i<prevCalc.length; i++){
            Arrays.fill(prevCalc[i], -1);
        }
        System.out.println(waysToChange(value, d, 0));

        // Test 2: expected 0, because no denominators available.
        d = new int[]{};
        value = 10;
        prevCalc = new int[value+1][value+1];
        for(int i=0; i<prevCalc.length; i++){
            Arrays.fill(prevCalc[i], -1);
        }
        System.out.println(waysToChange(value, d, 0));

        // Test 3: expected 1, because a value of 0 can be changed in only 1 way: by giving 0 of each denominator.
        d = new int[]{3, 5, 6};
        value = 0;
        prevCalc = new int[value+1][value+1];
        for(int i=0; i<prevCalc.length; i++){
            Arrays.fill(prevCalc[i], -1);
        }
        System.out.println(waysToChange(value, d, 0));
    }
}
