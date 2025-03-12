package divideandconquer;

public class MinimumCostToReachLastCell {

    public static int findMinimumCost(int[][] arr, int row, int col) {
        if (row == -1 || col == -1) return Integer.MAX_VALUE;
        if (row == 0 && col == 0) return arr[0][0];

        int op1 = findMinimumCost(arr, row-1, col);
        int op2 = findMinimumCost(arr, row, col-1);
        int minCost = Math.min(op1, op2);

        return minCost + arr[row][col];
    }

    public static void main(String[] args) {
        int[][] arr = { { 4, 7, 8, 6, 4 },
                        { 6, 7, 3, 9, 2 }, 
                        { 3, 8, 1, 2, 4 },
                        { 7, 1, 7, 3, 7 },
                        { 2, 9, 8, 9, 3 } };
        System.out.println(MinimumCostToReachLastCell.findMinimumCost(arr, arr.length-1, arr[0].length-1));
    }
}
