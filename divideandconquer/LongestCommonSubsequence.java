package divideandconquer;

public class LongestCommonSubsequence {

    public static int findLCSLength(String s1, String s2, int i1, int i2) {
        if (i1 == s1.length() || i2 == s2.length()) return 0;

        int c1 = 0;
        if (s1.charAt(i1) == s2.charAt(i2)) {
            c1 = 1 + findLCSLength(s1, s2, i1+1, i2+1);
        }
        int c2 = findLCSLength(s1, s2, i1, i2+1);
        int c3 = findLCSLength(s1, s2, i1+1, i2);

        return Math.max(c1, Math.max(c2, c3));
    }

    public static int findLCSLengthDpTopBottom(String s1, String s2, int i1, int i2, int[][] memo) {
        if (i1 == s1.length() || i2 == s2.length()) return 0;

        if (memo[i1][i2] == 0) {
            int c1 = 0;
            if (s1.charAt(i1) == s2.charAt(i2)) {
                c1 = 1 + findLCSLengthDpTopBottom(s1, s2, i1+1, i2+1, memo);
            }
            int c2 = findLCSLengthDpTopBottom(s1, s2, i1, i2+1, memo);
            int c3 = findLCSLengthDpTopBottom(s1, s2, i1+1, i2, memo);
            memo[i1][i2] = Math.max(c1, Math.max(c2, c3));
        }
        return memo[i1][i2];
    }

    public static int findLCSLengthDpBottomUp(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

		for (int i = s1.length(); i >= 1; i--) {

			for (int j = s2.length(); j >= 1; j--) {

				if (s1.charAt(i-1) == s2.charAt(j-1)) {
					dp[i-1][j-1] =  Math.max((1 + dp[i][j]), Math.max(dp[i][j-1], dp[i-1][j]));
				}
				else {
					dp[i-1][j-1] = Math.max(dp[i][j-1], dp[i-1][j]);
				}
                
			}
		}
		return dp[0][0];
    }

    public static void main(String[] args) {
        String s1 = "elephant";
        String s2 = "erepat";

        System.out.println(LongestCommonSubsequence.findLCSLength(s1, s2, 0, 0)); //eepat

        int[][] memo = new int[s1.length()][s2.length()] ;
        System.out.println(LongestCommonSubsequence.findLCSLengthDpTopBottom(s1, s2, 0, 0, memo)); 

        System.out.println(LongestCommonSubsequence.findLCSLengthDpBottomUp(s1, s2)); 
    }
}
