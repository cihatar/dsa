package divideandconquer;

import java.util.HashMap;

public class HouseRobber {
    
    public static int maxMoney(int[] HouseNetWorth, int currentIndex) {
        if (currentIndex >= HouseNetWorth.length) return 0;

        int stealCurrentHouse = HouseNetWorth[currentIndex] + maxMoney(HouseNetWorth, currentIndex + 2);
        int skipCurrentHouse = maxMoney(HouseNetWorth, currentIndex + 1);

        return Math.max(stealCurrentHouse, skipCurrentHouse);
    }

    public static int maxMoneyDpTopDown(int[] HouseNetWorth, int currentIndex, HashMap<Integer, Integer> memo) {
        if (currentIndex >= HouseNetWorth.length) return 0;

        if (!memo.containsKey(currentIndex)) {
            int stealCurrentHouse = HouseNetWorth[currentIndex] + maxMoneyDpTopDown(HouseNetWorth, currentIndex + 2, memo);
            int skipCurrentHouse = maxMoneyDpTopDown(HouseNetWorth, currentIndex + 1, memo);
            memo.put(currentIndex, Math.max(stealCurrentHouse, skipCurrentHouse));
        }
        return memo.get(currentIndex);
    }

    public static int maxMoneyDpBottomUp(int[] HouseNetWorth) {
        int[] dp = new int[HouseNetWorth.length + 2];
        for (int i = HouseNetWorth.length - 1; i >= 0; i--) {
			dp[i] = Math.max(HouseNetWorth[i] + dp[i + 2], dp[i + 1]);
		}
        return dp[0];
    }

    public static void main(String[] args) {
        int[] HouseNetWorth = { 6, 7, 1, 30, 8, 2, 4 };
        System.out.println(HouseRobber.maxMoney(HouseNetWorth, 0));

        HashMap<Integer, Integer> memo = new HashMap<>();
        System.out.println(HouseRobber.maxMoneyDpTopDown(HouseNetWorth, 0, memo));

        System.out.println(HouseRobber.maxMoneyDpBottomUp(HouseNetWorth));
    }
}
