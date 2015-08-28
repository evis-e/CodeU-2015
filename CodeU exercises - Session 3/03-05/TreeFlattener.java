import java.util.*;

public class TreeFlattener {

    public static class BinaryTreeNode{
        private BinaryTreeNode leftChild;
        private BinaryTreeNode rightChild;
        private String value;

        public BinaryTreeNode(String value){
            this.value = value;
            this.leftChild = null;
            this.rightChild = null;
        }
    }

    public interface BinaryTreeIterator{
        public abstract boolean hasNext();
        public abstract String next();
    }

    public static class BinaryTree{
        private BinaryTreeNode root;

        public BinaryTree(){
            root = null;
        }
        public BinaryTree(String str){
            root = new BinaryTreeNode(str);
        }

        // Last time I implemented the pre order iteration by using recursion which is more simple,
        // but since we are asked to make an iterator interface, I thought of using a stack to keep
        // track of the current node and the correct order they should be processed.
        // For post order and in order I needed to keep track whether a node was visited before or not
        // in order to prevent infinite loops.

        // In the question it says "Create an abstract class BinaryTreeIterator", but I got a strange error while
        // trying to do so, that's why I created an interface instead. We could even use Iterator class instead.

        class IteratorPreOrder implements BinaryTreeIterator{
            private Stack<BinaryTreeNode> stackPreOrder;
            IteratorPreOrder(){
                stackPreOrder = new Stack<BinaryTreeNode>();
                if(root != null){
                    stackPreOrder.add(root);
                }
            }
            @Override
            public boolean hasNext() {
                return stackPreOrder.size() != 0;
            }
            @Override
            public String next() {
                if(stackPreOrder.size() == 0){
                    return null;
                }
                BinaryTreeNode curr = stackPreOrder.pop();
                if(curr.rightChild != null){
                    stackPreOrder.add(curr.rightChild);
                }if(curr.leftChild != null){
                    stackPreOrder.add(curr.leftChild);
                }
                return curr.value;
            }
        }
        public BinaryTreeIterator getIteratorPreOrder(){
            return new IteratorPreOrder();
        }

        class IteratorPostOrder implements BinaryTreeIterator{

            private Stack<BinaryTreeNode> stackPostOrder;
            private HashMap<String, Boolean> postOrderIsVisited;

            IteratorPostOrder(){
                stackPostOrder = new Stack<BinaryTreeNode>();
                postOrderIsVisited = new HashMap<String, Boolean>();
                if(root != null) {
                    stackPostOrder.add(root);
                    postOrderIsVisited.put(root.value, false);
                }
            }

            @Override
            public boolean hasNext() {
                return stackPostOrder.size() != 0;
            }

            @Override
            public String next() {
                if(stackPostOrder.size() == 0){
                    return null;
                }
                while(stackPostOrder.size() != 0){
                    BinaryTreeNode curr = stackPostOrder.peek();
                    if(postOrderIsVisited.get(curr.value)){
                        return stackPostOrder.pop().value;
                    }
                    if(curr.rightChild != null){
                        stackPostOrder.add(curr.rightChild);
                        postOrderIsVisited.put(curr.rightChild.value, false);
                    }if(curr.leftChild != null){
                        stackPostOrder.add(curr.leftChild);
                        postOrderIsVisited.put(curr.leftChild.value, false);
                    }
//                    if(curr.leftChild == null && curr.rightChild == null){
//                        return stackPostOrder.pop().value;
//                    }
                    postOrderIsVisited.put(curr.value, true);
                }
                return null;
            }
        }
        public BinaryTreeIterator getIteratorPostOrder(){
            return new IteratorPostOrder();
        }

        class IteratorInOrder implements BinaryTreeIterator {

            private Stack<BinaryTreeNode> stackInOrder;
            private HashMap<String, Boolean> inOrderIsVisited;

            IteratorInOrder(){
                stackInOrder = new Stack<BinaryTreeNode>();
                inOrderIsVisited = new HashMap<String, Boolean>();
                if(root != null) {
                    stackInOrder.add(root);
                    inOrderIsVisited.put(root.value, false);
                }
            }

            @Override
            public boolean hasNext() {
                return stackInOrder.size() != 0;
            }

