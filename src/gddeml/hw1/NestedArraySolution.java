package gddeml.hw1;

import algs.hw1.arraysearch.NestedArraySearch;

/**
 * Copy this class into your package, which must have USERID has its root.
 */
public class NestedArraySolution extends NestedArraySearch {

    /**
     * Construct problem solution for given array. Do not modify this method.
     */
    public NestedArraySolution(int[][] a) {
        super(a);
    }

    /**
     * For this homework assignment, you need to complete the implementation of this
     * method.
     */
    @Override
    public int[] locate(int target) {
        int nOrig = this.length();
        int n = ((nOrig + 1) * nOrig) / 2;
        if (target <= 0) {
            System.out.println("target was too small");
        }
        int lo = 1;
        int hi = n;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int[] XY = unwrap(nOrig, mid);
            if (XY == null) {
                System.out.println("XY returned null");
                return null;
            }
            int rcTmp = inspect(XY[1], XY[0]);
            int rc = rcTmp - target;
            if (rc < 0) {
                lo = mid + 1;
            } else if (rc > 0) {
                hi = mid - 1;
            } else {
                return new int[]{XY[1], XY[0]};
            }
        }
        return null;
    }

    /**
     * @param h     is the size of the square
     * @param value is the value we are converting
     * @return
     */
    public static int[] unwrap(int h, int value) {
        int totalNodes = ((h + 1) * h) / 2;
        if (value > totalNodes || value == 0) {
            return null;
        }
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
    public static void main(String args[]) {
        int[][] ar = NestedArraySearch.create(13);
		new NestedArraySolution(ar).trial();
    }
}
