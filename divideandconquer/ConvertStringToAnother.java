package divideandconquer;

public class ConvertStringToAnother {

    public static int findMinOperations(String s1, String s2, int i1, int i2) {
        if (i1 == s1.length()) return s2.length() - i2;
        if (i2 == s2.length()) return s1.length() - i1;
            
        if (s1.charAt(i1) == s2.charAt(i2)) {
            return findMinOperations(s1, s2, i1 + 1, i2 + 1);
        }

        // delete
        int deleteOp = 1 + findMinOperations(s1, s2, i1 , i2+1);
        // insert
        int insertOp = 1 + findMinOperations(s1, s2, i1+1, i2);
        // replace
        int replaceOp = 1 + findMinOperations(s1, s2, i1 + 1, i2 + 1);

        return Math.min(deleteOp, Math.min(insertOp, replaceOp));
    }

    public static int findMinOperationsDpTopDown(String s1, String s2, int i1, int i2, int[][] memo) {
        if (i1 == s1.length()) return s2.length() - i2;
        if (i2 == s2.length()) return s1.length() - i1;

        if (s1.charAt(i1) == s2.charAt(i2)) {
            return findMinOperations(s1, s2, i1 + 1, i2 + 1);
        }

        if (memo[i1][i2] == 0) {
            int deleteOp = 1 + findMinOperations(s1, s2, i1 , i2+1);
            int insertOp = 1 + findMinOperations(s1, s2, i1+1, i2);
            int replaceOp = 1 + findMinOperations(s1, s2, i1 + 1, i2 + 1);
            memo[i1][i2] = Math.min(deleteOp, Math.min(insertOp, replaceOp));
        }

        return memo[i1][i2];
    }

    public static int findMinOperationsDpBottomUp(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
 
        for (int i1 = 0; i1 <= s1.length(); i1++)
            dp[i1][0] = i1;
    
        for (int i2 = 0; i2 <= s2.length(); i2++) 
            dp[0][i2] = i2;
    
        for (int i1 = 1; i1 <= s1.length(); i1++) {
            for (int i2 = 1; i2 <= s2.length(); i2++) { 
            if (s1.charAt(i1 - 1) == s2.charAt(i2 - 1))
                dp[i1][i2] = dp[i1 - 1][i2 - 1];
            else
                dp[i1][i2] = 1 + Math.min(dp[i1 - 1][i2], // delete
                            Math.min(dp[i1][i2 - 1], // insert
                            dp[i1 - 1][i2 - 1])); // replace
            }
        }
        return dp[s1.length()][s2.length()];
    }

    public static void main(String[] args) {
        String s1 = "table";
        String s2 = "tbres";
        
        System.out.println(ConvertStringToAnother.findMinOperations(s1, s2, 0, 0));

        int[][] memo = new int[s1.length()][s1.length()];
        System.out.println(ConvertStringToAnother.findMinOperationsDpTopDown(s1, s2, 0, 0, memo));

        System.out.println(ConvertStringToAnother.findMinOperationsDpBottomUp(s1, s2));
    }
}
