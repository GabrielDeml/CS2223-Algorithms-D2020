package gddeml.hw2;

/**
 * Once you copy this file into your USERID.hw2 package, you must complete this implementation.
 * <p>
 * This class will be used by Question Q1 on Homework2.
 */
public class WordList {
    //    Make a start node
    Node root = new Node(null);
    //    Init listSize
    private int listSize = 0;

    /**
     * Leave this Node class as is. While you don't need to make changes to this class,
     * it is acceptable if you would like to add methods to this class.
     */
    class Node {
        String word;
        Node next;

        Node(String w) {
            this.word = w;
        }

        /**
         * Set the next node
         *
         * @param next the next node
         */
        public void setNext(Node next) {
            this.next = next;
        }

        /**
         * Get the next node
         *
         * @return the next node
         */
        public Node getNext() {
            return next;
        }

        /**
         * Get the nodes word
         *
         * @return The words node
         */
        public String getWord() {
            return this.word;
        }
    }

    /**
     * If the given element doesn't exist in the set then update
     * the set and return true; otherwise return false. This means that
     * adding a duplicate element to a set must return false.
     *
     * @param elt element to be added.
     */
    public boolean add(String elt) {
        Node selectedNode = root;
        Node nextNode;
        String nodeWord;
        while (true) {
            nextNode = selectedNode.getNext();
            nodeWord = selectedNode.getWord();
            if (nodeWord != null && elt.equals(nodeWord)) {
                return false;
            } else if (nextNode == null) {
                selectedNode.setNext(new Node(elt));
                listSize++;
                return true;
            }
//            Set the selected node
            selectedNode = nextNode;
        }
    }

    /**
     * Returns the number of elements in the set.
     */
    public int size() {
        return listSize;
    }

    /**
     * Returns true if the given element was in the set (and was removed) or
     * false if the given element did not belong to the set.
     *
     * @param elt element to be removed.
     */

    public boolean remove(String elt) {
//        Set root as the selected
        Node selectedNode = root;
//        Set next node as next node
        Node nextNode;
//        Init previous node
        Node previousNode = selectedNode;
//        While while true
        String nodeWord;
        while (true) {
//            Get the next node
            nextNode = selectedNode.getNext();
            nodeWord = selectedNode.getWord();
//            If this node contains the word
            if (nodeWord != null && nodeWord.equals(elt)) {
//                Set the next node to the previous
                previousNode.setNext(nextNode);
//                Set the list size
                this.listSize--;
                return true;
//                If we made it so next node is null we must have mode it to the end
            } else if (nextNode == null) {
                return false;
            }
//            Save the previous node
            previousNode = selectedNode;
//            Set the selected node
            selectedNode = nextNode;
        }
    }

    /**
     * Returns true if the element exists within the collection.
     *
     * @param elt target element to be searched.
     */
    public boolean contains(String elt) {
        //        Set root as the selected
        Node selectedNode = root;
//        Set next node as next node
        Node nextNode;
//        While while true
        String nodeWord;
        while (true) {
//            Get the next node
            nextNode = selectedNode.getNext();
            nodeWord = selectedNode.getWord();
//            If this node contains the word
            if (nodeWord != null && nodeWord.equals(elt)) {
//                Set the next node to the previous
                return true;
            } else if (nextNode == null) {
                return false;
            }
//            Set the selected node
            selectedNode = nextNode;
        }
    }

    /**
     * For debugging, return comma-separated string of elements.
     */
    public String elements() {
//        Set root as the selected
        Node selectedNode = root;
//        Set next node as next node
        Node nextNode;
        String selectedNodeWord;
//        Init string builder
        StringBuilder returnString = new StringBuilder("");
        String prefix = "";
//        While while true
        while (true) {
//            Get the next node
            nextNode = selectedNode.getNext();
            selectedNodeWord = selectedNode.getWord();
            if (selectedNodeWord != null) {
//                Append word
                returnString.append(prefix);
                prefix = ",";
                returnString.append(selectedNodeWord);
            }
//            If we are at the end
            if (nextNode == null) {
                System.out.println(returnString.toString());
                return returnString.toString();
            }
//            Set the selected node
            selectedNode = nextNode;
        }
    }

    // you should not have to modify anything below. These are testing routines for you to check your work.
    // ----------------------------------------------------------------------------------------------------
    static void validate(Object o1, Object o2) {
        if (o1.equals(o2)) {
            return;
        }
        throw new RuntimeException(o1 + " doesn't equal " + o2);
    }

    // Once you have completed the implementation, you should be able to run this method and have
    // it complete without any runtime exceptions. While not an exhaustive test, this should be
    // sufficient to help you uncover many of the boundary cases.
    public static void main(String[] args) {

        WordList wl = new WordList();
        validate(0, wl.size());
        validate("", wl.elements());           // empty word list must return ""
        validate(false, wl.contains("this"));
        validate(true, wl.add("test"));
        validate("test", wl.elements());       // no trailing or pre comma.
        validate(false, wl.contains("this"));
        validate(true, wl.contains("test"));

        validate(false, wl.add("test"));       // can't add twice
        validate(true, wl.remove("test"));     // can remove first element
        validate(false, wl.remove("test"));    // can't remove first empty
        validate(true, wl.add("test"));
        validate(true, wl.add("that"));
        validate(false, wl.remove("not"));
        validate(true, wl.remove("test"));
        validate("that", wl.elements());       // no trailing or pre comma.
        validate(true, wl.remove("that"));

        WordList wlMine = new WordList();
        wlMine.add("hello");
        wlMine.add("hello");
        validate("hello", wlMine.elements());
        validate(1, wlMine.size());
    }
}
