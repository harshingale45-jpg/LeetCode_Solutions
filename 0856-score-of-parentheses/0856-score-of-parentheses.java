class Solution {
    public int scoreOfParentheses(String s) {
        int[] stack = new int[s.length()];
        int top = 0;

        for (char c : s.toCharArray()) {

            if (c == '(') {
                stack[++top] = 0;
            } else {
                int value = stack[top--];

                if (value == 0) {
                    value = 1;       // ()
                } else {
                    value = 2 * value; // (A)
                }

                stack[top] += value;
            }
        }

        return stack[0];
    }
}