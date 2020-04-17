package gddeml.hw2;

/**
 * This is a more complicated version of a question you might see on the midterm.
 * 
 * Currently this code produces the following output:
  
    N          Val
     2	           0
     4	           4
     8	          60
    16	         620
    32	        5580
    64	       47244
   128	      388620
   256	     3152140
   512	    25390860
  1024	   203823884
  2048	  1633383180
  4096	 13078242060

Your task is to modify the program so it produces the following

    N          Val      Actual   Predicted
     2	           0      c2      S(2)
     4	           4      c4      S(4)
     8	          60      c8      S(8)
    16	         620      ...     ...
    32	        5580      ...     ...
    64	       47244      ...     ...
   128	      388620      ...     ...
   256	     3152140      ...     ...
   512	    25390860      ...     ...
  1024	   203823884      ...     ...
  2048	  1633383180      ...     ...
  4096	 13078242060      ...     ...
  
 Where each of the c2, c4, c8, ... is replaced by an integer that reflects the actual number of times 
 that power() was invoked during proc(a, 0, a.length-1) for arrays, a, of size 2, 4, 8, 16, ...
 The final Predicted column reflects the prediction of S(N), which you should implement below
 in the Q3.model(n) method.

 Note for the bonus question: add a fourth-column that shows your prediction of Val. Implement your 
 formula in the bonusModel(int n) method.

 */
public class Q3 {

	/** 
	 * Helper method to compute base^exp as a long.
	 */
	static long power(int base, int exp) {
		return (long) Math.pow(base, exp);
	}
	
	/**
	 * This is the method under review. Do not change this method.
	 */
	public static long proc(int[] a, int lo, int hi) {
//		If low equals hi return low^2 and hi^2
		if (lo == hi) { 
			return power(a[lo], 2) + power(a[hi],2);
		}

//		Find the mid point
		int m = (lo + hi) / 2;
//		Take low^2 + running this function on the lower half of the array
		long total = power(lo, 2) + proc(a, lo, m);
//		While hi is bigger than low
		while (hi > lo) {
//			Total = power a[lo]^2 + total
			total += power(a[lo], 2);
//			low + 2
			lo+=2;
		}
		
		return total;
	}
	
	/**
	 * Complete your analysis of the code and modify this function to return the prediction
	 * of how many times Math.power() is called for an initial problem of size n.
	 */
	static long model(int n) {
		System.out.println("Should be: " + (n + Math.log(n) / Math.log(2) + 1));
		System.out.println("Is: " + ((Math.log(n)) + (Math.log(n) * n) / 2));
		return 0;
	}
	
	/**
	 * Bonus question. Can you come up with a (more complicated) formula that predicts
	 * the value, Value, or proc(a,0,n-1) when a is composed of the integers from 0 to n-1. 
	 */
	static long bonusModel(int n) {
		// FIX ME
		return 0;
	}

	/** Launch the experiment. */
	public static void main(String[] args) {
//		System.out.println("    N          Value");
//		for (int n = 2; n <= 4096; n *= 2) {
//			int counter = 0;
//			int[] a = new int[n];
//			for (int i = 0; i < n; i++) { a[i] = i; }
//
//			// initiate the request on an array of size n, containing values from 0 to n-1
//			// using indices of lo=0 and hi=n-1
//
////			A is a array that contains the number of number in order of n aka n = 4 a = [0,1,2,3]
//
//			long val = proc(a, 0, n-1);
//
//			System.out.println(String.format("%6d\t%12d", n, val));
//		}
		model(5);
	}
}
