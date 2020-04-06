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
        int n = this.length();
        int numberTriangles = 1 + ((n - 1) / 3);
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
        if (hi < 0) {
            return null;
        }
        int row = hi;
        int hi2 = hi;
        int lo2 = 1;
        while (lo2 <= hi2) {
            int mid = (lo2 + hi2) / 2;
            int rc = inspect(row, mid) - target;
            if (rc < 0) {
                lo2 = mid + 1;
            } else if (rc > 0) {
                hi2 = mid - 1;
            } else {
                return new int[]{row, mid};
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
//    public static int[][] unrap(int h) {
////       Init the return array
//        int numOfTriangles = (int) (1 + Math.floor((h - 1) / 3));
//        int totalNodes = ((h + 1) * h) / 2;
//        System.out.println(totalNodes);
//        int[][] out = new int[totalNodes][2];
////       Find the number of triangles
//
////       Init the tmp height
//        int tmpTriangleHeight = h;
////       Init the start x and start y
//        int tmpX = 0;
//        int startX = 0;
//        int startY = 0;
////       For all of the triangles that we have
//        for (int tri = 0; tri < numOfTriangles; tri++) {
////            First for the first row
//            for (int j = 0; j < tmpTriangleHeight; j++) {
////                Put the first row in
//                int x = j + startX;
//                int y = j + startY;
//                out[tmpX][0] = x;
//                out[tmpX++][1] = y;
//            }
//            for (int j = 1; j < tmpTriangleHeight - 1; j++) {
//                int x = tmpTriangleHeight - j ;
//                int y = tmpTriangleHeight - 1;
//                out[tmpX][0] = x;
//                out[tmpX++][1] = y;
//            }
//            for (int j = 1; j < tmpTriangleHeight  ; j++) {
//                int y = tmpTriangleHeight - j;
//                int x = tri;
//                out[tmpX][0] = x;
//                out[tmpX++][1] = y;
//            }
//            tmpX--;
//            startX++;
//            startY = startY + 2;
//            tmpTriangleHeight = tmpTriangleHeight - 3;
//        }
//        return out;
//    }
    public static int[] unwrap(int h, int value) {
//        int numOfTriangles = (int) (1 + Math.floor((h - 1) / 3));
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
//        int nodesLeft = value - nodesLeft;
//        int heightDelta = h - tmpHeight;
        int startX = heightDelta;
        int startY = heightDelta * 2;

        int X = 0;
        int Y = 0;
//        int tmpNum2 = (int) Math.round(tmpHeight / Math.sqrt(2));
//        int tmpNum = tmpNum2 + tmpHeight ;
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
//        int[][] ar = RowOrderedArraySearch.create(13);
//        new RowOrderedArraySolution(ar).trial();
//        int[] test2 = unwrap(4, 9);

        for (int i = 0; i <= 28; i++) {
            int[] test = unwrap(7, i);
            if (test != null) {
                System.out.println(i + " = (" + test[0] + " " + test[1] + ") ");
            }
        }
    }
}
