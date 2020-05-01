package gddeml.hw3;

import edu.princeton.cs.algs4.StdRandom;

/**
 * This is the template code for question 1.
 *
 * Be sure to Explain whether the empirical results support the proposition.
 *
 */
public class Question1 {
	public static void main(String[] args) {
		System.out.println("N      MaxComp       MaxEach");
		for (int i = 16; i <= 512; i *= 2) {
			Double[] compareableList = new Double[i];
			int maxNumberOfExchanges = 0;
			int maxNumberOfCompares = 0;
			for (int numOfTrials = 0; numOfTrials < 100; numOfTrials++) {
				for (int n = 0; n < i; n++) {
					compareableList[n] = (StdRandom.uniform());
				}
				Heap.constructHeap(compareableList);
				if (Heap.getNumberOfExchanges() > maxNumberOfExchanges) {
					maxNumberOfExchanges = Heap.getNumberOfExchanges();
				}
				if (Heap.getNumberOfCompares() > maxNumberOfCompares) {
					maxNumberOfCompares = Heap.getNumberOfCompares();
				}
				Heap.resetExchangesAndCompares();
			}
			System.out.println(i + "       " + maxNumberOfCompares + "        " + maxNumberOfExchanges);
		}

		// for N in 16 .. 512
		
		//   for each N, do T=100 trials you want to keep track of 
		//       what you believe to be the MOST number of exch invocations
		//       and most number of less invocations
		
		//       compute a random array of N uniform doubles
		
		//   Make sure you output for each N the maximum values you saw
		//   in a table like...
		//
		//       N   MaxComp    MaxExch
		//       16  22         8
		//     .....
	}
}
