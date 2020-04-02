package gddeml.hw1;

import algs.hw1.arraysearch.DiagonalArraySearch;

import java.util.Arrays;

/**
 * Copy this class into your package, which must have USERID has its root.
 */
public class DiagonalArraySolution extends DiagonalArraySearch {

    /**
     * Construct problem solution for given array. Do not modify this method.
     */
    public DiagonalArraySolution(int[][] a) {
        super(a);
    }

    /**
     * For this homework assignment, you need to complete the implementation of this
     * method.
     */
    @Override
    public int[] locate(int target) {
//        System.out.println(inspect(12, 1));
        int n = this.length();
        int lo = 0;
        int hi = n - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int rc = inspect(mid, 0) - target;
            if (rc < 0) {
                lo = mid + 1;
            } else if (rc > 0) {
                hi = mid - 1;
            } else {
                return new int[]{mid, 0};
            }
        }

        int row = hi;
//        System.out.println("Row: " + row);
        System.out.println(target);
        int hi2 = 12 - Math.abs(hi);
        int lo2 = 1;
        while (lo2 <= hi2) {
            int mid = (lo2 + hi2) / 2;
            int y = (row +  mid);
            int x = mid;
            int hello = inspect(y, x);
            int rc = hello - target;
            if (rc < 0) {
                lo2 = mid + 1;
            } else if (rc > 0) {
                hi2 = mid - 1;
            } else {
                return new int[]{y, x};
            }
        }
        return null;
    }

    /**
     * Be sure that you call your class constructor. Do not modify this method.
     */
    public static void main(String args[]) {
        int[][] ar = DiagonalArraySearch.create(13);
        
        new DiagonalArraySolution(ar).trial();
    }
}
