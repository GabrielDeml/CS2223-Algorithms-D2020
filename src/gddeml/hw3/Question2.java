package gddeml.hw3;

import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.SequentialSearchST;

/**
 * This question explores the different structures that result from using binary search trees and separate
 * chaining hash table to support the Symbol Table API, which allows you to associate a (key, value) pair
 * for future retrieval.
 * <p>
 * Once again, using the Tale Of Two Cities data set, modify this program to produce the following table.
 * This question is based on the number of comparisons needed to locate each key in the symbol table.
 * <p>
 * For Binary Search Trees, the depth of a node reflects the distance from the root, and this depth
 * is one value smaller than the total number of comparisons needed to locate that node in the tree
 * The same is true of AVL trees
 * For Separate Chaining Hash Symbol Tables, each of the buckets stores its size, and each of these
 * will contribute to the overall values.
 * <p>
 * For example, If there were five (key, value) pairs stored in the symbol table implemented as above
 * as follows (note that only the keys are shown).
 * <p>
 * BST                          AVL                  HT
 * <p>
 * B                             C                [0] -> E, A, C
 * / \                           / \               [1]
 * A   C                         B   E              [2] -> B
 * \                       /   /               [3]
 * D                     A   D                [4] -> D
 * \                                         [5]
 * E
 * <p>
 * Then # of comparisons to find each one is (note that hashing doesn't count as a compare):
 * <p>
 * A - 2                       A - 3                A - 2
 * B - 1                       B - 2                B - 1
 * C - 2                       C - 1                C - 3
 * D - 3                       D - 3                D - 1
 * E - 4                       E - 2                E - 1
 * <p>
 * Thus average is:
 * <p>
 * 12.5/5 = 2.5                 11/5 = 2.2           8/5 = 1.6
 * <p>
 * Your task is to complete this experiment using the Tale Of Two Cities data set to record the count of
 * each word in a symbol table. You will have to use put (key, value) properly. That is, for the first
 * occurrence of a given word, w, you would put (w, 1) -- hint: how would you determine this using the existing API?
 * For each subsequent occurrence of a given word, just increment the value associated with w by calling put() with a
 * count that is one greater. With the above data set, this is what you would output
 * <p>
 * N      1,   2,   3,   4
 * #BST   1,   2,   1,   1
 * #AVL   1,   2,   2
 * #HT    3,   1,   1
 * <p>
 * AVG. BST Depth:2.5
 * AVG. AVL Depth:2.2
 * AVG. HT  Depth:11.6
 * <p>
 * - - - - - - - - - - - - - - - - - BELOW IS THE SAMPLE OUTPUT YOU ARE TO MATCH - - - - - - - - - - - - - - - - - - - -
 * <p>
 * There are 10650 unique words.
 * The Height of the BST is 30
 * The Height of the AVL is 15
 * <p>
 * N      1,   2,   3,   4,   5,   6,   7,   8,   9,  10,  11,  12,  13,  14,  15,  16,  17,  18,  19,  20,  21,  22,  23,  24,  25,  26,  27,  28,  29
 * #BST   1,   2,   4,   8,  15,  30,  57,  98, 159, 252, 383, 549, 740, 886, 997,1085,1053,1024, 955, 793, 583, 391, 273, 157,  79,  37,  20,  10,   7,   2
 * #AVL   1,   2,   4,   8,  16,  32,  64, 128, 256, 512,1020,1944,2904,2583,1066, 110
 * #HT 2035,1977,1820,1549,1204, 867, 557, 331, 166,  92,  40,  11,   1
 * <p>
 * AVG. BST Depth:15.509671361502347
 * AVG. AVL Depth:11.716338028169014
 * AVG. HT Depth:3.6068544600938965
 */
