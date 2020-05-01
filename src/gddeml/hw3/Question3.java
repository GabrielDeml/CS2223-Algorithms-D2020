package gddeml.hw3;

/**
 * Copy this class into your USERID.hw3 package and complete
 */
public class Question3 {
	
	public static void main(String[] args) throws java.io.IOException {
		
		// First Construct the Binary Search Tree from these Strings where
		// the associated value is the total number of times the key appeared
		// in "The Tale Of Two Cities".
		BST bt = new BST();
		for (int i = 1; i < 46; i++) {
			TaleOfTwoCitiesExtractor totce = new TaleOfTwoCitiesExtractor(i);
			for (String s : totce) {
				if (bt.get(s) != null) {
					bt.put(s, bt.get(s) + 1);
				} else {
					bt.put(s, 1);
				}
			}
		}
		System.out.println("Top ten most frequent words");

		for(int i=0; i < 10; i++) {
			String mostF = bt.mostFrequent();
			System.out.println(mostF);
			bt.delete(mostF);
		}
		System.out.println("\n\nNumber of words that appear once");
		BST bt2 = new BST();
		for (int i = 1; i < 46; i++) {
			TaleOfTwoCitiesExtractor totce = new TaleOfTwoCitiesExtractor(i);
			for (String s : totce) {
				if (bt2.get(s) != null) {
					bt2.put(s, bt2.get(s) + 1);
				} else {
					bt2.put(s, 1);
				}
			}
		}
		System.out.println("Number of unique words " + bt2.printUnique());
		 
	}
	
}
