package gddeml.hw4;

import edu.princeton.cs.algs4.DijkstraUndirectedSP;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.SeparateChainingHashST;

import java.io.File;
import java.util.Scanner;


public class LongestWordLadder {

    /**
     * Represent the mapping of (uniqueID, 4-letter word)
     */
    static SeparateChainingHashST<String, Integer> table = new SeparateChainingHashST<String, Integer>();
    static SeparateChainingHashST<Integer, String> reverse = new SeparateChainingHashST<Integer, String>();

    /**
     * Determine if the two same-sized words are off by just a single character.
     */
    public static boolean offByOne(String w1, String w2) {
        int charMiss = 0;
        for (int i = 0; i < w1.length(); i++) {
            if (w1.charAt(i) != w2.charAt(i)) {
                if (++charMiss > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws java.io.IOException {

        // Use this to contain all four-letter words that you find in dictionary
        AVL<String> avl = new AVL<String>();


        // create a graph where each node represents a four-letter word.
        // Also construct avl tree of all four-letter words.
        // Note: you will have to copy this file into your project to access it, unless you
        // are already writing your code within the SedgewickAlgorithms4ed project.
        Scanner sc = new Scanner(new File("E:\\OneDrive - Worcester Polytechnic Institute (wpi.edu)\\D term F\\CS2223\\rawCloned\\cs2223d20\\Algorithms D2020\\src\\algs\\hw4\\words.english.txt"));
        int fourLetterWordCount = 0;
        while (sc.hasNext()) {
            String s = sc.next();
            if (s.length() == 4) {
                avl.insert(s);
                table.put(s, fourLetterWordCount);
                reverse.put(fourLetterWordCount, s);
                fourLetterWordCount++;
            }
        }
        sc.close();

        // now construct graph, where each node represents a word, and an edge exists between
        // two nodes if their respective words are off by a single letter. Use EdgeWeightedGraph
        // Because you can run DijkstraUndirectedSP on it. Hint: use the
        // keys() method provided by the AVL tree to iterate over all keys in the graph

        EdgeWeightedGraph graph = new EdgeWeightedGraph(table.size());

        String min = avl.min();
        for (String w1 : avl.keys()) {
            for (String w2 : avl.keys(min, w1)) {
                if (offByOne(w1, w2)) {
                    graph.addEdge(new Edge(table.get(w1), table.get(w2), 1));
                }
            }
        }

        double max = 0;
        int maxu = 0;
        int maxv = 0;

        for (int n = 0; n < table.size(); n++) {
            DijkstraUndirectedSP dijkstra = new DijkstraUndirectedSP(graph, n);
            for (int i = 0; i < table.size(); i++) {
                double distance = dijkstra.distTo(i);
                if ((distance < Double.POSITIVE_INFINITY) && distance > max) {
                    max = distance;
                    maxu = n;
                    maxv = i;
                }
            }
        }


        // Find largest non-infinite distance between any two vertices.

        System.out.println("longest WordLadder from " + reverse.get(maxu) + " to " + reverse.get(maxv) + " in " + max + " steps.");
        System.out.println("No go back and use WordLadder to recover this.");
    }
}
