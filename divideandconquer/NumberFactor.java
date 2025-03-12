// express N as a sum of 1, 3 and 4

package divideandconquer;

public class NumberFactor {
    
    public static int waysToGetN(int n) {
        if (n < 0) return -1;
        if (n == 0 || n == 1 || n == 2) return 1; 
        if (n == 3) return 2; // {1, 1, 1}, {3}

        int sub1 = waysToGetN(n-1);
        int sub2 = waysToGetN(n-3);
        int sub3 = waysToGetN(n-4);

        return sub1 + sub2 + sub3;
    }

    public static int waysToGetNDpTopDown(int n, int[] dp) {
        if (n < 0) return -1;
        if (n == 0 || n == 1 || n == 2) return 1; 
        if (n == 3) return 2; 

        if (dp[n] == 0) {
            int sp1 = waysToGetNDpTopDown(n-1, dp);
            int sp2 = waysToGetNDpTopDown(n-3, dp);
            int sp3 = waysToGetNDpTopDown(n-4, dp);
            dp[n] = sp1 + sp2 + sp3;
        }
        return dp[n];
    }

    public static int waysToGetNDpBottomUp(int n) {
        int dp[] = new int[n+1];
        dp[0] = dp[1] = dp[2] = 1;
        dp[3] = 2;

        for (int i = 4; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-3] + dp[i-4];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(NumberFactor.waysToGetN(6));

        System.out.println(waysToGetNDpTopDown(6, new int[7]));

        System.out.println(waysToGetNDpBottomUp(6));
    }
}
