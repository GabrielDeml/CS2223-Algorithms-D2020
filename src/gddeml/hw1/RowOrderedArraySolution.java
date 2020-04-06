package gddeml.hw1;

import algs.hw1.arraysearch.RowOrderedArraySearch;

/**
 * Copy this class into your package, which must have USERID has its root.
 */
public class RowOrderedArraySolution extends RowOrderedArraySearch {

    /**
     * Construct problem solution for given array. Do not modify this method.
     */
    public RowOrderedArraySolution(int[][] a) {
        super(a);
    }

    /**
     * For this homework assignment, you need to complete the implementation of this
     * method.
     */
    @Override
    public int[] locate(int target) {
        System.out.println("target: " + target + " length: " + this.length());
        int nOrig = this.length();
        int n = ((nOrig + 1) * nOrig) / 2;
        if (target > n) {
            return null;
        }
        int lo = 0;
        int hi = n - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int[] XY = unwrap(nOrig, mid);
            if (XY == null) {
                return null;
            }
            int rcTmp = inspect(XY[0], XY[1]);
            int rc = rcTmp - target;
            if (rc < 0) {
                lo = mid + 1;
            } else if (rc > 0) {
                hi = mid - 1;
            } else {
                return new int[]{mid, 0};
            }
        }
        return null;
    }

    /**
     * I got tired of trying to treat this problem as a nested triangle so this function just unraps it.
     * It is not the most effencent, but it works.
     *
     * @param h the height of the array
     * @return an array that contains the real x and y locations of the array
     */

    public static int[] unwrap(int h, int value) {
        int totalNodes = ((h + 1) * h) / 2;
        if (value > totalNodes || value == 0) {
            return null;
        }
//        if (value == 0) {
//            return new int[]{0, 0};
//        }
        int nodesLeft = value;
        int tmpHeight = h;
        int numberOfNodesInTri = 0;
        int heightDelta = 0;
        while (true) {
            numberOfNodesInTri = (3 * (tmpHeight - 1));
            if (numberOfNodesInTri < nodesLeft && nodesLeft != 1) {
                nodesLeft = nodesLeft - numberOfNodesInTri;
                tmpHeight = tmpHeight - 3;
                heightDelta++;
            } else break;
        }
        int startX = heightDelta;
        int startY = heightDelta * 2;

        int X = 0;
        int Y = 0;
        if (nodesLeft <= tmpHeight) {
            X = startX + nodesLeft - 1;
            Y = startY + nodesLeft - 1;
        } else if (nodesLeft <= (2 * tmpHeight) - 1) {
            X = startX + (tmpHeight - (nodesLeft - tmpHeight)) - 1;
            Y = startY + tmpHeight - 1;
        } else {
            X = startX;
            Y = startY + tmpHeight - (nodesLeft - (tmpHeight + (tmpHeight - 1))) - 1;
        }
        return new int[]{X, Y};
    }

    /**
     * Be sure that you call your class constructor. Do not modify this method.
     */
//    TODO: Make main static again
    public static void main(String args[]) {
        int[][] ar = RowOrderedArraySearch.create(13);
        new RowOrderedArraySolution(ar).trial();
//        new RowOrderedArraySolution(ar).locate(1092);
//        int[] test2 = unwrap(4, 9);

//        for (int i = 0; i <= 91; i++) {
//            int[] test = unwrap(13, i);
//            if (test != null) {
////                System.out.println(i + " = (" + test[0] + " " + test[1] + ") ");
//                System.out.println(test[1]);
//            }
//        }
    }
}
