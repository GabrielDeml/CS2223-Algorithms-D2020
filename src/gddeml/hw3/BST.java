package gddeml.hw3;

import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

/**
 * Minimum implementation of Binary Search Tree (BST) as a Symbol Table<String, Integer>
 * <p>
 * You need to copy this class into your USERID.hw3 and add methods to the end of this class.
 */
public class BST {

    Node root;               // root of the tree

    class Node {
        String key;
        Integer count;
        Node left, right;  // left and right subtrees
        int N;            // number of nodes in subtree

        public Node(String key, int ct, int N) {
            this.key = key;
            this.count = ct;
            this.N = N;
        }

        public String toString() {
            return "[" + key + "]";
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Return number of key-value pairs in ST.
     */
    public int size() {
        return size(root);
    }

    // Helper method that deals with "empty nodes"
    private int size(Node node) {
        if (node == null) return 0;

        return node.N;
    }

    /**
     * Search for key in BST.
     */
    public Integer get(String key) {
        return get(root, key);
    }

    /**
     * Helper method to search for key in BST rooted at parent.
     */
    private Integer get(Node parent, String key) {
        if (parent == null) return null;

        int cmp = key.compareTo(parent.key);

        if (cmp < 0) return get(parent.left, key);
        else if (cmp > 0) return get(parent.right, key);
        else return parent.count;
    }

    /**
     * Invoke put on parent, should it exist.
     */
    public void put(String key, Integer val) {
        root = put(root, key, val);
    }

    /**
     * Helper method to put (key, ct) pair into BST rooted at parent.
     */
    private Node put(Node parent, String key, Integer ct) {
        if (parent == null) return new Node(key, ct, 1);

        int cmp = key.compareTo(parent.key);
        if (cmp < 0) parent.left = put(parent.left, key, ct);
        else if (cmp > 0) parent.right = put(parent.right, key, ct);
        else parent.count = ct;

        parent.N = 1 + size(parent.left) + size(parent.right);
        return parent;
    }

    // traversal ideas
    // invoke an inorder traversal of the tree
    public void inorder() {
        inorder(root);
    }

    private void inorder(Node n) {
        if (n == null) {
            return;
        }

        inorder(n.left);
        StdOut.println(n.key);
        inorder(n.right);
    }

    /**
     * Removes the specified key and its associated value from this symbol table
     * (if the key is in this symbol table).
     *
     * @param key the key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void delete(String key) {
        if (key == null) throw new IllegalArgumentException("calls delete() with a null key");
        root = delete(root, key);
    }

    /**
     * Taken from Sedgewick algo.
     */
    private Node delete(Node x, String key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        return x;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        else return min(x.left);
    }

    /**
     * Removes the smallest key and associated value from the symbol table.
     *
     * @throws NoSuchElementException if the symbol table is empty
     */
    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        return x;
    }

    // ------------------------------------------------------------------------------------------------
    // YOU WILL ADD METHODS BELOW. THERE IS NO NEED TO MODIFY CODE ABOVE.
    // ------------------------------------------------------------------------------------------------

    /**
     * Returns the count of nodes at a given depth. Key is depth, Value is count.
     * <p>
     * For the following tree
     * <p>
     * G           -- depth is 0     { (0, 1) }
     * / \
     * D   H         -- depth is 1
     * \
     * F           -- depth is 2
     * <p>
     * The returned hash table should be { (0, 1), (1, 2), (2, 1) } where each (key, value) is (depth, count).
     * <p>
     * Note: you will need a helper method, much like you have seen in other recursive methods.
     * <p>
     * Returns a edu.princeton.cs.algs4.SeparateChainingHashST object representing the symbol table.
     */
    public SeparateChainingHashST<Integer, Integer> collect() {

//        Don't listen to the dude under me I actually return something. So take that...
        return iAmTheHelperButIDoAllOfTheWork(root, 0, new SeparateChainingHashST<>());
    }

    /**
     * Yeah you saw my function name I do all the recursion work the one above me is lazy and not worth anything
     *
     * @param node   root node
     * @param height We made our own because this node doesn't have them like the AVL trees. I am so jelly
     */
    private SeparateChainingHashST<Integer, Integer> iAmTheHelperButIDoAllOfTheWork(Node node, int height, SeparateChainingHashST<Integer, Integer> returnHash) {
//    	Hey TAs whats up?
//		It is so unfair me being a helper function has to do all of the work...
//		But to be fair... I have a proper java doc so people can see what I do :)
        height++;
        if (node.right != null) {
            returnHash = iAmTheHelperButIDoAllOfTheWork(node.right, height, returnHash);
        }
        if (node.left != null) {
            returnHash = iAmTheHelperButIDoAllOfTheWork(node.left, height, returnHash);
        }

        if (!returnHash.contains(height)) {
            returnHash.put(height, 1);
        } else {
            returnHash.put(height, returnHash.get(height) + 1);
        }
        return returnHash;
    }

    /**
     * Returns the height of this binary tree.
     */
    public int height() {
        return height(root) - 1;
    }


    /**
     * Returns the height of a given node.
     * <p>
     * For the following tree
     * <p>
     * G           -- height of G is 2
     * / \
     * D   H         -- height of D is 1, height of H is 0
     * \
     * F           -- height of F is 0
     */
    public int height(Node n) {
        if (n == null) {
            return 0;
        }
        return 1 + Math.max(height(n.left), height(n.right));
    }

    /**
     * Return the key whose count is the greatest (that is, has the most occurrences in the BST).
     */
//    private SeparateChainingHashST<Integer, Integer> returnHash2 = new SeparateChainingHashST<>();

    public String mostFrequent() {

        return iAmTheOtherHelperButIDoAllOfTheOtherWork(root, "");
    }

    private String iAmTheOtherHelperButIDoAllOfTheOtherWork(Node node, String keyStart) {
        String key = keyStart;
        if (node.left != null) {
            key = iAmTheOtherHelperButIDoAllOfTheOtherWork(node.left, key);
        }
        if (node.right != null) {
            key = iAmTheOtherHelperButIDoAllOfTheOtherWork(node.right, key);
        }
        if ((get(key) != null || !key.equals("")) && (get(key) != null && get(key) > get(node.key))) {
            return key;
        }
        return node.key;
    }

    /**
     * Print in ascending order the keys whose count is 1 (that is, only occur once) and return total.
     */
    public int printUnique() {
        return hiIAmProperRecursionNotTheFakeStuffAboveMe(root, 0);
    }

    private int hiIAmProperRecursionNotTheFakeStuffAboveMe(Node node, int count) {
        if (node.left != null) {
            count = hiIAmProperRecursionNotTheFakeStuffAboveMe(node.left, count);
        }
        if (node.count == 1) {
            System.out.println(node.key);
            count++;
        }
        if (node.right != null) {
            count = hiIAmProperRecursionNotTheFakeStuffAboveMe(node.right, count);
        }
        return count;
    }

}
