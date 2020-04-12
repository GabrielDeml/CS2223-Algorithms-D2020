package gddeml.hw2;

/**
 * Once you copy this file into your USERID.hw2 package, you must complete this implementation.
 * <p>
 * This class will be used by Question Q2 on Homework2.
 */
public class WordSymbolTable {
    Node root = new Node(null, -1);

    /**
     * Leave this Node class as is. While you don't need to make changes to this class,
     * it is acceptable if you would like to add methods to this class.
     */
    class Node {
        String word;
        int count;
        Node next;

        Node(String w, int count) {
            this.word = w;
            this.count = count;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;

        }

        public String getWord() {
            return this.word;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getCount() {
            return this.count;
        }

    }

    /**
     * Increase the count for given word.
     * <p>
     * Note: this might need to add the word in the first place.
     * <p>
     * Returns TRUE if the word was newly added, otherwise FALSE
     *
     * @param elt element whose count has increased by 1.
     */
    public boolean increment(String elt) {
        Node pointer = root;
        Node pointerNext = pointer;
        while (pointerNext != null) {
            pointer = pointerNext;
            if (pointer.getWord() == elt) {
                pointer.setCount(pointer.getCount() + 1);
                return false;
            }
            pointerNext = pointer.getNext();
        }
        pointer.setNext(new Node(elt, 1));
        return true;
    }

    /**
     * Decrease the count for given word.
     * <p>
     * Note: this might need to remove the word once the count reaches zero.
     * Returns TRUE if the word was removed, otherwise FALSE
     *
     * @param elt element whose count is to decrease by 1.
     */
    public boolean decrement(String elt) {
        Node pointer = root.getNext();
        while (pointer != null) {
            if (pointer.getWord() == elt) {
                pointer.setCount(pointer.getCount() - 1);
                return true;
            }
            pointer = pointer.getNext();
        }
        return false;
    }

    /**
     * Return number of words in the symbol table.
     */
    public int size() {
        Node pointer = root.getNext();
        int nodeCount = 0;
        while (pointer != null) {
            nodeCount++;
            pointer = pointer.getNext();
        }
        return nodeCount;
    }

    /**
     * Return the accumulated counts of all words in the word table.
     */
    public int totalCounts() {
        Node pointer = root.getNext();
        int nodeCount = 0;
        while (pointer != null) {
            nodeCount = nodeCount + pointer.getCount();
            pointer = pointer.getNext();
        }
        return nodeCount;
    }

    /**
     * Remove entire word from the word table.
     */
    public boolean remove(String elt) {
        Node pointer = root.getNext();
        Node previous = pointer;
        while (pointer != null) {
            if (pointer.getWord() == elt) {
                previous.setNext(pointer.getNext());
                return false;
            }
            previous = pointer;
            pointer = pointer.getNext();
        }
        return true;
    }

    /**
     * Returns true if word exists in the WordSymbolTable; false otherwise.
     *
     * @param elt target element to seek.
     */
    public boolean contains(String elt) {
        Node pointer = root.getNext();
        while (pointer != null) {
            if (pointer.getWord() == elt) {
                return true;
            }
            pointer = pointer.getNext();
        }
        return false;
    }

    /**
     * Returns the count for the word (or 0 if the word doesn't exist in the symbol table).
     *
     * @param elt target element to seek.
     */
    public int count(String elt) {
        Node pointer = root.getNext();
        while (pointer != null) {
            if (pointer.getWord() == elt) {
                return pointer.getCount();
            }
            pointer = pointer.getNext();
        }
        return 0;
    }

    /**
     * Return a word whose repetition count is equal to the maximum in the Symbol table.
     * <p>
     * Note that there may be multiple words that have the maximum count, so this method
     * only needs to return one of them.
     */
    public String mostFrequent() {
        Node pointer = root.getNext();
        Node maxNode = pointer;
        while (pointer != null) {
            if (pointer.getCount() > maxNode.getCount()) {
                maxNode = pointer;

            }
            pointer = pointer.getNext();
        }
        if (maxNode.getWord() == null) {
            return "";
        }
        return maxNode.getWord();
    }


    /**
     * For debugging, return semicolon-separated string of (word,count) pairs.
     */
    public String elements() {
        Node pointer = root.getNext();
        StringBuilder sb = new StringBuilder();
        String prefix = "";
        while (pointer != null) {
            sb.append(prefix);
            sb.append("(");
            sb.append(pointer.getWord());
            sb.append(",");
            sb.append(pointer.getCount());
            sb.append(")");
            prefix = ";";
            pointer = pointer.getNext();
        }
        System.out.println(sb.toString());
        return sb.toString();

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

        WordSymbolTable wl = new WordSymbolTable();
        validate(0, wl.size());
        validate("", wl.elements());           // empty word list must return ""
        validate(0, wl.count("nothing"));
        validate(false, wl.contains("this"));
        validate(true, wl.increment("test"));
        validate("(test,1)", wl.elements());       // no trailing or pre comma.
        validate(1, wl.count("test"));
        validate(false, wl.contains("this"));
        validate(true, wl.contains("test"));

        validate(1, wl.count("test"));
        validate(false, wl.increment("test"));       // when add TWICE, false is returned
        validate(2, wl.count("test"));

        validate(true, wl.remove("test"));     // can remove first element
        validate(false, wl.remove("test"));    // can't remove first empty
        validate(true, wl.increment("test"));
        validate(true, wl.increment("that"));
        validate(1, wl.count("test"));
        validate(false, wl.increment("that"));
        validate(2, wl.count("that"));
        validate(3, wl.totalCounts());
        validate(2, wl.size());
        validate(false, wl.remove("not"));
        validate(true, wl.remove("test"));
        validate("(that,2)", wl.elements());       // no trailing or pre comma.
        validate(true, wl.remove("that"));
    }
}
