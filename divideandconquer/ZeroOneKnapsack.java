package divideandconquer;

public class ZeroOneKnapsack {

    public static int knapSack(int[] profits, int[] weights, int capacity, int currentIndex) {
        if (capacity <= 0 || currentIndex < 0 || currentIndex >= profits.length) return 0;

        int profit1 = 0;
        int profit2 = 0;

        if (weights[currentIndex] <= capacity) {
            // add
            profit1 = profits[currentIndex] + knapSack(profits, weights, capacity-weights[currentIndex], currentIndex+1);
            // skip
            profit2 = knapSack(profits, weights, capacity, currentIndex+1);
        }
        return Math.max(profit1, profit2);
    }

    public static void main(String[] args) {
        int[] profits = { 31, 26, 17, 72 };
        int[] weight = { 3, 1, 2, 5, };
        int capacity = 7;
        System.out.println(ZeroOneKnapsack.knapSack(profits, weight, capacity, 0));
    }
}
