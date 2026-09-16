class Solution {
    private static final int MOD = 1_000_000_007;

    public int numberOfSets(int n, int k) {
        long[][] dp = new long[n][k + 1];
        long[][] prefix = new long[n][k + 1];

        // Base case: 0 segments
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
            prefix[i][0] = i + 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= Math.min(i, k); j++) {
                dp[i][j] = (dp[i - 1][j] + prefix[i - 1][j - 1]) % MOD;
                prefix[i][j] = (prefix[i - 1][j] + dp[i][j]) % MOD;
            }
        }

        return (int) dp[n - 1][k];
    }
}