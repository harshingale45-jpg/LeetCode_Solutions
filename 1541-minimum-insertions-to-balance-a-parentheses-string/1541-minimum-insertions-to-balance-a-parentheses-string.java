class Solution {
    public int minInsertions(String s) {
        int ans = 0;
        int open = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                open++;
            } else {
                // Check whether the next character is ')'
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    i++; // Use both closing parentheses
                } else {
                    ans++; // Insert one missing ')'
                }

                if (open == 0) {
                    ans++; // Insert a missing '('
                } else {
                    open--;
                }
            }
        }

        // Every unmatched '(' needs two ')'
        ans += open * 2;

        return ans;
    }
}