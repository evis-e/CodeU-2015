import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

class CollatzGraph {

    static HashMap<Integer, Integer> indexToLength = new HashMap<Integer, Integer>();

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

    static void writeToFile(String filename) throws Exception{
        File file = new File(filename);
        PrintWriter output = new PrintWriter(file);

        Iterator it = indexToLength.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            output.print(pair.getKey() + " " + pair.getValue()+"\n");
        }
        output.close();
    }

    static void readFile(String filename) throws Exception{
        File file = new File(filename);
        Scanner input = new Scanner(file);

        while(input.hasNext()){
            String key = input.next();
            String value = input.next();

            System.out.println(key + ": " +value);
        }
        input.close();
    }

    public static void main(String[] args) {
        CollatzGraph graph = new CollatzGraph();
        graph.initialize();

        System.out.println("Length: "+graph.loopCount(64));
        System.out.println("Length: "+graph.loopCount(5));
        System.out.println("Length: "+graph.loopCount(13));

        String fileName = "collatz.txt";
        try {
            writeToFile(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            readFile(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
