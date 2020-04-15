package gddeml.hw2;

/**
 * For this question, you are to process the book "The Tale of Two Cities" as included in the repository.
 * <p>
 * There are 45 chapters in the book, which I have extracted into separate files. I will admit that the
 * transcription is quite awkward. For example, everything has been converted to lower case, and all punctuation
 * marks have been removed. Some words are subdivided improperly, but this is what we have to work with!
 * <p>
 * The questions you are to answer are:
 * <p>
 * 1. Which chapter contains the most # of words in total, as returned by the TaleOfTwoCitiesExtractor
 * 2. Which chapter (of the 45) contains the most # of unique words? and how many unique words occur in
 * that chapter.
 * 3. Which two distinct chapters share the most words in common? And how many words is that?
 * <p>
 * The definition of a word is given to you by the TaleOfTwoCitiesExtractor class, which provides an
 * Iterable interface to a given chapter. This object will return the words in a chapter, one at a time,
 * in the order they appear in the chapter.
 * <p>
 * Your first responsibility is to ensure that TaleOfTwoCitiesExtractor works in your location.
 */
public class Q1 {

    /**
     * Complete this implementation.
     */
    static void largestChapter() throws java.io.IOException {
        int chapterWithMostWords = -1;
        int mostWords = -1;
        for (int i = 1; i < 46; i++) {
            int counter = 0;
            TaleOfTwoCitiesExtractor totce = new TaleOfTwoCitiesExtractor(i);
            for (String s : totce) {
                counter++;
            }
            if (counter > mostWords) {
                mostWords = counter;
                chapterWithMostWords = i;
            }
        }
        System.out.println(String.format("The chapter with the most number of words is %d with a total of %s", chapterWithMostWords, mostWords));
    }

    /**
     * Complete this implementation.
     */
    static void fewestUniqueWords() throws java.io.IOException {
//        int chapter = -1;
//        int minUnique = Integer.MAX_VALUE;
        int chapter = 0;
        int minUnique = Integer.MAX_VALUE;
        for (int i = 1; i < 46; i++) {
            TaleOfTwoCitiesExtractor totce = new TaleOfTwoCitiesExtractor(i);
            WordList wl = new WordList();
            for (String s : totce) {
                wl.add(s);
            }
            if (wl.size() < minUnique) {
                minUnique = wl.size();
                chapter = i;
            }
        }
        System.out.println(String.format("The chapter with the fewest number of unique words is %d with a total of %s", chapter, minUnique));
    }

    /**
     * Complete this implementation.
     */
    static void totalUniqueWords() throws java.io.IOException {
        WordList wl = new WordList();
        for (int i = 1; i < 46; i++) {
            TaleOfTwoCitiesExtractor totce = new TaleOfTwoCitiesExtractor(i);
            for (String s : totce) {
                wl.add(s);
            }
        }
        System.out.println(String.format("There are a total of %d unique words in the book.", wl.size()));
    }

    /**
     * Complete this implementation.
     */
    static void twoChaptersShareMostInCommon() throws java.io.IOException {
        int chapter1 = -1;
        int chapter2 = -1;
        int maxShared = -1;
        for (int totce1LoopCounter = 1; totce1LoopCounter < 46; totce1LoopCounter++) {
            TaleOfTwoCitiesExtractor totce1 = new TaleOfTwoCitiesExtractor(totce1LoopCounter);
            WordList w1 = new WordList();
            for (String s : totce1) {
                w1.add(s);
            }
            for (int totce2LoopCounter = 1 + totce1LoopCounter; totce2LoopCounter < 46; totce2LoopCounter++) {
                WordList w2 = new WordList();
                int sharedLocal = 0;
                TaleOfTwoCitiesExtractor totce2 = new TaleOfTwoCitiesExtractor(totce2LoopCounter);
                for (String s : totce2) {
                    if (w1.contains(s) && !w2.contains(s)) {
                        w2.add(s);
                        sharedLocal++;
                    }
                }
                if (sharedLocal > maxShared) {
                    maxShared = sharedLocal;
                    chapter1 = totce1LoopCounter;
                    chapter2 = totce2LoopCounter;
                }
            }
        }
        System.out.println(String.format("The two chapters that share the most words in common are chapters %d and %d with a total of %s words", chapter1, chapter2, maxShared));
    }

    /**
     * Leave this method alone.
     */
    public static void main(String[] args) throws java.io.IOException {
        largestChapter();
        fewestUniqueWords();
        totalUniqueWords();
        twoChaptersShareMostInCommon();
    }
}
