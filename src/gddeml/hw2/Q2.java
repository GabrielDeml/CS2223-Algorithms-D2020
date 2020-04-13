package gddeml.hw2;

import java.util.ArrayList;
import java.util.List;

/**
 * Building from Question 1, there are different questions to answer.
 * <p>
 * 1. What is the most frequently used word in the entire book?
 * 2. What are the top-ten most frequently used words in the entire book?
 * 3. How many words occur exactly once in the book?
 */
public class Q2 {

//    private static void updateTopTen(WordSymbolTable.Node target, List<WordSymbolTable.Node> topTen) {
//        for (int i = 0; i < topTen.size(); i++) {
//            if (topTen.get(i).getCount() < target.getCount()) {
//                topTen.add(i, target);
//                break;
//            }
//        }
//    }

    static void mostFrequent() throws java.io.IOException {
        WordSymbolTable wst = new WordSymbolTable();
        int wordCount = 0;
        for (int i = 1; i < 46; i++) {
            TaleOfTwoCitiesExtractor totce = new TaleOfTwoCitiesExtractor(i);
            for (String s : totce) {
                wordCount++;
                wst.increment(s);
            }
        }
        String mostFrequentWord = wst.mostFrequent();
        int mostFrequentTimesUsed = wst.count(mostFrequentWord);

        StringBuilder sb = new StringBuilder();
        String mfw;
        int topTenCounter = 0;
        int count = 0;
        for (int i = 0; i < 10; i++) {
            mfw = wst.mostFrequent();
            sb.append("(");
            sb.append(mfw);
            sb.append(" ");
            count = wst.count(mfw);
            topTenCounter = topTenCounter + count;
            sb.append(count);
            sb.append(") ");
            wst.remove(mfw);
        }

        System.out.println(
                String.format("\"%s\" is the most frequent word, used %d times out of %d total words (%.2f%%)",
                        mostFrequentWord, mostFrequentTimesUsed, wordCount, (float) 100 * mostFrequentTimesUsed / wordCount));

        System.out.println("The Top Ten words by frequency are: " + sb.toString());
        System.out.println("Top ten add to: " + topTenCounter);
        System.out.println(String.format("These ten words represent %.2f%% of the total words in the book", (float) topTenCounter * 100 / wordCount));

    }

    static void wordsUsedOnce() throws java.io.IOException {
        WordSymbolTable wst = new WordSymbolTable();

        for (int i = 1; i < 46; i++) {
            TaleOfTwoCitiesExtractor totce = new TaleOfTwoCitiesExtractor(i);
            for (String s : totce) {
                wst.increment(s);
            }
        }
        String pointer;
        int pointerCount = 0;
        int oneWordCount = 0;
        int maxLength = 0;
        while(wst.size() > 0) {
            pointer = wst.mostFrequent();
            if (wst.count(pointer) == 1) {
                oneWordCount++;
                if (pointer.length() > maxLength) {
                    maxLength = pointer.length();
                }
            }
            wst.remove(pointer);
        }
        System.out.println(String.format("%d words are used exactly once (longest is \"%s\")", oneWordCount, maxLength));
    }

    public static void main(String[] args) throws java.io.IOException {
        mostFrequent();
        wordsUsedOnce();
    }
}
