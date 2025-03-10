package greedyalgorithms;

import java.util.*;

public class CoinChangeProblem {
    
    public static void coinChange(int[] coins, int N) {
        // sort coins in ascending order
        Arrays.sort(coins);

        int index = coins.length - 1;
 
        while (true) { 
            int coinValue = coins[index];
            index--;

            int maxAmount = (N / coinValue) * coinValue;
            if (maxAmount > 0) {
                System.out.println("Coin value: " + coinValue + " taken count: " + (N / coinValue));
                N = N - maxAmount;
            }

            if (N == 0) break;
        }
    }

    public static void main(String[] args) {
        int[] coins = { 1, 2, 5, 10, 20, 50, 1000 };
        int amount = 2035;

        System.out.println("Coins: " + Arrays.toString(coins));
        System.out.println("Target amount: " + amount);
        
        CoinChangeProblem.coinChange(coins, amount);
    }
}
