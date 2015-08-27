import java.util.HashMap;

class CollatzGraph {

    HashMap<Integer, Integer> indexToLength = new HashMap<Integer, Integer>();

    int loopCount(int x) {
        // if we have previously calculated it, just return the value
        if(indexToLength.containsKey(x)){
            return indexToLength.get(x);
        }
        // calculate and store the lengths of all the nodes in the sequence
        int nextX;
        if (x % 2 == 0) nextX = x/2;
        else nextX = 3 * x + 1;

        int l = 1 + loopCount(nextX);
        indexToLength.put(x, l);
        return l;
    }

    void initialize() {
        indexToLength.put(1, 1);
    }

    int maxLoop(int x, int y) {
        int currMax = -1;
        for(; x<y; x++)
            currMax = Math.max(currMax, loopCount(x));
        return currMax;

    }

    public static void main(String[] args) {
        CollatzGraph graph = new CollatzGraph();
        graph.initialize();

        System.out.println("Length: "+graph.loopCount(64));
        System.out.println("Length: "+graph.loopCount(5));
        System.out.println("Length: "+graph.loopCount(13));
    }
}
