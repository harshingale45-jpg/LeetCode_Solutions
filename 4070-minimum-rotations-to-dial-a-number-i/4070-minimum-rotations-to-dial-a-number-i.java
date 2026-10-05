class Solution {
    public int minRotations(String s) {
        int current = 0;
        int ans = 0;

        for (char c : s.toCharArray()) {
            int target = c - '0';

            int diff = Math.abs(current - target);

            int rotations = Math.min(diff, 10 - diff);

            ans += rotations;

            current = target;
        }

        return ans;
    }
}