public class Question2 {
    public static void main(String[] args) throws java.io.IOException {
        BST b = new BST();
        AVL avl = new AVL();
        InstrumentedSeparateChainingHashST<String, Integer> hashST = new InstrumentedSeparateChainingHashST<>();

        // Now process the data from Tale Of Two Cities and use these three different symbol tables
        // to build up the frequency of each word. When you are done, there should be a total
        // of 10,650 unique keys in each of these symbol tables, and the Integer counts would be the
        // frequency of occurrence.
        // FILL IN HERE...
        for (int i = 1; i < 46; i++) {
            TaleOfTwoCitiesExtractor totce = new TaleOfTwoCitiesExtractor(i);
            for (String s : totce) {
//				BST
                if (b.get(s) != null) {
                    b.put(s, b.get(s) + 1);
                } else {
                    b.put(s, 1);
                }

//				AVL
                if (avl.get(s) != null) {
                    avl.put(s, avl.get(s) + 1);
                } else {
                    avl.put(s, 1);
                }

//				hashST
                if (hashST.get(s) != null) {
                    hashST.put(s, hashST.get(s) + 1);
                } else {
                    hashST.put(s, 1);
                }
            }
        }

        System.out.println("There are " + b.size() + " unique words.");
        System.out.println("The Height of the BST is " + b.height());
        System.out.println("The Height of the AVL is " + avl.root.height);

        // Here is where you will generate output that looks like ....
        System.out.print("N   ");
        for (int n = 1; n <= 30; n++) {
            System.out.print(String.format("%4d,", n));
        }

        SeparateChainingHashST<Integer, Integer> bCollect = b.collect();
        System.out.print("\n#BST");
        for (int n = 1; n < bCollect.size() + 1; n++) {
            System.out.print(String.format("%4d,", bCollect.get(n)));
        }
        SeparateChainingHashST<Integer, Integer> aCollect = avl.collect();
        System.out.print("\n#AVL");
        for (int n = 1; n < aCollect.size() + 1; n++) {
            System.out.print(String.format("%4d,", aCollect.get(n)));
        }

        SeparateChainingHashST<Integer, Integer> hCollect = new SeparateChainingHashST<>();
        int SSsize;
        for (SequentialSearchST SS : hashST.st) {
            SSsize = SS.size();
            if (!hCollect.contains(SSsize)) {
                hCollect.put(SSsize, 0);
            } else {
                hCollect.put(SSsize, hCollect.get(SSsize) + 1);
            }

        }
        SeparateChainingHashST<Integer, Integer> hCollectMarged = new SeparateChainingHashST<>();
        int totalCountAtHeight;
        for (int i = 0; i < hCollect.size(); i++) {
            totalCountAtHeight = 0;
            if (hCollect.get(i) != null) {
                for (int n = i; n < hCollect.size(); n++) {
                    totalCountAtHeight += hCollect.get(n) + 1;
                }
            }
            hCollectMarged.put(i, totalCountAtHeight);
        }

        System.out.print("\n#HT  ");
        for (int n = 1; n < hCollectMarged.size(); n++) {
            System.out.print(String.format("%4d,", hCollectMarged.get(n)));
        }
        System.out.println();

        // now output a row for each of the #BST, #AVL, #HT
        int bTotaln = 0;
        int bTotal = 0;
        for (int n = 1; n < bCollect.size() + 1; n++) {
            bTotaln += n * (bCollect.get(n));
            bTotal += bCollect.get(n);
        }
        int aTotaln = 0;
        int aTotal = 0;
        for (int n = 1; n < aCollect.size() + 1; n++) {
            aTotaln += n * (aCollect.get(n));
            aTotal += aCollect.get(n);
        }
        int hTotaln = 0;
        int hTotal = 0;
        for (int n = 1; n < hCollectMarged.size(); n++) {
            hTotaln += n * (hCollectMarged.get(n));
            hTotal += hCollectMarged.get(n);
        }

        System.out.println(bTotal);
        System.out.println(bTotaln);
        System.out.println("AVG. BST Depth: " + (double) bTotaln / bTotal);
        System.out.println("AVG. AVL Depth: " + (double) aTotaln / aTotal);
        System.out.println("AVG. HT Depth: " + (double) hTotaln / hTotal);

    }
}
