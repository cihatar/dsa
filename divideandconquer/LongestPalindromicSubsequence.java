package divideandconquer;

public class LongestPalindromicSubsequence {

    public static int findLPSLength(String s, int startIndex, int endIndex) {
        if (startIndex > endIndex) return 0;
        if (startIndex == endIndex) return 1;

        int c1 = 0;
        if (s.charAt(startIndex) == s.charAt(endIndex)) {
            c1 = 2 + findLPSLength(s, startIndex+1, endIndex-1);
        }
        int c2 = findLPSLength(s, startIndex, endIndex-1);
        int c3 = findLPSLength(s, startIndex+1, endIndex);

        return Math.max(c1, Math.max(c2, c3));
    }

    public static int findLPSLengthDpTopBottom(String s, int startIndex, int endIndex, int[][] memo) {
        if (startIndex > endIndex) return 0;
        if (startIndex == endIndex) return 1;

        if (memo[startIndex][endIndex] == 0) {
            int c1 = 0;
            if (s.charAt(startIndex) == s.charAt(endIndex)) {
                c1 = 2 + findLPSLength(s, startIndex+1, endIndex-1);
            }
            int c2 = findLPSLength(s, startIndex, endIndex-1);
            int c3 = findLPSLength(s, startIndex+1, endIndex);
            memo[startIndex][endIndex] = Math.max(c1, Math.max(c2, c3));
        }
        return memo[startIndex][endIndex];
    }

    public static int findLPSLengthDpBottomUp(String s) {
        int[][] dp = new int[s.length()][s.length()];

		for (int col = 0; col < s.length(); col++) {

			for (int row = s.length()-1; row >= 0; row--) {

				if (row > col) { 
					dp[row][col] = 0;
				} else if (row == col) { 
					dp[row][col] = 1;
				} else {
					if (s.charAt(row) == s.charAt(col)) {
						dp[row][col] = Math.max(2+dp[row + 1][col - 1], Math.max(dp[row][col - 1], dp[row + 1][col]));
					} else {
						dp[row][col] = Math.max(dp[row][col - 1], dp[row + 1][col]);
					}
				}

			} 
		}
		return dp[0][s.length()-1];
    }

    public static void main(String[] args) {
        String s = "elrmenmet";

        System.out.println(LongestPalindromicSubsequence.findLPSLength(s, 0, s.length()-1)); //ememe

        int[][] memo = new int[s.length()][s.length()];
        System.out.println(LongestPalindromicSubsequence.findLPSLengthDpTopBottom(s, 0, s.length()-1, memo)); 

        System.out.println(LongestPalindromicSubsequence.findLPSLengthDpBottomUp(s)); 
    }
}
