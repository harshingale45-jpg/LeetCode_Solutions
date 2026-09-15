class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        boolean[][] pal = new boolean[n][n];

        // Precompute palindrome table
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;

                if (len == 1) {
                    pal[i][j] = true;
                } else if (len == 2) {
                    pal[i][j] = (s.charAt(i) == s.charAt(j));
                } else {
                    pal[i][j] = (s.charAt(i) == s.charAt(j)) && pal[i + 1][j - 1];
                }
            }
        }

        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1];

            for (int start = i - k; start >= 0; start--) {
                if (pal[start][i - 1]) {
                    dp[i] = Math.max(dp[i], dp[start] + 1);
                }
            }
        }

        return dp[n];
    }
}