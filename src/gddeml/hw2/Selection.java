package gddeml.hw2;

import algs.days.day05.FixedCapacityQueue;

/**
 * N people go to a Casino and everyone wins a little bit of money. You suggest it would be
 * better if just one person collected all the winnings and you propose the following strategy.
 * <p>
 * 1. You tell everyone to stand in a circle (yourself included)
 * 2. You ask someone to volunteer to be the start. That is person #1. In clockwise fashion,
 * everyone in the circle is assigned a number from 1 to N.
 * 3. You pick a number 0 < k < N.
 * <p>
 * Starting with the starting person, count k people clockwise. The kth person leaves the circle
 * which shrinks by one in size; starting with the next person, again count k people clockwise
 * and that person leaves the circle.
 * <p>
 * The last one remaining collects all winnings.
 * <p>
 * Your program must produce a FixedCapacityQueue<Integer> reflecting the order in which people
 * are eliminated. The final item in the queue is the person who collects all winnings.
 * <p>
 * Your implementation MUST create a Linked List using the Node class.
 * <p>
 * For new Selection(5, 3).countOff(), the resulting queue should be [3 1 5 2 4] where last person to receive
 * winnings is person #4.
 * <p>
 * For new Selection(17, 7).countOff(), the resulting queue should be [7 14 4 12 3 13 6 17 11 9 8 10 16 5 15 1 2] where
 * last person to receive winnings is person #2.
 */
public class Selection {


    //    FixedCapacityQueue<Integer> queue;
    Node root;
    Node usedRoot;
    int lengthOfList = 0;

    final int N;      /* Number of people. */
    final int k;      /* Delta to counting. */

    /**
     * Construct an instance of the problem with N people choosing by k.
     */
    public Selection(int N, int k) {
        this.N = N;
        this.k = k;

        root = new Node(0);
        Node pointer = root;
        for (int i = 0; i < N; i++) {
            pointer.setNext(new Node(i));
            pointer = pointer.getNext();
            lengthOfList++;
        }
//        queue = new FixedCapacityQueue<Integer>(N);
    }

    /**
     * Use this node to form the linked list.
     */
    class Node {
        boolean isAlive = true;
        int person;
        Node next;

        public Node(int person) {
            this.person = person;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public void setPerson(int person) {
            this.person = person;
        }

        public int getPerson() {
            return person;
        }

        public Node getNext() {
            return next;
        }

        public boolean isAlive() {
            return isAlive;
        }

        public void setAlive(boolean isAlive) {
            this.isAlive = isAlive;
        }
    }

    class myLinkedList {
        Node head;
        Node tail;
        int count = 0;

        public int removeHead() {
            Node node = head;
            head = head.getNext();
            if(head == null){
                this.tail = null;
            }
            count--;
            return node.getPerson();
        }

        public void addTail(int i) {
            Node node = new Node(i);
            if (tail != null) {
                this.tail.setNext(node);
            }else{
                head = node;
            }
            this.tail = node;
            count++;
        }

        public int getCount() {
            return this.count;
        }
    }

    /**
     * Method consumes the elements of the queue and outputs them with spaces between elements.
     * <p>
     * No need to worry about a trailing space.
     *
     * @param result
     */
    static void output(FixedCapacityQueue<Integer> result) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        String prefix = "";
        while (!result.isEmpty()) {
            sb.append(prefix);
            prefix = " ";
            sb.append(result.dequeue());
        }
        sb.append("]");
        System.out.println(sb.toString());
    }

    /**
     * Key implementation for this assignment is to return a queue that contains the person identifiers (integers)
     * in the order that they were removed. The very last value is the "last man standing" who claims the full lottery winnings.
     */
    FixedCapacityQueue<Integer> countOff() {
        FixedCapacityQueue<Integer> outQueue = new FixedCapacityQueue<Integer>(N);
        myLinkedList queue = new myLinkedList();
        for (int i = 1; i <= N; i++)
            queue.addTail(i);
        while (queue.getCount() != 0) {
            for (int i = 0; i < k - 1; i++)
                queue.addTail(queue.removeHead());
            outQueue.enqueue(queue.removeHead());
        }
        return outQueue;
    }

    /**
     * Launch the small examples to demonstrate success.
     */
    public static void main(String[] args) {
        FixedCapacityQueue<Integer> result = new Selection(5, 3).countOff();
        System.out.println("N=5, k=3 should be [3 1 5 2 4]");
        output(result);
        System.out.println();
        result = new Selection(17, 7).countOff();
        System.out.println("N=17, k=7 should be [7 14 4 12 3 13 6 17 11 9 8 10 16 5 15 1 2]");
        output(result);
    }
}
