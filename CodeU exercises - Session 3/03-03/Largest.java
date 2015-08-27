import java.util.*;

public class Largest {
    /*
     * I implemented nthLargest by sorting the list and finding the nth largest, and
     * by using a priority queue. Using a queue makes the finding of the nth element
     * more time efficient, but I think that if the function is required to be called more
     * than once for the same list, then sorting the list once in the beginning and
     * accessing the elements by index is more efficient in the long run.
     *
     * Through my research I found out some more efficient methods like quick select,
     * and median of medians algorithm that work in linear time.
     */
    ArrayList<Integer> list;
    public Largest(ArrayList<Integer> l){
        list = l;
    }
    int nthLargest(int n) throws Exception {
        if(n<=0 || n>list.size()){
            throw new Exception("Parameter not valid");
        }

        // The list is sorted in descending order: O(c log c) where c is the
        // size of the list.
        // The sorting should be performed in the constructor because
        // nthLargest can be called more than one time for the same
        // list, but I wrote it here to compare the efficiency with the other
        // function.

        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        return list.get(n-1);
    }
    int nthLargestWithQueue(int n) throws Exception {
        // This method finds nth largest number without sorting. A priority queue
        // is created from the elements of the list in O(n log c) where c is the size of the
        // list and then finds the nth largest number.

        if(n<=0 || n>list.size()){
            throw new Exception("Parameter not valid");
        }
        Queue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        queue.addAll(list);
        for(int i=1; i<n; i++){
            queue.remove();
        }
        return queue.element();
    }
    public static void main(String[] str){
        ArrayList<Integer> list1 = new ArrayList<Integer>(Arrays.asList(3, 4, 2, 1));
        ArrayList<Integer> list2 = new ArrayList<Integer>();
        int n = 1000000;
        Random r = new Random();
        for(int i=0; i<n; i++){
            list2.add(r.nextInt() % 100000);
        }

        Largest largest = new Largest(list2);
        long start_time, end_time;

        try {
            start_time = System.nanoTime();
            System.out.println(largest.nthLargest(n/2));
            end_time = System.nanoTime();
            System.out.println((end_time - start_time) + " nsec");

            start_time = System.nanoTime();
            System.out.println(largest.nthLargestWithQueue(n / 2));
            end_time = System.nanoTime();
            System.out.println((end_time - start_time) + " nsec");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