            @Override
            public String next() {
                if (stackInOrder.size() == 0) {
                    return null;
                }
                while (stackInOrder.size() != 0) {
                    BinaryTreeNode curr = stackInOrder.pop();
                    if (inOrderIsVisited.get(curr.value)) {
                        return curr.value;
                    }
                    if (curr.rightChild != null) {
                        stackInOrder.add(curr.rightChild);
                        inOrderIsVisited.put(curr.rightChild.value, false);
                    }
                    stackInOrder.add(curr);
                    if (curr.leftChild != null) {
                        stackInOrder.add(curr.leftChild);
                        inOrderIsVisited.put(curr.leftChild.value, false);
                    }
                    inOrderIsVisited.put(curr.value, true);
                }
                return null;
            }
        }
        public BinaryTreeIterator getIteratorInOrder(){
            return new IteratorInOrder();
        }
    }

    public static void main(String[] args){
        BinaryTree binaryTree;
        List<String> list;

        System.out.print("\nTest Case 1: Complete tree\n");
        GenerateTree.init(3, 10);                              // a complete tree
        binaryTree = GenerateTree.generateTree();

        System.out.print("\nPreOrder traversal\n");
        BinaryTreeIterator pre;
        pre = binaryTree.getIteratorPreOrder();
        while(pre.hasNext()){
            System.out.println(pre.next());
        }

        System.out.print("\nPostOrder traversal\n");
        BinaryTreeIterator post;
        post = binaryTree.getIteratorPostOrder();
        while(post.hasNext()){
            System.out.println(post.next());
        }

        System.out.print("\nInOrder traversal\n");
        BinaryTreeIterator in;
        in = binaryTree.getIteratorInOrder();
        while(in.hasNext()){
            System.out.println(in.next());
        }

        System.out.print("\nTest Case 2: \n");
        GenerateTree.init(3, 5);
        binaryTree = GenerateTree.generateTree();

        BinaryTreeIterator iterator = binaryTree.getIteratorPostOrder();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        System.out.print("\nTest Case 3: \n");
        GenerateTree.init(3, 0);      // tree consisting of just the root
        binaryTree = GenerateTree.generateTree();

        iterator = binaryTree.getIteratorPostOrder();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        System.out.print("\nTest Case 4: \n");
        GenerateTree.init(2, 5);
        binaryTree = GenerateTree.generateTree();

        iterator = binaryTree.getIteratorPostOrder();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        System.out.print("\nTest Case 5: \n");
        GenerateTree.init(0, 4);      // binaryTree = null
        binaryTree = GenerateTree.generateTree();

        iterator = binaryTree.getIteratorPostOrder();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        System.out.print("\nTest Case 6: \n");
        GenerateTree.init(10, 5);
        binaryTree = GenerateTree.generateTree();

        iterator = binaryTree.getIteratorPostOrder();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
    public static class GenerateTree{
        // This class generates a random binary tree limited
        // by maxHeight and p. maxHeight is a threshold for the height the
        // tree can reach. A maxHeight = 0 would generate an empty tree.
        // 0 <= p <= 10, which describes the probability for an edge
        // to exist. p = 0 would generate a binary tree with only the root, while
        // p = 10 would generate a complete tree.
        // The values of the nodes of the tree are put in such a way that when the tree
        // is flattened, the numbers are a sequence from 1 to N in order to make it easier to test.

        static int cnt;
        private static int maxHeight, p;
        public static void init(int height, int P){
            maxHeight = height;
            p = P;
        }
        private static BinaryTree generateTree() {
            if(maxHeight == 0) return new BinaryTree();
            cnt = 1;
            BinaryTree binaryTree = new BinaryTree(cnt+"");
            fillTree(binaryTree.root, 1);
            return binaryTree;
        }

        private static void fillTree(BinaryTreeNode node, int currHeight) {
            if(currHeight >= maxHeight) return;

            Random random = new Random();
            int r;

            r = random.nextInt(10);   // generates a random number form 0 to 9

            if(r<p){
                cnt++;
                node.leftChild = new BinaryTreeNode(cnt + "");
                fillTree(node.leftChild, currHeight+1);
            }
            r = random.nextInt(10);   // generates a random number form 0 to 9
            if(r<p){
                cnt++;
                node.rightChild = new BinaryTreeNode(cnt + "");
                fillTree(node.rightChild, currHeight+1);
            }
        }
    }
}
