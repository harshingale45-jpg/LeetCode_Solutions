class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // A valid parentheses string must have even length
        if ((m + n - 1) % 2 != 0) {
            return false;
        }

        // Maximum possible balance
        int maxBalance = m + n;

        boolean[][][] dp = new boolean[m][n][maxBalance + 1];

        // Starting cell must be '('
        if (grid[0][0] == ')') {
            return false;
        }

        dp[0][0][1] = true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0) {
                    continue;
                }

                for (int balance = 0; balance <= maxBalance; balance++) {

                    // Current cell is ')'
                    // So previous balance must be balance + 1
                    if (grid[i][j] == ')') {
                        if (balance + 1 <= maxBalance) {

                            if (i > 0 && dp[i - 1][j][balance + 1]) {
                                dp[i][j][balance] = true;
                            }

                            if (j > 0 && dp[i][j - 1][balance + 1]) {
                                dp[i][j][balance] = true;
                            }
                        }
                    }

                    // Current cell is '('
                    else {
                        if (balance > 0) {

                            if (i > 0 && dp[i - 1][j][balance - 1]) {
                                dp[i][j][balance] = true;
                            }

                            if (j > 0 && dp[i][j - 1][balance - 1]) {
                                dp[i][j][balance] = true;
                            }
                        }
                    }
                }
            }
        }

        return dp[m - 1][n - 1][0];
    }
}