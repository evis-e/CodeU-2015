import java.util.Arrays;

public class Solution {

    /* This function sorts the list of ints, and by looping through them, it finds the
     * number which appears most (the appearances are consecutive since it is sorted). Then
     * it checks whether that number is a majority according to the definition.
     *
     * If we were not allowed to modify the list, then we could not sort it. In that case,
     * what we could do would be to loop through the list an store the number of occurrences in
     * a hash table with the integer elements as keys.
     */

    static Integer hasMajority(int[] list){
        int size = list.length;

        if(size == 0) return null;
        if(size == 1) return list[0];

        Arrays.sort(list);

        int maxCnt = -1;
        int cnt = 1;
        int maj = list[0];
        for (int i=1; i<size; i++){
            if(list[i] == list[i-1]){
                cnt++;
            }else{
                cnt = 1;
            }
            // This if block is executed sometimes unnecessary. Actually this check should be done every time
            // a different number in the list is encountered, so we can move this inside the else block.
            // In that case, we have to perform this check one more time after the for loop for cases
            // when the majority is the larges number in the list (ex: list6).
            // I chose to write it here as it makes the code shorter and I think it does not affect
            // the performance that much.
            if(maxCnt<cnt){
                maxCnt = cnt;
                maj = list[i-1];
            }
        }
        return (maxCnt*2 > size) ? maj : null;
    }
    public static void main( String[] str){
        int[] list1 = {1, 1, 2, 3, 3, 3, 3, 3, 3, 4, 5}; //expected: 3
        int[] list2 = {1, 1}; //expected: 1
        int[] list3 = {1, 5}; //expected: null
        int[] list4 = {1}; //expected: 1
        int[] list5 = {-1, -1, 2, 3}; //expected: null
        int[] list6 = {1, 2, 2, 2}; //expected: 2
        int[] list7 = {4, 7, 2, 4, 2, 4, 4, 8, 4}; //expected: 4

        System.out.println(hasMajority(list1));
        System.out.println(hasMajority(list2));
        System.out.println(hasMajority(list3));
        System.out.println(hasMajority(list4));
        System.out.println(hasMajority(list5));
        System.out.println(hasMajority(list6));
        System.out.println(hasMajority(list7));
    }
}
