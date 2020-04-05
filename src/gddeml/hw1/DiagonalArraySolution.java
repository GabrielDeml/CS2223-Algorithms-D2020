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
        // Get the length of the array we are given
        int n = this.length();
        // Set the low value
        int lo = 0;
        // Set the high value
        int hi = n - 1;
        // Init mid and make it not null
        int mid = 0;
        // Do binary array search
        while (lo <= hi) {
            // Find the mid point
            mid = (lo + hi) / 2;
            // Get the mid point and set the offset from zero
            int rc = inspect(mid, 0) - target;
            // If it is smaller
            if (rc < 0) {
                lo = mid + 1;
                // If it is bigger
            } else if (rc > 0) {
                hi = mid - 1;
                // Else we must have found it
            } else {
                // Return the value
                return new int[]{mid, 0};
            }
        }
        // If it is outside the triangle then don't bother checking it again
        if (hi < 0) {
            return null;
        }

        // The row that we are in is the hi
        int row = hi;
        int hi2 = 12 - hi;
        int lo2 = 1;
        int mid2 = 0;
        while (lo2 <= hi2) {
            mid2 = (lo2 + hi2) / 2;
            int y = (row + mid2);
            int x = mid2;
            int hello = inspect(y, x);
            int rc = hello - target;
            if (rc < 0) {
                lo2 = mid2 + 1;
            } else if (rc > 0) {
                hi2 = mid2 - 1;
            } else {
                return new int[]{y, x};
            }
        }
        System.out.println("Hell I am just saying something");
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
